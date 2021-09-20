package com.mahima.animestreamingapp.classes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService

class CartNotification (val context :Context){
    val CHANNEL_3 :String = "Cart"
    val CHANNEL_4:String = "Favorites"
    val CHANNEL_5 : String ="Checkout"
    fun  createNofiticationChannels(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            notification for cart
            val channel3 = NotificationChannel(
                CHANNEL_3,
                "CART",
                NotificationManager.IMPORTANCE_LOW
            )
            channel3.description="Cart Channel"

//            notification for favorites
            val channel4 = NotificationChannel(
                CHANNEL_4,
                "FAVORITES",
                NotificationManager.IMPORTANCE_LOW
            )
            channel4.description="Favorites Channel"


//            notification for checkout
            val channel5 = NotificationChannel(
                CHANNEL_5,
                "CHECK OUT",
                NotificationManager.IMPORTANCE_LOW
            )
            channel5.description="Checkout Channel"

//            notification manager
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel3)
            notificationManager.createNotificationChannel(channel4)
            notificationManager.createNotificationChannel(channel5)
        }
    }
}