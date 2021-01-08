package com.nala.view.activities

import android.Manifest
import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nala.BuildConfig
import com.nala.R
import com.nala.businesslogic.network.RetroFitInterface
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.utils.ConstantCodes
import com.nala.utils.ConstantCodes.Companion.FILE_SEPERATOR
import com.nala.utils.ConstantCodes.Companion.REQUEST_CAMERA_RESULT
import com.nala.utils.ConstantCodes.Companion.REQUEST_LOCATION_PERMISSION
import com.nala.utils.ConstantCodes.Companion.REQUEST_PERMISSION_CAMERA
import com.nala.utils.ConstantCodes.Companion.REQUEST_PERMISSION_CAMERAANDWRITE
import com.nala.utils.ConstantCodes.Companion.REQUEST_PERMISSION_WRITESTORAGE
import com.nala.utils.ConstantCodes.Companion.REQUEST_PHOTOGALLERY_RESULT
import com.nala.utils.ConstantCodes.Companion.REQUEST_RESULT_DOCUMENT
import com.nala.utils.Logger
import com.nala.utils.Utils.Companion.hideKeyboard
import com.nala.utils.UtilsDocument
import com.nala.utils.UtilsImage
import com.nala.utils.UtilsImage.*
import com.nala.view.MyApplication
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */

@SuppressLint("Registered")
open class ActivityBase : AppCompatActivity(), LocationListener {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mPreferences: UtilsSharedPreferences

    @Inject
    lateinit var mRetroFitInterface: RetroFitInterface

    @Inject
    lateinit var mBroadcastManager: LocalBroadcastManager

    lateinit var mApplication: MyApplication

    //private CallbackManager mCallBackManager = null;
    private var mIsFromCamera = false
    private var mIsFromPhotoGallery = false
    private var mIsFromDocument = false
    private var mIsDocumentForAdditional = false
    private var mIsDocumentByCamera = false
    protected var mIsForFrontCamera = false
    private var mUriForPhotoResult: Uri? = null

    protected var fileSize = ""
    protected var fileSizeMin = ""
    protected var fileSizeMax = ""
    protected var appointmentType = ""

