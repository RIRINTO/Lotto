package com.hakjae.lotto.lib.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.core.content.edit
import java.util.*


class DeviceBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {

            val sharedPreferences = context.getSharedPreferences("lottoAlarm", MODE_PRIVATE)
            val lottoAlarm = sharedPreferences.getInt("lottoAlarm", 0)

            if (lottoAlarm == 1) {

                val time: Long = sharedPreferences.getLong("remindSchedule", Calendar.getInstance().timeInMillis)
                val remindSchedule: Calendar = Calendar.getInstance()
                remindSchedule.timeInMillis = time
                while (remindSchedule.before(Calendar.getInstance())) {    // 토요일 오후 8시 50분이 이미 지난 경우
                    remindSchedule.add(Calendar.DATE, 7)           // 작동 시점을 일주일 단위로 미룸
                }

                sharedPreferences.edit {
                    putLong("remindSchedule",remindSchedule.timeInMillis) // 알림 시간 저장
                }

                val alarmIntent = Intent(context, AlarmReceiver::class.java)
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    remindSchedule.timeInMillis,
                    pendingIntent
                )
            }
        }
    }
}
