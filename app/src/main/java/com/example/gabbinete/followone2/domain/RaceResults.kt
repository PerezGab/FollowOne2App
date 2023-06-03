package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import com.example.gabbinete.followone2.api.models.FastestLap
import com.example.gabbinete.followone2.api.models.Time
import kotlinx.parcelize.Parcelize

@Parcelize
data class RaceResults(
    val number: String,
    val position: String,
    val positionText: String,
    val points: String,
    val driver: Driver,
    val constructor: Constructor,
    val grid: String,
    val laps: String,
    val status: String,
    val time: Time?,
    val fastestLap: FastestLap?
): Parcelable