package com.example.gabbinete.followone2.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.api.models.RaceResult
import com.example.gabbinete.followone2.api.models.Session
import com.example.gabbinete.followone2.domain.Constructor
import com.example.gabbinete.followone2.domain.Driver
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    // Constructor =================================================================================
    @TypeConverter
    fun toConstructorFromJson(json: String): Constructor {
        return Gson().fromJson(json, Constructor::class.java)
    }

    @TypeConverter
    fun fromConstructorToJson(constructor: Constructor): String {
        return Gson().toJson(constructor)
    }

    // Constructor List ============================================================================
    @TypeConverter
    fun toListConstructorsFromJson(json: String): List<Constructor> {
        return jsonParser.fromJson<ArrayList<Constructor>>(
            json, object : TypeToken<ArrayList<Constructor>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromListConstructorToJson(constructors: List<Constructor>): String {
        return jsonParser.toJson(constructors, object : TypeToken<ArrayList<Constructor>>() {}.type)
            ?: "[]"
    }

    // Driver ======================================================================================
    @TypeConverter
    fun fromDriverToJson(driver: Driver): String? {
        return Gson().toJson(driver)
    }

    @TypeConverter
    fun toDriverFromJson(json: String): Driver? {
        return Gson().fromJson(json, Driver::class.java)
    }

    // Session =====================================================================================
    @TypeConverter
    fun fromSessionToJson(session: Session): String {
        return Gson().toJson(session)
    }

    @TypeConverter
    fun toSessionFromJson(json: String): Session {
        return Gson().fromJson(json, Session::class.java)
    }

    // RaceResults List ============================================================================
    @TypeConverter
    fun fromRaceResultsToJson(results: List<RaceResult>): String {
        return jsonParser.toJson(results, object : TypeToken<ArrayList<RaceResult>>() {}.type)
            ?: "[]"
    }

    @TypeConverter
    fun toRaceResultsFromJson(json: String): List<RaceResult> {
        return jsonParser.fromJson(json, object : TypeToken<ArrayList<RaceResult>>() {}.type)
            ?: emptyList()
    }

    // QualifyingResults List ======================================================================
    @TypeConverter
    fun fromQualifyingResultsToJson(results: List<QualifyingResult>): String {
        return jsonParser.toJson(results, object : TypeToken<ArrayList<QualifyingResult>>() {}.type)
            ?: "[]"
    }

    @TypeConverter
    fun toQualifyingResultsFromJson(json: String): List<QualifyingResult> {
        return jsonParser.fromJson(json, object : TypeToken<ArrayList<QualifyingResult>>() {}.type)
            ?: emptyList()
    }
}

