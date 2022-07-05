package com.project.kakaoaddressapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class WebViewActivity : AppCompatActivity() {

    private var browser: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        browser = findViewById(R.id.webView)
        browser?.settings?.javaScriptEnabled = true

        browser?.addJavascriptInterface(MyJavaScriptInterface(), "Android")

        browser?.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
//안드로이드에서 자바스크립트 메서드 호출
                browser?.loadUrl("javascript:sample2_execDaumPostcode();")
            }
        })
//최초로 웹뷰 로딩
        browser?.loadUrl("")
    }

    inner class MyJavaScriptInterface {
        @JavascriptInterface
        fun processDATA(data: String?) {
            //자바 스크립트로 부터 다음 카카오 주소 검색 API 결과를 전달 받는다.
            val extra = Bundle()
            val intent = Intent()
            extra.putString("data", data)
            intent.putExtras(extra)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}