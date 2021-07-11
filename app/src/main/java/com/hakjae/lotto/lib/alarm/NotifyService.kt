package com.hakjae.lotto.lib.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import java.util.*


class NotifyService : Service() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sharedPreferences = getSharedPreferences("lottoAlarm", MODE_PRIVATE)
        val time: Long = sharedPreferences.getLong("remindSchedule", Calendar.getInstance().timeInMillis)
        val remindSchedule: Calendar = Calendar.getInstance()

        remindSchedule.timeInMillis = time
        repeatedNotification(remindSchedule)

        return super.onStartCommand(intent, flags, startId)
    }

    private fun repeatedNotification(remindSchedule: Calendar) {

        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            remindSchedule.timeInMillis,
            pendingIntent
        )
    }
}