    private lateinit var locationManager: LocationManager
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {

//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        window.statusBarColor = Color.TRANSPARENT

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = Color.WHITE


        super.onCreate(savedInstanceState)
        mApplication = application as MyApplication
        mPreferences = mApplication.getAppComponent().getPreferences()
        mApplication.getAppComponent().inject(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSION_CAMERAANDWRITE -> onRequestPermissionsResultCameraWrite(grantResults)
            REQUEST_PERMISSION_WRITESTORAGE -> onRequestPermissionsResultWrite(grantResults)
            REQUEST_PERMISSION_CAMERA -> onRequestPermissionsResultCamera(grantResults)
            REQUEST_LOCATION_PERMISSION -> onRequestPermissionsResultLocation(grantResults)
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun onRequestPermissionsResultCameraWrite(grantResults: IntArray?) {
        if (grantResults != null && grantResults.size == 2) {
            val writePermitted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            val cameraPermitted = grantResults[1] == PackageManager.PERMISSION_GRANTED
            if (writePermitted && cameraPermitted) {
                openCamera()
            } else {
                if (writePermitted) {
                    showAlertPermissions(
                        resources.getString(R.string.permission_camera),
                        REQUEST_PERMISSION_CAMERA
                    )
                } else if (cameraPermitted) {
                    showAlertPermissions(
                        resources.getString(
                            R.string.permission_externalstorage_camera
                        ),
                        REQUEST_PERMISSION_WRITESTORAGE
                    )
                } else {
                    showAlertPermissions(
                        resources.getString(R.string.permission_camera_external),
                        REQUEST_PERMISSION_CAMERAANDWRITE
                    )
                }
            }
        } else {
            showAlertPermissions(
                resources.getString(R.string.permission_camera_external),
                REQUEST_PERMISSION_CAMERAANDWRITE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun onRequestPermissionsResultWrite(grantResults: IntArray?) {
        if (grantResults != null && grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (mIsFromCamera) {
                mIsFromCamera = false
                openCamera()
            } else if (mIsFromPhotoGallery) {
                mIsFromPhotoGallery = false
                startActivityForResult(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ),
                    REQUEST_PHOTOGALLERY_RESULT
                )
            } else if (mIsFromDocument) {
                mIsFromDocument = false
                fileChooserIntent()
            }
        } else {
            if (mIsFromCamera) {
                showAlertPermissions(
                    resources.getString(R.string.permission_externalstorage_camera),
                    REQUEST_PERMISSION_WRITESTORAGE
                )
            } else if (mIsFromPhotoGallery) {
                showAlertPermissions(
                    resources.getString(
                        R.string.permission_externalstorage_photogallery
                    ),
                    REQUEST_PERMISSION_WRITESTORAGE
                )
            } else if (mIsFromDocument) {
                showAlertPermissions(
                    resources.getString(
                        R.string.permission_externalstorage_document
                    ),
                    REQUEST_PERMISSION_WRITESTORAGE
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun onRequestPermissionsResultCamera(grantResults: IntArray?) {
        if (grantResults != null && grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            showAlertPermissions(
                resources.getString(R.string.permission_camera),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun onRequestPermissionsResultLocation(grantResults: IntArray?) {
        if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            getLastLocation()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()

            showAlertPermissions(
                resources.getString(R.string.permission_location),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    open fun broadcastError(error: Int, errorString: String) {
        mBroadcastManager.sendBroadcast(
            Intent(mContext.resources.getString(R.string.broadcastFragBaseError))
                .putExtra(mContext.resources.getString(R.string.bundleFragBaseError), error)
                .putExtra(
                    mContext.resources.getString(R.string.bundleFragBaseErrorString),
                    errorString
                )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_PHOTOGALLERY_RESULT ||
                    requestCode == REQUEST_RESULT_DOCUMENT) && resultCode == RESULT_OK
        ) {
            processResultGalleryDocument(requestCode, data)
        } else if (requestCode == REQUEST_CAMERA_RESULT && resultCode == RESULT_OK) {
            manipulateCameraCapture()
        } /*else {
            mCallBackManager.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    open fun processResultGalleryDocument(requestCode: Int, data: Intent?) {
        if (data != null) {
            if (requestCode == REQUEST_PHOTOGALLERY_RESULT) {
                AsyncImage().execute(data.data)
            } else {
                manipulateDocumentSelected(data.data)
            }
        } else {
            broadcastError(
                if (requestCode == REQUEST_PHOTOGALLERY_RESULT) R.string.message_unabletochooseimage else R.string.message_unabletochoosedocument,
                ""
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun imageChooser(isDoc: Boolean) {
        mIsForFrontCamera = false
        hideKeyboard(this@ActivityBase)

        val items : Array<CharSequence>
        if (isDoc) {
            items = arrayOf<CharSequence>(getString(R.string.text_camera),getString(R.string.text_photogallery),getString(R.string.text_document))
        }else{
            items = arrayOf<CharSequence>(getString(R.string.text_camera),getString(R.string.text_photogallery))
        }

        val builder = AlertDialog.Builder(this@ActivityBase)
        builder.setTitle(getString(R.string.text_uploadphotousing))
        builder.setItems(items) { dialog: DialogInterface, item: Int ->
            if (item == 0) {
                manipulateCameraClick()
            } else if (item == 1) {
                manipulatePhotoGalleryClick()
            } else {
                manipulateFileChooser()
            }
            dialog.dismiss()
        }
        builder.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun manipulateCameraClick() {
        if (ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = true
            mIsFromDocument = false
            requestCameraAndWritePermissions()
        } else if (ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = true
            mIsFromDocument = false
            requestWritePermissions(
                resources.getString(R.string.permission_externalstorage_camera)
            )
        } else if (ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = true
            mIsFromDocument = false
            requestCameraPermissions()
        } else {
            openCamera()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun manipulatePhotoGalleryClick() {
        if (ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = true
            mIsFromCamera = false
            mIsFromDocument = false
            requestWritePermissions(
                resources.getString(R.string.permission_externalstorage_photogallery)
            )
        } else {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            if (intent.resolveActivity(mContext.packageManager) != null) {
                startActivityForResult(intent, REQUEST_PHOTOGALLERY_RESULT)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestCameraAndWritePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            ) &&
            ActivityCompat.shouldShowRequestPermissionRationale(
                this@ActivityBase,
                permission.CAMERA
            )
        ) {
            showAlertPermissions(
                resources.getString(R.string.permission_camera_external),
                REQUEST_PERMISSION_CAMERAANDWRITE
            )
        } else {
            requestPermissions(
                arrayOf(permission.WRITE_EXTERNAL_STORAGE, permission.CAMERA),
                REQUEST_PERMISSION_CAMERAANDWRITE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestWritePermissions(alertMessage: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            showAlertPermissions(alertMessage, REQUEST_PERMISSION_WRITESTORAGE)
        } else {
            requestPermissions(
                arrayOf(permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_WRITESTORAGE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestCameraPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@ActivityBase,
                permission.CAMERA
            )
        ) {
            showAlertPermissions(
                resources.getString(R.string.permission_camera),
                REQUEST_PERMISSION_CAMERA
            )
        } else {
            requestPermissions(
                arrayOf(permission.CAMERA),
                REQUEST_PERMISSION_CAMERA
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun showAlertPermissions(alertMessage: String, requestType: Int) {
        val builder = AlertDialog.Builder(this@ActivityBase, R.style.AppCompatAlertDialogStyle)
        builder.setTitle(resources.getString(R.string.message_alert))
        builder.setMessage(alertMessage)
        builder.setCancelable(true)
        builder.setPositiveButton(
            resources.getString(R.string.text_request)
        ) { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            when (requestType) {
                REQUEST_PERMISSION_WRITESTORAGE -> requestPermissions(
                    arrayOf(permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_WRITESTORAGE
                )
                REQUEST_PERMISSION_CAMERAANDWRITE -> requestPermissions(
                    arrayOf(
                        permission.WRITE_EXTERNAL_STORAGE,
                        permission.CAMERA
                    ), REQUEST_PERMISSION_CAMERAANDWRITE
                )

                REQUEST_LOCATION_PERMISSION -> requestPermissions(
                    arrayOf(
                        permission.ACCESS_FINE_LOCATION,
                        permission.ACCESS_COARSE_LOCATION
                    ), REQUEST_LOCATION_PERMISSION
                )
                else -> requestPermissions(
                    arrayOf(permission.CAMERA),
                    REQUEST_PERMISSION_CAMERA
                )
            }
        }
        builder.setNegativeButton(
            resources.getString(R.string.message_cancel)
        ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    open fun openCamera() {
        var error = -1
        if (UtilsImage.isCameraPresent(mContext.packageManager)) {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(mContext.packageManager) != null) {
                var fileTemporary: File? = null
                try {
                    fileTemporary = createTempImageFile(mContext)
                } catch (exception: IOException) {
                    //e(javaClass.simpleName + " openCamera", exception)
                }
                if (fileTemporary != null) {
                    mUriForPhotoResult = FileProvider.getUriForFile(
                        mContext,
                        BuildConfig.APPLICATION_ID.toString() + ".provider",
                        fileTemporary
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUriForPhotoResult)
                    if (mIsForFrontCamera) {
                        takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
                        takePictureIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1)
                        takePictureIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
                        mIsForFrontCamera = false
                    }
                    startActivityForResult(takePictureIntent, REQUEST_CAMERA_RESULT)
                } else {
                    error = R.string.message_camera_unabletocapture
                }
            } else {
                error = R.string.message_camera_unabletoopen
            }
        } else {
            error = R.string.message_camera_absent
        }
        if (error != -1) {
            broadcastError(error, "")
        }
    }

    inner class AsyncImage : AsyncTask<Uri?, Void?, Uri?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            mBroadcastManager.sendBroadcast(
                Intent(
                    mContext.getResources().getString(R.string.bundleChooserProgressIsToShow)
                ).putExtra(
                    mContext.getResources().getString(R.string.bundleChooserProgressIsToShow),
                    true
                )
            )
        }

        override fun doInBackground(vararg uris: Uri?): Uri? {
            try {
                return savePicReturnUri(
                    MediaStore.Images.Media.getBitmap(
                        mContext
                            .getContentResolver(), uris[0]
                    ), mContext
                )
            } catch (exception: IOException) {
                //e(javaClass.simpleName + " AsyncImage", exception)
            }
            return null
        }

        override fun onPostExecute(uri: Uri?) {
            super.onPostExecute(uri)
            if (uri != null) {
                mUriForPhotoResult = uri
                manipulateCameraCapture()
            } else {
                broadcastError(R.string.message_unabletochooseimage, "")
            }
            mBroadcastManager.sendBroadcast(
                Intent(
                    mContext.getResources().getString(R.string.bundleChooserProgressIsToShow)
                ).putExtra(
                    mContext.getResources().getString(R.string.bundleChooserProgressIsToShow),
                    false
                )
            )
        }
    }

    open fun manipulateCameraCapture() {
        var error = -1
        var errorString: String? = ""
        if (mUriForPhotoResult != null) {
            val fileName: String = mUriForPhotoResult.toString()
                .substring(mUriForPhotoResult.toString().lastIndexOf("/") + 1)
            val sb: StringBuilder = StringBuilder()
            sb.append(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES))
                .append(FILE_SEPERATOR).append(fileName)
            val file: File = File(sb.toString())
            if (!file.exists() || file.length() <= 0) {
                if (mIsDocumentByCamera) {
                    broadcastError(R.string.message_unabletochoosedocument, (errorString)!!)
                } else {
                    broadcastError(R.string.message_unabletochooseimage, (errorString)!!)
                }
                mIsDocumentByCamera = false
                return
            }
            val fileSizeError: String = calculateFileSize(
                this@ActivityBase, file, fileSize, fileSizeMin,
                fileSizeMax, false
            )
            if (TextUtils.isEmpty(fileSizeError)) {
                if (mIsDocumentByCamera) {
                    mBroadcastManager.sendBroadcast(
                        Intent(mContext.resources.getString(R.string.broadcastDocumentResult))
                            /* Intent(mContext.resources.getString(R.string.broadcastImageResult))*/
                            .putExtra(
                                mContext.resources.getString(R.string.bundleDocumentIsAdditional),
                                mIsDocumentForAdditional
                            )
                            .putExtra(
                                mContext.resources.getString(R.string.bundleDocumentFilePath),
                                file.absolutePath
                            )
                    )
                    if (mIsDocumentForAdditional) {
                        mIsDocumentForAdditional = false
                    }
                } else {
                    val bitmap: Bitmap = UtilsImage.getScaledMatrixedBitmapFromFile(
                        file, mContext.resources.getDimensionPixelSize(R.dimen.appbar_height)
                    )
                    if (bitmap != null) {
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        val byteArray = stream.toByteArray()
                        mBroadcastManager.sendBroadcast(
                            Intent(mContext.resources.getString(R.string.broadcastImageResult))
                                .putExtra(
                                    mContext.resources.getString(R.string.bundleImageResultStream),
                                    byteArray
                                )
                                .putExtra(
                                    mContext.resources.getString(R.string.bundleImageResultUri),
                                    mUriForPhotoResult.toString()
                                )
                        )
                    } else {
                        error = R.string.message_unabletochooseimage
                    }
                }
            } else {
                errorString = fileSizeError
            }
        } else {
            error = R.string.message_unabletochooseimage
        }
        if (error != -1 || !TextUtils.isEmpty(errorString)) {
            broadcastError(error, (errorString)!!)
        }
        mIsDocumentByCamera = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun fileChooser(isAdditional: Boolean) {
        hideKeyboard(this@ActivityBase)
        val items =
            arrayOf<CharSequence>(getString(R.string.text_camera), getString(R.string.text_storage))
        val builder = AlertDialog.Builder(this@ActivityBase)
        builder.setTitle(getString(R.string.text_uploaddocumentusing))
        builder.setItems(items) { dialog: DialogInterface, item: Int ->
            mIsDocumentForAdditional = isAdditional
            if (item == 0) {
                mIsDocumentByCamera = true
                manipulateCameraClick()
            } else {
                manipulateFileChooser()
            }
            dialog.dismiss()
        }
        builder.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun manipulateFileChooser() {
        if (ActivityCompat.checkSelfPermission(
                this@ActivityBase,
                permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = false
            mIsFromDocument = true
            requestWritePermissions(
                resources.getString(R.string.permission_externalstorage_document)
            )
        } else {
            fileChooserIntent()
        }
    }

    open fun fileChooserIntent() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        //intent.type = "*/*"
        intent.type = "application/pdf"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(
                intent,
                mContext.resources.getString(R.string.text_uploaddocument)
            ), REQUEST_RESULT_DOCUMENT
        )
    }

    open fun manipulateDocumentSelected(data: Uri?) {
        var error = -1
        var errorString: String? = ""
        var filePath: String = ""
        try {
            filePath = UtilsDocument.getPath(this@ActivityBase, data)
        } catch (exception: Exception) {
            Logger.Companion.e(javaClass.simpleName + " manipulateDocumentSelected", exception)
        }
        if (filePath != null) {
            val file = File(filePath)
            if (!file.exists() || file.length() <= 0) {
                broadcastError(R.string.message_unabletochoosedocument, (errorString)!!)
                return
            }
            val fileSizeError: String = calculateFileSize(
                this@ActivityBase, file, fileSize, fileSizeMin,
                fileSizeMax, true
            )
            if (TextUtils.isEmpty(fileSizeError)) {
                mBroadcastManager.sendBroadcast(
                    Intent(mContext.resources.getString(R.string.broadcastDocumentResult))
                        /*  Intent(mContext.resources.getString(R.string.broadcastImageResult))*/
                        .putExtra(
                            mContext.resources.getString(R.string.bundleDocumentIsAdditional),
                            mIsDocumentForAdditional
                        )
                        .putExtra(
                            mContext.resources.getString(R.string.bundleDocumentFilePath),
                            filePath
                        )
                )
                if (mIsDocumentForAdditional) {
                    mIsDocumentForAdditional = false
                }
            } else {
                errorString = fileSizeError
            }
        } else {
            error = R.string.message_unabletochoosedocument
        }
        if (error != -1 || !TextUtils.isEmpty(errorString)) {
            broadcastError(error, (errorString)!!)
        }
    }

    fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            getLastLocation()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

    private fun getLastLocation() {

        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        mLastLocation = task.result

                        Log.d(
                            "TAG",
                            "=====>" + "Latitudee: " + mLastLocation?.latitude + " , Longitude: " + mLastLocation?.longitude
                        )

                        mPreferences.setString(
                            R.string.pref_gps_lat,
                            mLastLocation?.latitude.toString()
                        )
                        mPreferences.setString(
                            R.string.pref_gps_lng,
                            mLastLocation?.longitude.toString()
                        )

                        /* mPreferences.setString(R.string.pref_gps_lat,ConstantCodes.USER_LATITUDE)
                         mPreferences.setString(R.string.pref_gps_lng,ConstantCodes.USER_LONGITUDE)*/
                    }
                }
        }
    }


    override fun onLocationChanged(location: Location?) {

        Log.d(
            "TAG",
            "=====>" + "Latitude: " + location?.latitude + " , Longitude: " + location?.longitude
        )

        mPreferences.setString(R.string.pref_gps_lat, location?.latitude.toString())
        mPreferences.setString(R.string.pref_gps_lng, location?.longitude.toString())

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}