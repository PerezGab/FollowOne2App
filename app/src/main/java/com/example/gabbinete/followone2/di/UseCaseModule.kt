package com.example.gabbinete.followone2.di

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.GetByIdUseCase
import com.example.gabbinete.followone2.use_case.GetConstructorStandingsUseCase
import com.example.gabbinete.followone2.use_case.GetDriverByIdUseCase
import com.example.gabbinete.followone2.use_case.GetDriverStandingsUseCase
import com.example.gabbinete.followone2.use_case.GetLastRaceUseCase
import com.example.gabbinete.followone2.use_case.GetRaceByRoundUseCase
import com.example.gabbinete.followone2.use_case.GetSeasonRacesUseCase
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.use_case.IsConditionUseCase
import com.example.gabbinete.followone2.use_case.IsDataStoredUseCase
import com.example.gabbinete.followone2.use_case.OnDataStoredCompletedUseCase
import com.example.gabbinete.followone2.use_case.OnEventUseCase
import com.example.gabbinete.followone2.use_case.OnStartUpUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Named("getSeasonRacesUseCase")
    fun bindGetSeasonRacesUseCase(getSeasonRacesUseCase: GetSeasonRacesUseCase): GetTablesUseCase<GrandPrix>

    @Binds
    @Named("getLastRaceUseCase")
    fun bindGetLastRaceUseCase(getLastRaceUseCase: GetLastRaceUseCase): GetTablesUseCase<GrandPrix>

    @Binds
    fun bindGetDriverStandingsUseCase(getDriverStandingsUseCase: GetDriverStandingsUseCase): GetTablesUseCase<DriverStandings>

    @Binds
    fun bindGetConstructorStandingsUseCase(getConstructorStandingsUseCase: GetConstructorStandingsUseCase): GetTablesUseCase<ConstructorStandings>

    @Binds
    fun bindIsDataStoredUseCase(isDataStoredUseCase: IsDataStoredUseCase): IsConditionUseCase<Boolean>

    @Binds
    fun bindGetRaceResultsByRoundUseCase(getRaceByRoundUseCase: GetRaceByRoundUseCase): GetByIdUseCase<GrandPrix>

    @Binds
    fun bindGetDriverProfileByNameUseCase(getDriverProfileByIdUseCase: GetDriverByIdUseCase): GetByIdUseCase<Driver>

    @Binds
    @Named("onStartUp")
    fun bindOnStartUpUseCase(onStartUpUseCase: OnStartUpUseCase): OnEventUseCase

    @Binds
    @Named("onDataStoredCompleted")
    fun bindOnDataStoredCompletedUseCase(onDataStoredCompletedUseCase: OnDataStoredCompletedUseCase): OnEventUseCase
}