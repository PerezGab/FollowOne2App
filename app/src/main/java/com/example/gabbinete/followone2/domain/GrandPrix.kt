package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import com.example.gabbinete.followone2.api.models.QualifyingResult
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class GrandPrix(
    val season: String,
    val round: String,
    val url: String?,
    val raceName: String,
    val circuit: Circuit?,
    val date: String?,
    val time: String?,
    val raceResults: List<RaceResults>?,
    val qualifyingResults: List<QualifyingResult>?,
    val firstPractice: Session?,
    val secondPractice: Session?,
    val thirdPractice: Session?,
    val qualifying: Session?,
    val sprint: Session?
): Parcelable {

    @IgnoredOnParcel
    val race: Session = Session(
        SessionEnum.RACE,
        date ?: "", time ?: ""
    )

    @IgnoredOnParcel
    val formalTitleName: String =
        when (raceName) {
            "Bahrain Grand Prix" -> "GULF AIR BAHRAIN GRAND PRIX 2023"
            "Saudi Arabian Grand Prix" -> "STC SAUDI ARABIAN GRAND PRIX 2023"
            "Australian Grand Prix" -> "ROLEX AUSTRALIAN GRAND PRIX 2023"
            "Azerbaijan Grand Prix" -> "AZERBAIJAN GRAND PRIX 2023"
            "Miami Grand Prix" -> "CRYPTO.COM MIAMI GRAND PRIX 2023"
            "Emilia Romagna Grand Prix" -> "QATAR AIRWAYS GRAN PREMIO DEL MADE IN ITALY E DELL'EMILIA-ROMAGNA 2023"
            "Monaco Grand Prix" -> "GRAND PRIX DE MONACO 2023"
            "Spanish Grand Prix" -> "AWS GRAN PREMIO DE ESPAÑA 2023"
            "Canadian Grand Prix" -> "PIRELLI GRAND PRIX DU CANADA 2023"
            "Austrian Grand Prix" -> "GROSSER PREIS VON ÖSTERREICH 2023"
            "British Grand Prix" -> "ARAMCO BRITISH GRAND PRIX 2023"
            "Hungarian Grand Prix" -> "QATAR AIRWAYS HUNGARIAN GRAND PRIX 2023"
            "Belgian Grand Prix" -> "BELGIAN GRAND PRIX 2023"
            "Dutch Grand Prix" -> "HEINEKEN DUTCH GRAND PRIX 2023"
            "Italian Grand Prix" -> "PIRELLI GRAN PREMIO D’ITALIA 2023"
            "Singapore Grand Prix" -> "SINGAPORE AIRLINES SINGAPORE GRAND PRIX 2023"
            "Japanese Grand Prix" -> "LENOVO JAPANESE GRAND PRIX 2023"
            "Qatar Grand Prix" -> "QATAR AIRWAYS QATAR GRAND PRIX 2023"
            "United States Grand Prix" -> "LENOVO UNITED STATES GRAND PRIX 2023"
            "Mexico City Grand Prix" -> "GRAN PREMIO DE LA CIUDAD DE MÉXICO 2023"
            "São Paulo Grand Prix" -> "ROLEX GRANDE PRÊMIO DE SÃO PAULO 2023"
            "Las Vegas Grand Prix" -> "HEINEKEN SILVER LAS VEGAS GRAND PRIX 2023"
            "Abu Dhabi Grand Prix" -> "ETIHAD AIRWAYS ABU DHABI GRAND PRIX 2023"
            else -> ""
        }

    companion object {
        fun postSeason(): GrandPrix {
            return GrandPrix(
                "",
                "",
                null,
                "Season has finished",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        }
    }
}



