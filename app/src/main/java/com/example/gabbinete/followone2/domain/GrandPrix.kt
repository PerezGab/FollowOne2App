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
            "Bahrain Grand Prix" -> "GULF AIR BAHRAIN GRAND PRIX"
            "Saudi Arabian Grand Prix" -> "STC SAUDI ARABIAN GRAND PRIX"
            "Australian Grand Prix" -> "ROLEX AUSTRALIAN GRAND PRIX"
            "Azerbaijan Grand Prix" -> "QATAR AIRWAYS AZERBAIJAN GRAND PRIX"
            "Miami Grand Prix" -> "CRYPTO.COM MIAMI GRAND PRIX"
            "Emilia Romagna Grand Prix" -> "AWS GRAN PREMIO DEL MADE IN ITALY E DELL'EMILIA-ROMAGNA"
            "Monaco Grand Prix" -> "TAG HEUER GRAND PRIX DE MONACO"
            "Spanish Grand Prix" -> "ARAMCO GRAN PREMIO DE ESPAÑA"
            "Canadian Grand Prix" -> "PIRELLI GRAND PRIX DU CANADA"
            "Austrian Grand Prix" -> "MSC CRUISES AUSTRIAN GRAND PRIX"
            "British Grand Prix" -> "QATAR AIRWAYS BRITISH GRAND PRIX"
            "Hungarian Grand Prix" -> "LENOVO HUNGARIAN GRAND PRIX"
            "Belgian Grand Prix" -> "MOËT & CHANDON BELGIAN GRAND PRIX"
            "Dutch Grand Prix" -> "HEINEKEN DUTCH GRAND PRIX"
            "Italian Grand Prix" -> "PIRELLI GRAN PREMIO D’ITALIA"
            "Singapore Grand Prix" -> "SINGAPORE AIRLINES SINGAPORE GRAND PRIX"
            "Japanese Grand Prix" -> "LENOVO JAPANESE GRAND PRIX"
            "Qatar Grand Prix" -> "QATAR AIRWAYS QATAR GRAND PRIX"
            "United States Grand Prix" -> "MSC CRUISES UNITED STATES GRAND PRIX"
            "Mexico City Grand Prix" -> "GRAN PREMIO DE LA CIUDAD DE MÉXICO"
            "São Paulo Grand Prix" -> "MSC CRUISES GRANDE PRÊMIO DE SÃO PAULO"
            "Las Vegas Grand Prix" -> "HEINEKEN LAS VEGAS GRAND PRIX"
            "Abu Dhabi Grand Prix" -> "ETIHAD AIRWAYS ABU DHABI GRAND PRIX"
            "Chinese Grand Prix" -> "HEINEKEN CHINESE GRAND PRIX"
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



