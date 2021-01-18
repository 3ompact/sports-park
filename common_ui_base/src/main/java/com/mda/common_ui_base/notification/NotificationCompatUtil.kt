package com.mda.common_ui_base.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

/**
 * 通知兼容工具
 * */
class NotificationCompatUtil {

    companion object {
        fun createNotificationBuilder(
            context: Context,
            channel: Channel,
            title: CharSequence? = null,
            text: CharSequence? = null,
            intent: Intent? = null
        ): NotificationCompat.Builder {

            //在8.0及其以上才能发布任何通知
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(context, channel)
            }

            val builder = NotificationCompat.Builder(context, channel.channelId)
                .setPriority(getLowVersionPriorty(channel))
                .setVisibility(channel.lockScreenVisibility)
                .setVibrate(channel.vibrate)
                .setSound(channel.sound ?: Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOnlyAlertOnce(true)

            if(!TextUtils.isEmpty(title)) builder.setContentTitle(title)

            if(!TextUtils.isEmpty(text)) builder.setContentText(text)

            if(intent != null){
                val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
                builder.setContentIntent(pendingIntent)
                    .setAutoCancel(true)

                if(NotificationManager.IMPORTANCE_HIGH == channel.importance)  builder.setFullScreenIntent(pendingIntent, false)

            }
            return builder
        }


        private fun getLowVersionPriorty(channel: Channel): Int {
            return when (channel.importance) {
                NotificationManager.IMPORTANCE_HIGH -> NotificationCompat.PRIORITY_HIGH
                NotificationManager.IMPORTANCE_LOW -> NotificationCompat.PRIORITY_LOW
                NotificationManager.IMPORTANCE_MIN -> NotificationCompat.PRIORITY_MIN
                else -> NotificationCompat.PRIORITY_DEFAULT
            }
        }

        /**
         * 创建通知渠道
         */
        @RequiresApi(Build.VERSION_CODES.O)
        private fun createChannel(context: Context, channel: Channel) {
            val notificationChannel =
                NotificationChannel(channel.channelId, channel.name, channel.importance)
            notificationChannel.description = channel.description
            notificationChannel.vibrationPattern = channel.vibrate
            notificationChannel.setSound(
                channel.sound ?: Settings.System.DEFAULT_NOTIFICATION_URI,
                notificationChannel.audioAttributes
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }




        /**
         * 触发通知
         */
        fun notify(context: Context, id: Int, notification: Notification) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id, notification)
        }

        /**
         * 取消通知
         */
        fun cancel(context: Context, id: Int) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(id)
        }

        /**
         * 取消所有通知
         */
        fun cancel(context: Context) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()
        }

    }
    data class Channel(
        val channelId: String,
        val name: CharSequence,
        val importance: Int,
        val description: String? = null,
        @NotificationCompat.NotificationVisibility
        val lockScreenVisibility: Int = NotificationCompat.VISIBILITY_SECRET,
        val vibrate: LongArray? = null,
        val sound: Uri? = null

    )

}