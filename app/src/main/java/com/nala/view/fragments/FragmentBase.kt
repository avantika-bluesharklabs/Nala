package com.nala.view.fragments

import android.Manifest
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
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.nala.BuildConfig
import com.nala.businesslogic.network.RetroFitInterface
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.utils.ConstantCodes
import com.nala.utils.ConstantCodes.Companion.PREF_GPS_ALTITUDE
import com.nala.utils.ConstantCodes.Companion.PREF_GPS_LATITUDE
import com.nala.utils.ConstantCodes.Companion.PREF_GPS_LONGITUDE
import com.nala.utils.ConstantCodes.Companion.REQUEST_LOCATION_PERMISSION
import com.nala.utils.Utils
import com.nala.utils.UtilsDocument
import com.nala.utils.UtilsImage
import com.nala.view.MyApplication
import com.nala.view.activities.ActivityBase
import com.nala.view.activities.ActivityMain
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * Created by Avantika Gadhiya on Mar, 30 2020 14:39.
 */

open class FragmentBase : Fragment(), LocationListener {
    @Inject
    lateinit var mPreferences: UtilsSharedPreferences


    @Inject
    lateinit var mRetroFitInterface: RetroFitInterface

    protected lateinit var mApplication: MyApplication
    protected lateinit var mActivity: ActivityBase
    protected lateinit var mContext: Context
    protected var mMainActivity: ActivityMain? = null

    @Inject
    lateinit var mBroadcastManager: LocalBroadcastManager

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

    private lateinit var locationManager: LocationManager
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
       // requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        requireActivity().window.statusBarColor = Color.TRANSPARENT



