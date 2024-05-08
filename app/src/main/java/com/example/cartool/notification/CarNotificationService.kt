package com.example.cartool.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class CarNotificationService(
    private val context:Context,
    private val carNumber: String,
    private val carService: String,
){
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(){
        val notification=NotificationCompat.Builder(context,"car_notification")
            .setContentTitle("Service notification for ${carNumber}")
            .setContentText("There are 7 more days until $carService will expire!!")
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}