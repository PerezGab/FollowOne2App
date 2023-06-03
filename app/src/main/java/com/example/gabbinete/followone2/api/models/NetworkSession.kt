package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.Session
import com.example.gabbinete.followone2.domain.SessionEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSession(
    val date: String,
    val time: String
) : Parcelable {

    fun toDomainFp1() = Session(SessionEnum.FP1, date, time)
    fun toDomainFp2() = Session(SessionEnum.FP2, date, time)
    fun toDomainFp3() = Session(SessionEnum.FP3, date, time)
    fun toDomainQualy() = Session(SessionEnum.QUALY, date, time)
    fun toDomainSprint() = Session(SessionEnum.SPRINT, date, time)

}