        super.onCreate(savedInstanceState)
        mActivity = activity as ActivityBase
        mMainActivity = activity as ActivityMain
        mApplication = mActivity.mApplication
        mApplication.getAppComponent().inject(this)
        mContext = context as Context
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE -> onRequestPermissionsResultCameraWrite(grantResults)
            ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE -> onRequestPermissionsResultWrite(grantResults)
            ConstantCodes.REQUEST_PERMISSION_CAMERA -> onRequestPermissionsResultCamera(grantResults)
            ConstantCodes.REQUEST_LOCATION_PERMISSION -> onRequestPermissionsResultLocation(grantResults)
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
                        resources.getString(com.nala.R.string.permission_camera),
                        ConstantCodes.REQUEST_PERMISSION_CAMERA
                    )
                } else if (cameraPermitted) {
                    showAlertPermissions(
                        resources.getString(
                            com.nala.R.string.permission_externalstorage_camera
                        ),
                        ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
                    )
                } else {
                    showAlertPermissions(
                        resources.getString(com.nala.R.string.permission_camera_external),
                        ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE
                    )
                }
            }
        } else {
            showAlertPermissions(
                resources.getString(com.nala.R.string.permission_camera_external),
                ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE
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
                    ConstantCodes.REQUEST_PHOTOGALLERY_RESULT
                )
            } else if (mIsFromDocument) {
                mIsFromDocument = false
                fileChooserIntent()
            }
        } else {
            if (mIsFromCamera) {
                showAlertPermissions(
                    resources.getString(com.nala.R.string.permission_externalstorage_camera),
                    ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
                )
            } else if (mIsFromPhotoGallery) {
                showAlertPermissions(
                    resources.getString(
                        com.nala.R.string.permission_externalstorage_photogallery
                    ),
                    ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
                )
            } else if (mIsFromDocument) {
                showAlertPermissions(
                    resources.getString(
                        com.nala.R.string.permission_externalstorage_document
                    ),
                    ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
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
                resources.getString(com.nala.R.string.permission_camera),
                ConstantCodes.REQUEST_PERMISSION_CAMERA
            )
        }
    }


    open fun broadcastError(error: Int, errorString: String) {
        mBroadcastManager.sendBroadcast(
            Intent(mContext.resources.getString(com.nala.R.string.broadcastFragBaseError))
                .putExtra(mContext.resources.getString(com.nala.R.string.bundleFragBaseError), error)
                .putExtra(
                    mContext.resources.getString(com.nala.R.string.bundleFragBaseErrorString),
                    errorString
                )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == ConstantCodes.REQUEST_PHOTOGALLERY_RESULT ||
                    requestCode == ConstantCodes.REQUEST_RESULT_DOCUMENT) && resultCode == AppCompatActivity.RESULT_OK
        ) {
            processResultGalleryDocument(requestCode, data)
        } else if (requestCode == ConstantCodes.REQUEST_CAMERA_RESULT && resultCode == AppCompatActivity.RESULT_OK) {
            manipulateCameraCapture()
        } /*else {
            mCallBackManager.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    open fun processResultGalleryDocument(requestCode: Int, data: Intent?) {
        if (data != null) {
            if (requestCode == ConstantCodes.REQUEST_PHOTOGALLERY_RESULT) {
                AsyncImage().execute(data.data)
            } else {
                manipulateDocumentSelected(data.data)
            }
        } else {
            broadcastError(
                if (requestCode == ConstantCodes.REQUEST_PHOTOGALLERY_RESULT) com.nala.R.string.message_unabletochooseimage else com.nala.R.string.message_unabletochoosedocument,
                ""
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun imageChooser() {
        mIsForFrontCamera = false
        Utils.hideKeyboard(mActivity)
        val items = arrayOf<CharSequence>(
            getString(com.nala.R.string.text_camera),
            getString(com.nala.R.string.text_photogallery)
        )
        val builder = AlertDialog.Builder(mActivity)
        builder.setTitle(getString(com.nala.R.string.text_uploadphotousing))
        builder.setItems(items) { dialog: DialogInterface, item: Int ->
            if (item == 0) {
                manipulateCameraClick()
            } else {
                manipulatePhotoGalleryClick()
            }
            dialog.dismiss()
        }
        builder.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun manipulateCameraClick() {
        if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = true
            mIsFromDocument = false
            requestCameraAndWritePermissions()
        } else if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = true
            mIsFromDocument = false
            requestWritePermissions(
                resources.getString(com.nala.R.string.permission_externalstorage_camera)
            )
        } else if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.CAMERA
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
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = true
            mIsFromCamera = false
            mIsFromDocument = false
            requestWritePermissions(
                resources.getString(com.nala.R.string.permission_externalstorage_photogallery)
            )
        } else {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            if (intent.resolveActivity(mContext.packageManager) != null) {
                startActivityForResult(intent, ConstantCodes.REQUEST_PHOTOGALLERY_RESULT)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestCameraAndWritePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) &&
            ActivityCompat.shouldShowRequestPermissionRationale(
                mActivity,
                Manifest.permission.CAMERA
            )
        ) {
            showAlertPermissions(
                resources.getString(com.nala.R.string.permission_camera_external),
                ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE
            )
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE
            )
        }
    }



    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestWritePermissions(alertMessage: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            showAlertPermissions(alertMessage, ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE)
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun requestCameraPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                mActivity,
                Manifest.permission.CAMERA
            )
        ) {
            showAlertPermissions(
                resources.getString(com.nala.R.string.permission_camera),
                ConstantCodes.REQUEST_PERMISSION_CAMERA
            )
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                ConstantCodes.REQUEST_PERMISSION_CAMERA
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    open fun showAlertPermissions(alertMessage: String, requestType: Int) {
        val builder = AlertDialog.Builder(mActivity, com.nala.R.style.AppCompatAlertDialogStyle)
        builder.setTitle(resources.getString(com.nala.R.string.message_alert))
        builder.setMessage(alertMessage)
        builder.setCancelable(true)
        builder.setPositiveButton(
            resources.getString(com.nala.R.string.text_request)
        ) { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            when (requestType) {
                ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE -> requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    ConstantCodes.REQUEST_PERMISSION_WRITESTORAGE
                )
                ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE -> requestPermissions(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ), ConstantCodes.REQUEST_PERMISSION_CAMERAANDWRITE
                )
                else -> requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    ConstantCodes.REQUEST_PERMISSION_CAMERA
                )
            }
        }
        builder.setNegativeButton(
            resources.getString(com.nala.R.string.message_cancel)
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
                    fileTemporary = UtilsImage.createTempImageFile(mContext)
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
                    startActivityForResult(takePictureIntent, ConstantCodes.REQUEST_CAMERA_RESULT)
                } else {
                    error = com.nala.R.string.message_camera_unabletocapture
                }
            } else {
                error = com.nala.R.string.message_camera_unabletoopen
            }
        } else {
            error = com.nala.R.string.message_camera_absent
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
                    mContext.getResources().getString(com.nala.R.string.bundleChooserProgressIsToShow)
                ).putExtra(
                    mContext.getResources().getString(com.nala.R.string.bundleChooserProgressIsToShow),
                    true
                )
            )
        }

        override fun doInBackground(vararg uris: Uri?): Uri? {
            try {
                return UtilsImage.savePicReturnUri(
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
                broadcastError(com.nala.R.string.message_unabletochooseimage, "")
            }
            mBroadcastManager.sendBroadcast(
                Intent(
                    mContext.getResources().getString(com.nala.R.string.bundleChooserProgressIsToShow)
                ).putExtra(
                    mContext.getResources().getString(com.nala.R.string.bundleChooserProgressIsToShow),
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
                .append(ConstantCodes.FILE_SEPERATOR).append(fileName)
            val file: File = File(sb.toString())
            if (!file.exists() || file.length() <= 0) {
                if (mIsDocumentByCamera) {
                    broadcastError(com.nala.R.string.message_unabletochoosedocument, (errorString)!!)
                } else {
                    broadcastError(com.nala.R.string.message_unabletochooseimage, (errorString)!!)
                }
                mIsDocumentByCamera = false
                return
            }
            val fileSizeError: String = UtilsImage.calculateFileSize(
                mActivity, file, fileSize, fileSizeMin,
                fileSizeMax, false
            )
            if (TextUtils.isEmpty(fileSizeError)) {
                if (mIsDocumentByCamera) {
                    mBroadcastManager.sendBroadcast(
                        Intent(mContext.resources.getString(com.nala.R.string.broadcastDocumentResult))
                            .putExtra(
                                mContext.resources.getString(com.nala.R.string.bundleDocumentIsAdditional),
                                mIsDocumentForAdditional
                            )
                            .putExtra(
                                mContext.resources.getString(com.nala.R.string.bundleDocumentFilePath),
                                file.absolutePath
                            )
                    )
                    if (mIsDocumentForAdditional) {
                        mIsDocumentForAdditional = false
                    }
                } else {
                    val bitmap: Bitmap = UtilsImage.getScaledMatrixedBitmapFromFile(
                        file, mContext.resources.getDimensionPixelSize(com.nala.R.dimen.appbar_height)
                    )
                    if (bitmap != null) {
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        val byteArray = stream.toByteArray()
                        mBroadcastManager.sendBroadcast(
                            Intent(mContext.resources.getString(com.nala.R.string.broadcastImageResult))
                                .putExtra(
                                    mContext.resources.getString(com.nala.R.string.bundleImageResultStream),
                                    byteArray
                                )
                                .putExtra(
                                    mContext.resources.getString(com.nala.R.string.bundleImageResultUri),
                                    mUriForPhotoResult.toString()
                                )
                        )
                    } else {
                        error = com.nala.R.string.message_unabletochooseimage
                    }
                }
            } else {
                errorString = fileSizeError
            }
        } else {
            error = com.nala.R.string.message_unabletochooseimage
        }
        if (error != -1 || !TextUtils.isEmpty(errorString)) {
            broadcastError(error, (errorString)!!)
        }
        mIsDocumentByCamera = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected open fun fileChooser(isAdditional: Boolean) {
        Utils.hideKeyboard(mActivity)
        val items =
            arrayOf<CharSequence>(getString(com.nala.R.string.text_camera), getString(com.nala.R.string.text_storage))
        val builder = AlertDialog.Builder(mActivity)
        builder.setTitle(getString(com.nala.R.string.text_uploaddocumentusing))
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
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mIsFromPhotoGallery = false
            mIsFromCamera = false
            mIsFromDocument = true
            requestWritePermissions(
                resources.getString(com.nala.R.string.permission_externalstorage_document)
            )
        } else {
            fileChooserIntent()
        }
    }

    open fun fileChooserIntent() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(
                intent,
                mContext.resources.getString(com.nala.R.string.text_uploaddocument)
            ), ConstantCodes.REQUEST_RESULT_DOCUMENT
        )
    }


    open fun manipulateDocumentSelected(data: Uri?) {
        var error = -1
        var errorString: String? = ""
        var filePath: String = ""
        try {
            filePath = UtilsDocument.getPath(mActivity, data)
        } catch (exception: Exception) {
            //   Logger.Companion.e(javaClass.simpleName + " manipulateDocumentSelected", exception)
        }
        if (filePath != null) {
            val file = File(filePath)
            if (!file.exists() || file.length() <= 0) {
                broadcastError(com.nala.R.string.message_unabletochoosedocument, (errorString)!!)
                return
            }
            val fileSizeError: String = UtilsImage.calculateFileSize(
                mActivity, file, fileSize, fileSizeMin,
                fileSizeMax, true
            )
            if (TextUtils.isEmpty(fileSizeError)) {
                mBroadcastManager.sendBroadcast(
                    Intent(mContext.resources.getString(com.nala.R.string.broadcastDocumentResult))
                        .putExtra(
                            mContext.resources.getString(com.nala.R.string.bundleDocumentIsAdditional),
                            mIsDocumentForAdditional
                        )
                        .putExtra(
                            mContext.resources.getString(com.nala.R.string.bundleDocumentFilePath),
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
            error = com.nala.R.string.message_unabletochoosedocument
        }
        if (error != -1 || !TextUtils.isEmpty(errorString)) {
            broadcastError(error, (errorString)!!)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun onRequestPermissionsResultLocation(grantResults: IntArray?) {
        if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mActivity, "Permission Granted", Toast.LENGTH_SHORT).show()

            if (ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            getLastLocation()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        } else {
            Toast.makeText(mActivity, "Permission Denied", Toast.LENGTH_SHORT).show()

            showAlertPermissions(
                "Location permission is needed to fetch current location",
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    fun getLocation() {
        locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                mActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            getLastLocation()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

  /*  private fun getLastLocation() {

        if ((ContextCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                mActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(mActivity) { task ->
                    if (task.isSuccessful && task.result != null) {
                        mLastLocation = task.result

                        Log.d(
                            "TAG",
                            "=====>" + "Latitudee: " + mLastLocation?.latitude + " , Longitude: " + mLastLocation?.longitude
                        )

                        mPreferences.setString(
                            PREF_GPS_LATITUDE,
                            mLastLocation?.latitude.toString()
                        )
                        mPreferences.setString(
                            PREF_GPS_LONGITUDE,
                            mLastLocation?.longitude.toString()
                        )
                        mPreferences.setString(PREF_GPS_ALTITUDE, mLastLocation?.altitude.toString())

                        *//* mPreferences.setString(R.string.pref_gps_lat,ConstantCodes.USER_LATITUDE)
                         mPreferences.setString(R.string.pref_gps_lng,ConstantCodes.USER_LONGITUDE)*//*
                    }
                }
        }
    }*/

    private fun getLastLocation() {

        if ((ContextCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                mActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {


            mFusedLocationClient!!.lastLocation.addOnSuccessListener { location : Location? ->
                Log.e(
                    "TAGsas",
                    "=====>" + "isSuccessful: " + location?.latitude + " , result: " + location?.longitude
                )
                mBroadcastManager.sendBroadcast(
                    Intent(mContext.resources.getString(com.nala.R.string.broadcastLocationResult))
                        .putExtra(
                            mContext.resources.getString(com.nala.R.string.bundleLocationLatitude),
                            location?.latitude
                        )
                        .putExtra(
                            mContext.resources.getString(com.nala.R.string.bundleLocationLongitude),
                            location?.longitude
                        )
                )
                // Got last known location. In some rare situations this can be null.
            }

//
//            mFusedLocationClient!!.lastLocation
//                .addOnCompleteListener(mActivity) { task ->
//
//
//                    Log.e(
//                        "TAGsas",
//                        "=====>" + "isSuccessful: " + task.isSuccessful + " , result: " + task.result
//                    )
//
//                    if (task.isSuccessful && task.result != null) {
//                        mLastLocation = task.result
//
//                        Log.d(
//                            "TAG",
//                            "=====>" + "Latitudee: " + mLastLocation?.latitude + " , Longitude: " + mLastLocation?.longitude
//                        )
//
//
//                        mPreferences.setString(
//                            PREF_GPS_LATITUDE,
//                            mLastLocation?.latitude.toString()
//                        )
//                        mPreferences.setString(
//                            PREF_GPS_LONGITUDE,
//                            mLastLocation?.longitude.toString()
//                        )
//                        mPreferences.setString(
//                            PREF_GPS_ALTITUDE,
//                            mLastLocation?.altitude.toString()
//                        )
//
//
//
//
//
//                        /* mPreferences.setString(R.string.pref_gps_lat,ConstantCodes.USER_LATITUDE)
//                         mPreferences.setString(R.string.pref_gps_lng,ConstantCodes.USER_LONGITUDE)*/
//                    }
//                }

        }
    }








    override fun onLocationChanged(location: Location?) {

        Log.d(
            "TAG",
            "=====>" + "Latitude: " + location?.latitude + " , Longitude: " + location?.longitude
        )
        mBroadcastManager.sendBroadcast(
            Intent(mContext.resources.getString(com.nala.R.string.broadcastLocationResult))
                .putExtra(
                    mContext.resources.getString(com.nala.R.string.bundleLocationLatitude),
                    location?.latitude
                )
                .putExtra(
                    mContext.resources.getString(com.nala.R.string.bundleLocationLongitude),
                    location?.longitude
                )
        )
        mPreferences.setString(PREF_GPS_LATITUDE, location?.latitude.toString())
        mPreferences.setString(PREF_GPS_LONGITUDE, location?.longitude.toString())
        mPreferences.setString(PREF_GPS_ALTITUDE, location?.altitude.toString())


        /* mPreferences.setString(R.string.pref_gps_lat,ConstantCodes.USER_LATITUDE)
         mPreferences.setString(R.string.pref_gps_lng,ConstantCodes.USER_LONGITUDE)*/

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }


 }
