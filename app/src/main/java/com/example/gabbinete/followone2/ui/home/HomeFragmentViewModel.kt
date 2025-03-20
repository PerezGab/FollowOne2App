package com.example.gabbinete.followone2.ui.home

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.domain.RaceResults
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.use_case.IsConditionUseCase
import com.example.gabbinete.followone2.util.Constants.Companion.ONE_MINUTE
import com.example.gabbinete.followone2.util.Constants.Companion.ONE_SECOND
import com.example.gabbinete.followone2.util.Response
import com.example.gabbinete.followone2.util.countdownFormatter
import com.example.gabbinete.followone2.util.formatDate
import com.example.gabbinete.followone2.util.formatToSeconds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "HomeFragmentViewModel"

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    @Named("getSeasonRacesUseCase") private val getSeasonRacesUseCase: GetTablesUseCase<GrandPrix>,
    @Named("getLastRaceUseCase") private val getLastRaceUseCase: GetTablesUseCase<GrandPrix>,
    private val isDataStoredUseCase: IsConditionUseCase<Boolean>,
//    private val workManager: WorkManager
) : ViewModel() {

    private lateinit var timer:CountDownTimer

    private var _state = MutableStateFlow(
        HomeFragmentState(
            null,
            null,
            true,
            "",
            null,
            "",
            false
        )
    )
    val state = _state.asStateFlow()

//    val workInfo = workManager.getWorkInfosForUniqueWorkLiveData(INSTANT_WORK_NAME)

    init {
        Log.d(TAG, "init block")
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModelScope.launch {
            isDataStoredUseCase().collect {
                if (it) {
                    Log.d(TAG, "isDataStoredUseCase is $it")
                    setupLastRace()
                    setupNextRace()
//                    startWorker()
                }
            }
        }
    }

//    private fun startWorker() {
//        Log.d(TAG, "startWorker() is being called. Building workRequest and periodic work...")
//        val workRequest = PeriodicWorkRequestBuilder<InstantWorker>(
//            60, TimeUnit.SECONDS
//        ).setBackoffCriteria(BackoffPolicy.LINEAR, Duration.ofSeconds(15))
//            .build()
//
//        workManager.enqueueUniquePeriodicWork(
//            INSTANT_WORK_NAME,
//            ExistingPeriodicWorkPolicy.KEEP,
//            workRequest
//        )
//    }

    private fun setupLastRace() {
        Log.d(TAG, "setupLastRace is called.")
        viewModelScope.launch {
            getLastRaceUseCase(false).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        Log.d(TAG, "SetupLastRace Result is Response.Loading")
                        _state.update { it.copy(isLoading = result.isLoading) }
                    }

                    is Response.Success -> {
                        Log.d(TAG, "SetupLastRace Result is Response.Success")
                        result.data?.let { lastGP ->
                            _state.update { state ->
                                state.copy(lastGP = lastGP[0], isLoading = result.isLoading)
                            }
                        }
                    }

                    is Response.Error -> {
                        Log.d(TAG, "SetupLastRace Result is Response.Loading")
                        result.data?.let { lastGP ->
                            _state.update { state ->
                                state.copy(lastGP = lastGP[0], isLoading = result.isLoading)
                            }
                        }
//                        result.message TODO
                    }
                }
            }
        }
    }

    private fun setupNextRace() {
        Log.d(TAG, "setupNextRace is called.")
        viewModelScope.launch {
            getLastRaceUseCase(false).collect { result ->
                result.data?.let { listLastRace ->
                    val nextGpRound = listLastRace[0].round.toInt() + 1
                    getSeasonRacesUseCase(false).collect { seasonRaces ->
                        val totalRounds = seasonRaces.data?.count()
                        when (result) {
                            is Response.Loading -> {
                                Log.d(TAG, "SetupNextRace Result is Response.Loading")
                                _state.update { it.copy(isLoading = result.isLoading) }
                            }

                            is Response.Success -> {
                                Log.d(TAG, "SetupNextRace Result is Response.Success")
                                if (totalRounds != null && nextGpRound >= totalRounds) {
                                    _state.update {
                                        it.copy(
                                            nextGP = GrandPrix.postSeason(),
                                            isLoading = result.isLoading
                                        )
                                    }
                                } else {
                                    _state.update {
                                        it.copy(
                                            nextGP = seasonRaces.data?.get(nextGpRound - 1),
                                            isLoading = result.isLoading
                                        )
                                    }
                                }
                            }

                            is Response.Error -> {
                                Log.d(TAG, "SetupNextRace Result is Response.Error")
                                if (totalRounds != null && nextGpRound >= totalRounds) {
                                    _state.update {
                                        it.copy(
                                            nextGP = GrandPrix.postSeason(),
                                            isLoading = result.isLoading
                                        )
                                    }
                                } else {
                                    _state.update {
                                        it.copy(
                                            nextGP = seasonRaces.data?.get(nextGpRound - 1),
                                            isLoading = result.isLoading
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun lastGpFastestLap(raceResults: List<RaceResults>) {
        viewModelScope.launch(Dispatchers.Default) {
            for (raceResult in raceResults) {
                while (raceResult.fastestLap?.rank == "1") {
                    _state.update { it.copy(lastGpFastestLap = raceResult) }
                }
            }
        }
    }

    fun calculateCountdown(date: String, time: String) {
        viewModelScope.launch {
            Log.d(TAG, "calculateCountdown is called")
            val gpTimeSeconds = (date + "T" + time).formatToSeconds()
            Log.d(TAG, "Grand Prix time in seconds is $gpTimeSeconds")
            val countdown = countdownFormatter(gpTimeSeconds)
            Log.d(TAG, "countdown is $countdown")
            _state.update {
                it.copy(
                    nextGpCountdown = countdown
                )
            }
        }
    }

    fun formatDate(date: String) {
        viewModelScope.launch {
            val dateFormatted = date.formatDate()
            _state.update { it.copy(nextGpDate = dateFormatted) }
        }
    }

    fun startTimerCountdown() {
        _state.update { it.copy(shouldUpdateCountdown = false) }
        Log.d(TAG, "timer Countdown(). shouldUpdateCountdown is false")
        timer = object : CountDownTimer(ONE_MINUTE, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                _state.update { it.copy(shouldUpdateCountdown = true) }
                Log.d(TAG, "timer onFinish")
            }
        }
        timer.start()
    }

    fun restartTimerCountdown() {
        _state.update { it.copy(shouldUpdateCountdown = true) }
    }

    fun cancelTimerCountdown() {
        timer.cancel()
    }
}



