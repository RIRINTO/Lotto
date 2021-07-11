package com.hakjae.lotto.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hakjae.lotto.R
import com.hakjae.lotto.databinding.ActivitySettingBinding
import com.hakjae.lotto.databinding.DialogInfoBinding
import com.hakjae.lotto.lib.alarm.NotifyService
import java.util.*


class SettingActivity : AppCompatActivity() {

    // SharedPreferences로 값 저장하기 위해 변수 생성
    private lateinit var binding: ActivitySettingBinding
    private lateinit var sharedPreferences: SharedPreferences

    // 알람 세팅 버튼의 상태 0으로 초기화
    private var alarmSettingButton = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        sharedPreferences = getSharedPreferences("lottoAlarm", MODE_PRIVATE)

        // 기존에 설정한 알람의 On/OFF 값 불러오기
        // 알람을 설정했던 경우, 버튼 check 상태 변경
        alarmSettingButton = sharedPreferences.getInt("alarmSettingButton", 0)
        binding.alarmSwitch.isChecked = alarmSettingButton == 1

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "설정"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24dp)

        binding.alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {  // 체크 상태로 변경
                val remindSchedule = Calendar.getInstance()
                remindSchedule.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY) // 토요일 오후 8시 50분 지정
                remindSchedule.set(Calendar.HOUR_OF_DAY, 20)
                remindSchedule.set(Calendar.MINUTE, 50)
                remindSchedule.set(Calendar.SECOND, 0)
                remindSchedule.set(Calendar.MILLISECOND, 0)
                while (remindSchedule.before(Calendar.getInstance())) {    // 토요일 오후 8시 50분이 이미 지난 경우
                    remindSchedule.add(Calendar.DATE, 7)           // 작동 시점을 일주일 단위로 미룸
                }
                sharedPreferences.edit {
                    putInt("alarmSettingButton", 1) //설정 버튼의 상태 저장
                    putLong("remindSchedule", remindSchedule.timeInMillis) // 알림 시간 저장
                }
                val start = Intent(this, NotifyService::class.java)
                startService(start)
                Toast.makeText(applicationContext, "알람을 설정했습니다", Toast.LENGTH_SHORT).show()
            } else {  // 체크 해제 상태로 변경
                sharedPreferences.edit {
                    putInt("alarmSettingButton", 0)
                }
                val stop = Intent(this, NotifyService::class.java)
                stopService(stop)
                Toast.makeText(applicationContext, "알람을 해제했습니다", Toast.LENGTH_SHORT).show()
            }
        }

        binding.homeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            finish()
        }

        binding.infoButton.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            val dialog = DialogInfoBinding.inflate(LayoutInflater.from(applicationContext))
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setView(dialog.root)
                .show()
        }

        binding.settingButton.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}