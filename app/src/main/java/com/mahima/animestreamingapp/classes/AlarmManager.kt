package com.mahima.animestreamingapp.classes

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mahima.animestreamingapp.DashboardActivity
import com.mahima.animestreamingapp.R
import java.util.Date.from

class AlarmManager : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val intenttoopen = Intent(context,DashboardActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(context,0,intenttoopen,0)

//        notification builder
        val builder = NotificationCompat.Builder(context!!,"forpetwalk")
            .setSmallIcon(R.drawable.run)
            .setContentTitle("Jojo")
            .setContentText("Time to go for a walk!!!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notificationManager1 = NotificationManagerCompat.from(context)
        notificationManager1.notify(1,builder)
    }
}