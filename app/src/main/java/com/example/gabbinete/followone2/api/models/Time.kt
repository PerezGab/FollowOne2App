package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(val millis: String, val time: String) : Parcelable
