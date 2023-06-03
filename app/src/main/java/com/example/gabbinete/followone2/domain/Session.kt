package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val name: SessionEnum,
    val date: String,
    val time: String
): Parcelable

enum class SessionEnum {
    FP1,
    FP2,
    FP3,
    QUALY,
    SPRINT,
    RACE
}