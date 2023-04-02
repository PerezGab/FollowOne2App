package com.example.gabbinete.followone2.di

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Named("getSeasonRaces")
    fun getSeasonRacesUseCaseBinder(getSeasonRacesUseCase: GetSeasonRacesUseCase): GetTablesUseCase<GrandPrix>

    @Binds
    @Named("getLastRace")
    fun getLastRaceUseCaseBinder(getLastRaceUseCase: GetLastRaceUseCase): GetTablesUseCase<GrandPrix>

    @Binds
    fun getDriverStandingsUseCaseBinder(getDriverStandingsUseCase: GetDriverStandingsUseCase): GetTablesUseCase<DriverStandings>

    @Binds
    fun getConstructorStandingsUseCaseBinder(getConstructorStandingsUseCase: GetConstructorStandingsUseCase): GetTablesUseCase<ConstructorStandings>

    @Binds
    fun isDataStoredUseCaseBinder(isDataStoredUseCase: IsDataStoredUseCase): IsConditionUseCase<Boolean>
}