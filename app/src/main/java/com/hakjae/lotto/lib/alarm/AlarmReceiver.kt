package com.hakjae.lotto.lib.alarm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.edit
import com.hakjae.lotto.ui.activities.MainActivity
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(
            context, MainActivity::class.java)
        val PendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent, 0)
        val builder = NotificationCompat.Builder(
            context, "default")

        builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentTitle("로또6/45 추첨 시간입니다!")
            .setContentText("추첨 결과를 확인해볼까요?")
            .setContentIntent(PendingIntent)

        // 노티피케이션 동작
        notificationManager.notify(5184, builder.build())

        val sharedPreferences = context.getSharedPreferences(
            "lottoAlarm", MODE_PRIVATE)
        val remindSchedule = Calendar.getInstance()
        remindSchedule.timeInMillis = sharedPreferences.getLong(
            "remindSchedule", Calendar.getInstance().timeInMillis)

        // 일주일 후로 알람 설정
        remindSchedule.add(Calendar.DATE, 7)

        //  Preference에 설정한 값 저장
        sharedPreferences.edit {
            putLong("remindSchedule", remindSchedule.timeInMillis)
        }

        val start = Intent(
            context, NotifyService::class.java
        )

        context.startService(
            start
        )
    }
}