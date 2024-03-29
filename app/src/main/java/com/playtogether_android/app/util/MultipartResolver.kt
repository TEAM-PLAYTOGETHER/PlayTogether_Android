package com.playtogether_android.app.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface
import dagger.hilt.android.qualifiers.ActivityContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.inject.Inject

class MultiPartResolver @Inject constructor(
    @ActivityContext private val context: Context
) {
    fun createImgMultiPart(uri: Uri): MultipartBody.Part {
        var resizedWidth = RESIZED_SIZE
        var resizedHeight = RESIZED_SIZE
        val options = BitmapFactory.Options()
        val inputStream = context.contentResolver.openInputStream(uri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        getRotatedBitmap(
            BitmapFactory.decodeStream(inputStream, null, options),
            getOrientationOfImage(uri)
        )?.let {
            if (it.width >= RESIZED_SIZE && it.height >= RESIZED_SIZE) {
                if (it.width >= it.height) {
                    resizedWidth = resizedHeight * (it.width.toFloat() / it.height.toFloat())
                } else {
                    resizedHeight = resizedWidth * (it.height.toFloat() / it.width.toFloat())
                }
            } else {
                resizedWidth = it.width.toFloat()
                resizedHeight = it.height.toFloat()
            }
            Bitmap.createScaledBitmap(it, resizedWidth.toInt(), resizedHeight.toInt(), true)
                .compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        }
        val file = File(replaceFileName(uri.toString()))
        val surveyBody =
            byteArrayOutputStream.toByteArray().toRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, surveyBody)
    }

    fun createImgMultiPart(bitmap: Bitmap): MultipartBody.Part {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val file = File(replaceFileName(bitmap.toString()))
        val surveyBody =
            byteArrayOutputStream.toByteArray().toRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, surveyBody)
    }

    private fun getOrientationOfImage(uri: Uri): Int {
        // uri -> inputStream
        val inputStream =  context.contentResolver.openInputStream(uri)
        val exif: ExifInterface? = try {
            ExifInterface(inputStream!!)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }
        inputStream.close()

        // 회전된 각도 알아내기
        val orientation = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        if (orientation != -1) {
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return 90
                ExifInterface.ORIENTATION_ROTATE_180 -> return 180
                ExifInterface.ORIENTATION_ROTATE_270 -> return 270
            }
        }
        return 0
    }

    @Throws(Exception::class)
    fun getRotatedBitmap(bitmap: Bitmap?, degrees: Int): Bitmap? {
        if (bitmap == null) return null
        if (degrees == 0) return bitmap
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun getPathFromUri(uri: Uri): String? {
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.moveToNext()
        val columnIndex = cursor?.getColumnIndex("_data") ?: return null
        val path =
            cursor.getString(columnIndex)

        cursor.close()
        return path
    }

    private fun replaceFileName(fileName: String): String = "${fileName.replace(".", "_")}.jpeg"

    companion object {
        private const val RESIZED_SIZE = 720f
    }
}
