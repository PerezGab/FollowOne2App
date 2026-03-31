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
            "Bahrain Grand Prix" -> "BAHRAIN GRAND PRIX"
            "Saudi Arabian Grand Prix" -> "SAUDI ARABIAN GRAND PRIX"
            "Australian Grand Prix" -> "AUSTRALIAN GRAND PRIX"
            "Azerbaijan Grand Prix" -> "AZERBAIJAN GRAND PRIX"
            "Miami Grand Prix" -> "MIAMI GRAND PRIX"
            "Emilia Romagna Grand Prix" -> "GRAN PREMIO DEL MADE IN ITALY E DELL'EMILIA-ROMAGNA"
            "Monaco Grand Prix" -> "GRAND PRIX DE MONACO"
            "Spanish Grand Prix" -> "GRAN PREMIO DE ESPAÑA"
            "Canadian Grand Prix" -> "GRAND PRIX DU CANADA"
            "Austrian Grand Prix" -> "AUSTRIAN GRAND PRIX"
            "British Grand Prix" -> "BRITISH GRAND PRIX"
            "Hungarian Grand Prix" -> "HUNGARIAN GRAND PRIX"
            "Belgian Grand Prix" -> "BELGIAN GRAND PRIX"
            "Dutch Grand Prix" -> "DUTCH GRAND PRIX"
            "Italian Grand Prix" -> "GRAN PREMIO D’ITALIA"
            "Singapore Grand Prix" -> "SINGAPORE GRAND PRIX"
            "Japanese Grand Prix" -> "JAPANESE GRAND PRIX"
            "Qatar Grand Prix" -> "QATAR GRAND PRIX"
            "United States Grand Prix" -> "UNITED STATES GRAND PRIX"
            "Mexico City Grand Prix" -> "GRAN PREMIO DE LA CIUDAD DE MÉXICO"
            "Brazilian Grand Prix" -> "GRANDE PRÊMIO DE SÃO PAULO"
            "Las Vegas Grand Prix" -> "LAS VEGAS GRAND PRIX"
            "Abu Dhabi Grand Prix" -> "ABU DHABI GRAND PRIX"
            "Chinese Grand Prix" -> "CHINESE GRAND PRIX"
            "Barcelona Grand Prix" -> "GRAN PREMIO DE BARCELONA-CATALUNYA"
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



