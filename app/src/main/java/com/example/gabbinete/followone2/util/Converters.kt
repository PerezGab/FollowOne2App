package com.example.gabbinete.followone2.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.NetworkSession
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.LocalCircuit
import com.example.gabbinete.followone2.database.entities.LocalConstructor
import com.example.gabbinete.followone2.database.entities.LocalDriver
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
    fun fromSessionToJson(session: NetworkSession?): String {
        return if (session !== null) Gson().toJson(session) else ""
    }

    @TypeConverter
    fun toSessionFromJson(json: String): NetworkSession? {
        return Gson().fromJson(json, NetworkSession::class.java)
    }

    // RaceResults List ============================================================================
    @TypeConverter
    fun fromRaceResultsToJson(results: List<NetworkRaceResult>?): String {
//        Log.d("RoomTypeConverter", "fromRaceResultsToJson is called with $results")
        return jsonParser.toJson(results, object : TypeToken<ArrayList<NetworkRaceResult>>() {}.type)
            ?: "[]"
    }

    @TypeConverter
    fun toRaceResultsFromJson(json: String): List<NetworkRaceResult> {
//        Log.d("RoomTypeConverter", "toRaceResultsFromJson is called with $json")
        return jsonParser.fromJson(json, object : TypeToken<ArrayList<NetworkRaceResult>>() {}.type)
            ?: emptyList()
    }

    // QualifyingResults List ======================================================================
    @TypeConverter
    fun fromQualifyingResultsToJson(results: List<QualifyingResult>?): String {
        return jsonParser.toJson(results, object : TypeToken<ArrayList<QualifyingResult>>() {}.type)
            ?: "[]"
    }

    @TypeConverter
    fun toQualifyingResultsFromJson(json: String): List<QualifyingResult> {
        return jsonParser.fromJson(json, object : TypeToken<ArrayList<QualifyingResult>>() {}.type)
            ?: emptyList()
    }

    // LocalDriver =================================================================================
    @TypeConverter
    fun fromLocalDriverToJson(driver: LocalDriver): String {
        return Gson().toJson(driver)
    }

    @TypeConverter
    fun toLocalDriverFromJson(json: String): LocalDriver {
        return Gson().fromJson(json, LocalDriver::class.java)
    }

    // LocalConstructor ============================================================================
    @TypeConverter
    fun fromLocalConstructorToJson(constructor: LocalConstructor): String {
        return Gson().toJson(constructor)
    }

    @TypeConverter
    fun toLocalConstructorFromJson(json: String): LocalConstructor {
        return Gson().fromJson(json, LocalConstructor::class.java)
    }

    // LocalConstructor List =======================================================================
    @TypeConverter
    fun fromLocalConstructorListToJson(constructors: List<LocalConstructor>): String {
        return jsonParser.toJson(
            constructors,
            object : TypeToken<ArrayList<LocalConstructor>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toLocalConstructorListFromJson(json: String): List<LocalConstructor> {
        return jsonParser.fromJson(json, object : TypeToken<ArrayList<LocalConstructor>>() {}.type)
            ?: emptyList()
    }

    // Circuit =====================================================================================
    @TypeConverter
    fun fromCircuitToJson(circuit: LocalCircuit?): String {
        return Gson().toJson(circuit)
    }

    @TypeConverter
    fun toCircuitFromJson(json: String): LocalCircuit {
        return Gson().fromJson(json, LocalCircuit::class.java)
    }
}

