package com.tanamo.blog.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tanamo.blog.R
import com.tanamo.blog.mod.Kons
import com.tanamo.blog.mod.Kons.initToast
import kotlinx.android.synthetic.main.details.*

@SuppressLint("NewApi")
class Details : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.details)

        init()

    }

    fun init() {

        setSupportActionBar(toolbar)

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                this@Details.setProgress(progress * 100)

                if (progress == 100) {

                }

            }
        }

        if (!Kons.connect(this)) {
            //AlertDialog Method
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle(getString(R.string.app_name))
            alertDialog.setMessage(getString(R.string.connect))
            alertDialog.setButton("Ok") { _, _ -> finish() }
            alertDialog.setIcon(R.drawable.ic_launcher)
            alertDialog.show()


        } else {

            val bun = intent.extras
            val myURL = bun.getString(getString(R.string.tan_key))

            startWebView(myURL)

        }


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startWebView(url: String) {

        webView.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            // Show loader on url load
            override fun onLoadResource(view: WebView, url: String) {


            }

            override fun onPageFinished(view: WebView, url: String) {


            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                initToast(applicationContext, "Error!!! Pls Try Again")
                finish()

            }
        }

        val setd = webView.settings
        setd.javaScriptEnabled = true
        setd.domStorageEnabled = true
        webView.settings.setAppCacheEnabled(true)
        webView.settings.saveFormData = true
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.loadUrl(url)

    }

    override fun onStop() {
        super.onStop()
        webView.stopLoading()
    }


}