package com.mahima.animestreamingapp.ui.mypet

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mahima.animestreamingapp.R
import java.sql.Time
import java.util.*

class WalkreminderFragment : Fragment() {
//    variable declaration
    private lateinit var tvtime : TextView
    private lateinit var btnselecttime : Button
    private lateinit var btndone : Button
    private lateinit var picker: MaterialTimePicker
    private lateinit var alarmManager: AlarmManager
    private lateinit var calendar:Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_walkreminder, container, false)
//        initializing variables
        tvtime = view.findViewById(R.id.tvtime)

        tvtime.setText(calendar.time.toString())
        btnselecttime = view.findViewById(R.id.btnselecttime)
        btndone = view.findViewById(R.id.btndone)
//        function to create notification channel
        createNotificationChannel()
//        btn click listeners
        btnselecttime.setOnClickListener { selectTime() }

        btndone.setOnClickListener { done() }

        return view
    }

//    set the time
    private fun done() {
        alarmManager= requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(),com.mahima.animestreamingapp.classes.AlarmManager::class.java)
        var pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )

    Toast.makeText(requireContext(),"Reminder added successfully", Toast.LENGTH_SHORT).show()
}
//function for time picker
    private fun selectTime() {

        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select reminder time")
            .build()

        picker.show(requireActivity().supportFragmentManager,"forpetwalk")

        picker.addOnPositiveButtonClickListener {
            if(picker.hour > 12){
                tvtime.text=
                    String.format("%02d",picker.hour - 12) + " : " +
                            String.format("%02d",picker.minute) + " PM"
            }else{
                String.format("%02d",picker.hour) + " : " +
                        String.format("%02d",picker.minute) + " AM"

            }

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    //    function to create notification channel
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name : CharSequence= "forremindertowalkpet"
            val description= "Channel_1"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("forpetwalk",name,importance)
            channel.description= description

            val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

}