package com.example.gabbinete.followone2.util

import com.example.gabbinete.followone2.api.models.NetworkConstructor
import com.example.gabbinete.followone2.api.models.NetworkConstructorStanding
import com.example.gabbinete.followone2.api.models.NetworkDriver
import com.example.gabbinete.followone2.api.models.NetworkDriverStanding
import com.example.gabbinete.followone2.api.models.NetworkGrandPrix
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.database.entities.LocalConstructor
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.domain.Constructor
import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.domain.RaceResults
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun List<LocalDriverStandings>.toDomainDriverStandings(): List<DriverStandings> {
    return map { it.toDomainDriverStandings() }
}

fun List<LocalConstructorStandings>.toDomainConstructorStandings(): List<ConstructorStandings> {
    return map { it.toDomainConstructorStandings() }
}

fun List<LocalConstructor>.toDomainConstructor(): List<Constructor> {
    return map { it.toDomainConstructor() }
}

fun List<LocalGrandPrix>.toDomainGrandPrix(): List<GrandPrix> {
    return map { it.toDomainGrandPrix() }
}

fun List<NetworkDriver>.toLocalDriver(): List<LocalDriver> {
    return map { it.toLocalDriver() }
}

fun List<NetworkConstructor>.toLocalConstructorList(): List<LocalConstructor> {
    return map { it.toLocalConstructor() }
}

fun List<NetworkDriverStanding>.toLocalDriverStandings(): List<LocalDriverStandings> {
    return map { it.toLocalDriverStandings() }
}

fun List<NetworkConstructorStanding>.toLocalConstructorStandings(): List<LocalConstructorStandings> {
    return map { it.toLocalConstructorStanding() }
}

fun List<NetworkGrandPrix>.toLocalGrandPrixList(): List<LocalGrandPrix> {
//    this.forEach { println(it) }
    return map { it.toLocalGrandPrix() }
}

fun List<NetworkRaceResult>.toDomainRaceResultsList(): List<RaceResults> {
    return map { it.toDomainRaceResult() }
}

fun String.formatDate(): String {
        val formatParser = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateParsed = LocalDate.parse(this@formatDate, formatParser)
        val result = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return dateParsed.format(result)
    }

fun String.formatTime(): String {
        val formatterParser = DateTimeFormatter.ofPattern("HH:mm:ss'Z'")
        val formatterResult = DateTimeFormatter.ofPattern("HH:mm")
        val timeResult = LocalTime.parse(this@formatTime, formatterParser).format(formatterResult)
        return timeResult + " HS"
    }

fun String.formatToSeconds(): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return LocalDateTime.parse(this, formatter).toEpochSecond(ZoneOffset.UTC)
}

fun countdownFormatter(gpTime: Long): String {
    val currentTime = Instant.now().epochSecond
    val countdownValueSeconds = gpTime - currentTime
    val countdownValueMinutes = ((countdownValueSeconds / 60) % 60)
    val countdownValueHours = ((countdownValueSeconds / (60 * 60)) % 24)
    val countdownValueDays = (((countdownValueSeconds / 60) / 60) / 24)
    return if (countdownValueSeconds < 0) "Already started." else when {
        countdownValueDays > 1 -> "$countdownValueDays days ${countdownValueHours}hs ${countdownValueMinutes}min left"
        countdownValueDays.toInt() == 1 -> "$countdownValueDays day ${countdownValueHours}hs ${countdownValueMinutes}min left"
        else -> when {
            countdownValueHours > 1 -> "$countdownValueHours hours ${countdownValueMinutes}min left"
            countdownValueHours.toInt() == 1 -> "$countdownValueHours hour ${countdownValueMinutes}min left"
            else -> "$countdownValueMinutes minutes left"
        }
    }
}