package com.example.gabbinete.followone2.util

import com.example.gabbinete.followone2.api.models.*
import com.example.gabbinete.followone2.database.entities.LocalConstructor
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.domain.*

@JvmName("toDomainDriverStandingsLocalDriverStandings")
fun List<LocalDriverStandings>.toDomainDriverStandings(): List<DriverStandings> {
    return map {
        DriverStandings(
            position = it.position,
            positionText = it.positionText,
            points = it.points,
            wins = it.wins,
            driver = it.localDriver.toDomainDriver(),
            constructors = it.localConstructors.toDomainConstructor()
        )
    }
}

@JvmName("toDomainConstructorStandingsLocalConstructorStandings")
fun List<LocalConstructorStandings>.toDomainConstructorStandings(): List<ConstructorStandings> {
    return map {
        ConstructorStandings(
            points = it.points,
            position = it.position,
            positionText = it.positionText,
            wins = it.wins,
            constructor = it.constructor.toDomainConstructor()
        )
    }
}

@JvmName("toDomainConstructorLocalConstructor")
fun List<LocalConstructor>.toDomainConstructor(): List<Constructor> {
    return map {
        Constructor(
            constructorId = it.constructorId,
            url = it.url,
            name = it.name,
            nationality = it.nationality
        )
    }
}

fun List<LocalGrandPrix>.toDomainGrandPrix(): List<GrandPrix> {
    return map {
        GrandPrix(
            season = it.season,
            round = it.round,
            url = it.url,
            raceName = it.raceName,
            circuit = it.circuit,
            date = it.date,
            time = it.time,
            raceResults = it.raceResults,
            qualifying = it.qualifying,
            qualifyingResults = it.qualifyingResults,
            firstPractice = it.firstPractice,
            secondPractice = it.secondPractice,
            thirdPractice = it.thirdPractice,
            sprint = it.sprint
        )
    }
}

fun List<NetworkDriver>.toDomainDriver(): List<Driver> {
    return map {
        Driver(
            driverId = it.driverId,
            permanentNumber = it.permanentNumber,
            code = it.code,
            url = it.url,
            givenName = it.givenName,
            familyName = it.familyName,
            dateOfBirth = it.dateOfBirth,
            nationality = it.nationality
        )
    }
}

fun List<NetworkConstructor>.toDomainConstructor(): List<Constructor> {
    return map {
        Constructor(
            constructorId = it.constructorId,
            url = it.url,
            name = it.name,
            nationality = it.nationality
        )
    }
}

fun List<NetworkDriverStanding>.toDomainDriverStandings(): List<DriverStandings> {
    return map {
        DriverStandings(
            position = it.position,
            positionText = it.positionText,
            points = it.points,
            wins = it.wins,
            driver = it.networkDriver.toDomainDriver(),
            constructors = it.constructors.toDomainConstructor()
        )
    }
}

fun List<NetworkConstructorStanding>.toDomainConstructorStandings(): List<ConstructorStandings> {
    return map {
        ConstructorStandings(
            position = it.position,
            positionText = it.positionText,
            points = it.points,
            wins = it.wins,
            constructor = it.constructor.toDomainConstructor()
        )
    }
}

fun List<DriverStandingList>.toDomainDriverStandingList(): List<SeasonStandings> {
    return map {
        SeasonStandings(
            season = it.season,
            round = it.round,
            standings = it.driverStandings.toDomainDriverStandings()
        )
    }
}

fun List<ConstructorStandingList>.toDomainConstructorStandingList(): List<SeasonStandings> {
    return map {
        SeasonStandings(
            season = it.season,
            round = it.round,
            standings = it.constructorStandings.toDomainConstructorStandings()
        )
    }
}

fun List<GrandPrix>.toLocalGrandPrixList(): List<LocalGrandPrix> {
    return map { it.toLocalGrandPrix() }
}

fun List<NetworkGrandPrix>.toDomainGrandPrixList(): List<GrandPrix> {
    return map {
        GrandPrix(
            season = it.season,
            round = it.round,
            url = it.url,
            raceName = it.raceName,
            circuit = it.circuit.toDomainCircuit(),
            date = it.date,
            time = it.time,
            raceResults = it.raceResults,
            qualifyingResults = it.qualifyingResults,
            firstPractice = it.firstPractice,
            secondPractice = it.secondPractice,
            thirdPractice = it.thirdPractice,
            qualifying = it.qualifying,
            sprint = it.sprint
        )
    }
}