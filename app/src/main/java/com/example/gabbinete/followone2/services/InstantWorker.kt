package com.example.gabbinete.followone2.services

private const val TAG = "InstantWorker"

//@HiltWorker
//class InstantWorker @AssistedInject constructor(
//    @Assisted context: Context,
//    @Assisted workerParameters: WorkerParameters
//) : Worker(context, workerParameters) {
//    override fun doWork(): Result {
//        val currentTime = Instant.now().epochSecond
//        Log.d(TAG, "Instant time is $currentTime")
//        val data = Data.Builder().putLong(INSTANT_WORKER_KEY, currentTime).build()
//        return Result.success(data)
//    }
//}