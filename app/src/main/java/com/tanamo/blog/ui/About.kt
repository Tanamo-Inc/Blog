package com.tanamo.blog.ui

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

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.tanamo.blog.R
import com.tanamo.blog.mod.Kons.getAppName
import com.tanamo.blog.mod.Kons.getAppVersionName

import kotlinx.android.synthetic.main.about.view.*

/**
 * Created by ${TANDOH} on ${6/20/2017}.
 */

class About(private val mCtx: Context) {

    fun show() {
        val factory = LayoutInflater.from(mCtx)

        @SuppressLint("InflateParams")
        val dialogView = factory.inflate(R.layout.about, null)


        innerUpdate(dialogView)

        val adBuilder = AlertDialog.Builder(mCtx)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .setView(dialogView)

        adBuilder.show()

    }

    @SuppressLint("SetTextI18n")
    private fun innerUpdate(dialogView: View) {

        // app name & version
        val appText = "${getAppName(mCtx, mCtx.packageName)} v ${getAppVersionName(mCtx, mCtx.packageName)}"
        dialogView.app_name.text = appText

        // author
        dialogView.author.text = "Developed by:${mCtx.getString(R.string.author)}"

        // text
        dialogView.needs.setText(R.string.about_text)

        // website
        dialogView.website.text = "${mCtx.getString(R.string.website)}:\n${mCtx.getString(R.string.website_url)}"

        // Github
        dialogView.bugs.text = "${mCtx.getString(R.string.github)}:\n${mCtx.getString(R.string.github_link)}"

        // face
        dialogView.face.text = "${mCtx.getString(R.string.face)}:\n${mCtx.getString(R.string.face_url)}"


    }

}
