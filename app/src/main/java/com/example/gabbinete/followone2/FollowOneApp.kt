package com.example.gabbinete.followone2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FollowOneApp : Application()
//    Configuration.Provider
//{
//
//    @Inject
//    lateinit var workerFactory: HiltWorkerFactory
//
//    override fun getWorkManagerConfiguration(): Configuration =
//        Configuration.Builder().setWorkerFactory(workerFactory).build()
//
//}