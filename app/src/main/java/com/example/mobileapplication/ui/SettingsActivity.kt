package com.example.mobileapplication.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.example.mobileapplication.R
import com.example.mobileapplication.ui.fragments.TimePickerFragment
import com.example.mobileapplication.utils.AlarmReceiver
import com.example.mobileapplication.utils.NotificationManager
import kotlinx.android.synthetic.main.activity_settings.*
import java.text.DateFormat
import java.util.*

class SettingsActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private var alarmManager: AlarmManager? = null
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun showTimerPickerFragment(view: View) {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.show(supportFragmentManager, "time_picker")
    }

    fun cancelAlarm(view: View) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)
        alarm_time_text.text = "Set Morning Time"
    }

    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        updateTimeText(calendar)
        startAlarm(calendar)
    }

    private fun startAlarm(calendar: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 10000, pendingIntent)
    }

    private fun updateTimeText(calendar: Calendar) {
        val str = "Morning Feeding at: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)
        alarm_time_text.text = str
    }
}