package com.tanamo.blog


import android.app.Application
import com.google.firebase.database.FirebaseDatabase


class Tanamo : Application() {


    override fun onCreate() {
        super.onCreate()
        //     Fabric.with(this, Crashlytics())
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)


    }


}