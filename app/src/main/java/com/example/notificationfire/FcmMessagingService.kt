package com.example.notificationfire

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("Title", remoteMessage.notification!!.title!!)
        if (remoteMessage.data.size > 0) {
            val title = remoteMessage.data["title"]
            val message = remoteMessage.data["message"]
            val intent = Intent("com.example.roomdatabasemethod_FCM-MESSAGE")
            val a = intent.putExtra("title", title).toString()
            val b = intent.putExtra("message", message).toString()
            Log.d("ni", a)
            Log.d("nu", b)
            val localBroadcastManager = LocalBroadcastManager.getInstance(this)
            localBroadcastManager.sendBroadcast(intent)
        }
        //        super.onMessageReceived(remoteMessage);
//        String title=remoteMessage.getNotification().getTitle();
//        String message=remoteMessage.getNotification().getBody();
//
//        Intent intent = new Intent(this,MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this);
//        notificationbuilder.setContentTitle(title);
//        notificationbuilder.setContentText(message);
//        notificationbuilder.setAutoCancel(true);
//        notificationbuilder.setSmallIcon(R.mipmap.ic_launcher);
//        notificationbuilder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notificationbuilder.build());
    }
}

