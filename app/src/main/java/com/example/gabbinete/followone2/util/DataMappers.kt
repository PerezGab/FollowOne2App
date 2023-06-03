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