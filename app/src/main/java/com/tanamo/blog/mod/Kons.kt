package com.tanamo.blog.mod

/*
 * Copyright (C) 2016 Tanamo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast

/**
 * Created by ${TANDOH} on ${6/20/2017}.
 */
object Kons {

    const val COUNTS = 3000
    const val TAG = "tanamo"

    fun suV(code: () -> Unit) {
        sVersion(code, 21)
    }

    private fun sVersion(code: () -> Unit, sdk: Int) {
        if (Build.VERSION.SDK_INT >= sdk) {
            code.invoke()
        }
    }

    fun getAppName(ctx: Context, pkgName: String): String {
        return try {
            val pm = ctx.packageManager
            val appInfo = pm.getApplicationInfo(pkgName, 0)
            val label = pm.getApplicationLabel(appInfo).toString()
            label
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }

    }

    fun getAppVersionName(ctx: Context, pkgName: String): String {
        return try {
            val pm = ctx.packageManager
            val pkgInfo = pm.getPackageInfo(pkgName, 0)
            val ver = pkgInfo.versionName
            ver
        } catch (e: PackageManager.NameNotFoundException) {
            "0"
        }

    }

    fun initToast(c: Context, message: String) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }

    fun connect(context: Context): Boolean {
        val conec: Boolean
        val conectivtyManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        conec = conectivtyManager.activeNetworkInfo != null
                && conectivtyManager.activeNetworkInfo.isAvailable
                && conectivtyManager.activeNetworkInfo.isConnected
        return conec
    }


}
