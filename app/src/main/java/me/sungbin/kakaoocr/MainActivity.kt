package me.sungbin.kakaoocr

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1000
        )

        val requestUrl = "https://dapi.kakao.com/v2/vision/text/ocr"

        btn_test.setOnClickListener {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image",
                    "20190413_205303.jpg",
                    File("/storage/emulated/0/Download/Jn6MZ.png").asRequestBody()
                ).build()

            val request = Request.Builder()
                .addHeader("Authorization", "KakaoAK 비밀")
                .addHeader("Accept-Charset", "UTF-8")
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Host", "dapi.kakao.com")
                .url(requestUrl).post(requestBody).build()

            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("AAAAA", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("AAAAA", "Response Body is " + response.body?.string())
                }
            })
        }
    }
}
