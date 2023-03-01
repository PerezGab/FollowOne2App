package com.example.gabbinete.followone2.util

import com.example.gabbinete.followone2.api.models.*
import com.example.gabbinete.followone2.database.entities.*
import com.example.gabbinete.followone2.domain.Constructor
import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix

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
    return map { it.toLocalGrandPrix() }
}

//fun List<DriverStandingList>.toDomainDriverStandingList(): List<SeasonStandings> {
//    return map {
//        SeasonStandings(
//            season = it.season,
//            round = it.round,
//            standings = it.driverStandings.toDomainDriverStandings()
//        )
//    }
//}

//fun List<ConstructorStandingList>.toDomainConstructorStandingList(): List<SeasonStandings> {
//    return map {
//        SeasonStandings(
//            season = it.season,
//            round = it.round,
//            standings = it.constructorStandings.toDomainConstructorStandings()
//        )
//    }
//}

