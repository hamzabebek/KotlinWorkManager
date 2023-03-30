package com.example.kotlinworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDatabase(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val getData = inputData
        val myNumber = getData.getInt("intKey",1)
        refreshDatabase(myNumber)
        return Result.success()
    }
    private fun refreshDatabase(myNumber : Int){
        val sp = context.getSharedPreferences("databaseInt",Context.MODE_PRIVATE)
        var mySavedNumber = sp.getInt("myNumber",0)
        mySavedNumber = mySavedNumber + myNumber
        println(mySavedNumber)
        sp.edit().putInt("myNumber",mySavedNumber).apply()
    }
}