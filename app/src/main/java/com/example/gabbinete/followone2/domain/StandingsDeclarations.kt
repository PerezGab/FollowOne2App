package com.example.gabbinete.followone2.domain

abstract class Standings(
    open val position: String,
    open val positionText: String,
    open val points: String,
    open val wins: String,
)

class DriverStandings(
    position: String,
    positionText: String,
    points: String,
    wins: String,
    val driver: Driver,
    val constructors: List<Constructor>
) :
    Standings(
        position, positionText,
        points,
        wins
    ) {
    override val position: String
        get() = super.position
    override val positionText: String
        get() = super.positionText
    override val points: String
        get() = super.points
    override val wins: String
        get() = super.wins
}

class ConstructorStandings(
    position: String,
    positionText: String,
    points: String,
    wins: String,
    val constructor: Constructor
) : Standings(
    position, positionText,
    points, wins
) {
    override val position: String
        get() = super.position
    override val positionText: String
        get() = super.positionText
    override val points: String
        get() = super.points
    override val wins: String
        get() = super.wins
}