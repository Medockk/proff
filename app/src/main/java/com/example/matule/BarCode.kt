package com.example.matule

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Toast
import androidx.compose.ui.unit.Dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

class BarCode {

    fun createBarCode(barCodeText: String, width: Dp, height: Dp):Bitmap?{
        if (barCodeText.isNotEmpty()){
            val mwriter = MultiFormatWriter()
            try {
                val matrix = mwriter.encode(barCodeText, BarcodeFormat.CODE_128, width.value.toInt(), height.value.toInt())
                val bitmap = Bitmap.createBitmap(width.value.toInt(), height.value.toInt(), Bitmap.Config.RGB_565)
                for (i in 0 until width.value.toInt()) {
                    for (j in 0 until height.value.toInt()) {
                        bitmap.setPixel(i, j, if (matrix[i, j]) Color.BLACK else Color.WHITE)
                    }
                }
                return bitmap
            }catch (ex: Exception){

            }
        }
        return null
    }
}