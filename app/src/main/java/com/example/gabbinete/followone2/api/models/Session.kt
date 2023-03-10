package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val date: String,
    val time: String
) : Parcelable