package com.nala.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.exifinterface.media.ExifInterface;

import com.nala.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.nala.utils.ConstantCodes.FILE_SEPERATOR;

/**
 * Created by Avantika Gadhiya on Jul, 25 2018 17:05.
 * <p>
 * Utility for images
 */
public class UtilsImage {

    /**
     * Used to check presence of camera on device
     *
     * @param packageManager Package manager to check for camera
     * @return Is camera present or not
     */
    public static boolean isCameraPresent(PackageManager packageManager) {
        boolean isCameraPresent = false;
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
                packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) ||
                packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            final int cameraCount = Camera.getNumberOfCameras();
            if (cameraCount > 0) {
                isCameraPresent = true;
            }
        }
        return isCameraPresent;
    }

    public static File createTempImageFile(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format
                (new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",         /* suffix */
                storageDir      /* directory */
        );
    }

    public static Bitmap getMatrixedBitmapFromFile(File file) {
        Matrix matrixForRotation = new Matrix();
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), new BitmapFactory
                .Options());

        if (bitmap == null) {
            return null;
        }
        matrixForRotation.postRotate(bitmapExifOrientation(file.getAbsolutePath()));
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrixForRotation, true);
    }

    public static Bitmap getScaledMatrixedBitmapFromFile(File file, int dimension) {
        Matrix matrixForRotation = new Matrix();
        Bitmap bitmapDecodedFile = null;
        float fileSizeInBytes = file.length();
        float fileSizeInKB = fileSizeInBytes / 1024;
        if (fileSizeInKB > 200) {
            bitmapDecodedFile = compressImage(file);
        } else
        {
            bitmapDecodedFile = getBitmapFromAbsolutePath(file.getAbsolutePath());
        }
        if (bitmapDecodedFile == null) {
            return null;
        }
        matrixForRotation.postRotate(bitmapExifOrientation(file.getAbsolutePath()));
        return Bitmap.createBitmap(bitmapDecodedFile, 0, 0,
                bitmapDecodedFile.getWidth(), bitmapDecodedFile.getHeight(),
                matrixForRotation, true);
    }

    private static Bitmap getScaledDownBitmapForImageView(String absolutePath, int dimension) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(absolutePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / dimension, photoH / dimension);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeFile(absolutePath, bmOptions);

    }

    private static Bitmap getBitmapFromAbsolutePath(String absolutePath) {
        Bitmap bitmap = null;
        try {
            File f = new File(absolutePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Used to fetch the exif rotation for the image chosen from the gallery.
     * <p/>
     * Image captured from camera can be converted to different orientation while saving, hence to
     * get it vertical upright, the rotation angle is to be fetched.
     *
     * @param picturePath File picture whose rotation is to be checked
     * @return The rotation value for the image
     */
    private static int bitmapExifOrientation(String picturePath) {
        ExifInterface exifInterface;
        int rotation = 0;
        int orientation;
        try {
            exifInterface = new ExifInterface(picturePath);
            orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotation = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotation = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotation = 270;
                    break;
                default:
                    rotation = 0;
                    break;
            }
        } catch (IOException exception) {

        }
        return rotation;
    }

    /**
     * Used to calculate the file size from file path provided and provide that it is within size
     * limits if any
     *
     * @param activity    Activity in contention
     * @param file        File whose size is to be measured
     * @param fileSize    File size
     * @param fileSizeMin File size minimum
     * @param fileSizeMax File size maximum
     * @param isDoc       Is file size to be checked for document
     * @return Validity whether bitmap is of needed size
     */
    public static String calculateFileSize(Activity activity, File file, String fileSize,
                                           String fileSizeMin, String fileSizeMax, boolean isDoc) {
        String alphabetsPattern = "[^a-zA-Z]";
        String numericPattern = "[^0-9]";
        float fileSizeInBytes = file.length();
        float fileSizeInKB = fileSizeInBytes / 1024;
        float fileSizeInMB = fileSizeInKB / 1024;
        String calStringInKB = Float.toString(fileSizeInKB);
        double imageSizeInKB = Double.parseDouble(calStringInKB);
        String calStringInMB = Float.toString(fileSizeInMB);
        double imageSizeInMB = Double.parseDouble(calStringInMB);

        String fileTypeContent = isDoc ?
                activity.getString(R.string.error_filesize_type_document) :
                activity.getString(R.string.error_filesize_type_image);
        String sizePrefix = activity.getString(R.string.error_filesize_prefix) +
                " " + fileTypeContent + " ";

        if (!TextUtils.isEmpty(fileSizeMin)) {
            String isInvalid = getFileSizeValidation(sizePrefix,
                    fileSizeMin.replaceAll(alphabetsPattern, ""),
                    Double.parseDouble(fileSizeMin.replaceAll(numericPattern, "")),
                    imageSizeInKB, imageSizeInMB, fileSizeMin,
                    activity.getString(R.string.error_filesize_type_suffix_min),
                    activity.getString(R.string.error_filesize_suffix_min),
                    true);
            if (!TextUtils.isEmpty(isInvalid)) {
                return isInvalid;
            }
        }

        if (!TextUtils.isEmpty(fileSizeMax)) {
            String isInvalid = getFileSizeValidation(sizePrefix,
                    fileSizeMax.replaceAll(alphabetsPattern, ""),
                    Double.parseDouble(fileSizeMax.replaceAll(numericPattern, "")),
                    imageSizeInKB, imageSizeInMB, fileSizeMax,
                    activity.getString(R.string.error_filesize_type_suffix_max),
                    activity.getString(R.string.error_filesize_suffix_max),
                    false);
            if (!TextUtils.isEmpty(isInvalid)) {
                return isInvalid;
            }
        }

        if (!TextUtils.isEmpty(fileSize)) {
            String isInvalid = getFileSizeValidation(sizePrefix,
                    fileSize.replaceAll(alphabetsPattern, ""),
                    Double.parseDouble(fileSize.replaceAll(numericPattern, "")),
                    imageSizeInKB, imageSizeInMB, fileSize,
                    activity.getString(R.string.error_filesize_type_suffix_max),
                    activity.getString(R.string.error_filesize_suffix_max),
                    false);
            if (!TextUtils.isEmpty(isInvalid)) {
                return isInvalid;
            }
        }
        return "";
    }

    private static String getFileSizeValidation(String sizePrefix,
                                                String fileSizeFormat, double fileSizeValue,
                                                double imageSizeInKB, double imageSizeInMB,
                                                String size, String typeSuffix, String suffix,
                                                boolean isForMin) {
        boolean isSuccess;

        if (fileSizeFormat.equalsIgnoreCase("KB")) {
            isSuccess = isForMin ?
                    (imageSizeInKB < fileSizeValue) :
                    (imageSizeInKB > fileSizeValue);
        } else {
            isSuccess = isForMin ?
                    (imageSizeInMB < fileSizeValue) :
                    (imageSizeInMB > fileSizeValue);
        }
        if (!isSuccess) {
            return "";
        } else {
            return (sizePrefix + typeSuffix + " " + size + " " + suffix).trim();
        }
    }

    /**
     * Saves the pic bitmap for temporary usage for the size of the image
     *
     * @param pic Bitmap to be manipulated
     * @return Return the file stored
     */
    public static Uri savePicReturnUri(Bitmap pic, Context context) {
        Bitmap bitmap;
        OutputStream outputStream = null;
        bitmap = pic;

        try {
            File fileImage = createTempImageFile(context);
            outputStream = new FileOutputStream(fileImage);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            return Uri.parse(fileImage.getAbsolutePath());
        } catch (Exception exception) {
            Logger.Companion.e("UtilsImage savePicReturnUri", exception);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException exception) {
                Logger.Companion.e("UtilsImage savePicReturnUri", exception);
            }
        }
        return null;
    }

    public static File savepic(Bitmap pic, File file) {
        try (OutputStream output = new FileOutputStream(file)) {
            pic.compress(Bitmap.CompressFormat.JPEG, 100, output);
            return file;
        } catch (Exception exception) {
            Logger.Companion.e("UtilsImage savepic", exception);
        }
        return null;
    }

    public static Bitmap compressImage(File file) {

        String filePath = file.getAbsolutePath();
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scaledBitmap;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public static File getFileFromUriForImageUploading(String picUri, Context context) {
        Uri uri = Uri.parse(picUri);
        if (uri == null) {
            return null;
        }
        String fileName = uri.toString().substring(uri.toString().lastIndexOf("/")
                + 1);
        return new File(context.getExternalFilesDir(Environment
                .DIRECTORY_PICTURES) + FILE_SEPERATOR + fileName);
    }
}
