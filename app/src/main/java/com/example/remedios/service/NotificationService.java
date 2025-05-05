package com.example.remedios.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.remedios.R;

public class NotificationService extends Service {

    public static final String CHANNEL_ID = "med_channel";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Espera 5 segundos e dispara a notificação
        new Handler().postDelayed(() -> {
            String nome = intent.getStringExtra("med_nome");

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Hora do remédio")
                    .setContentText("Tome: " + nome)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);

            NotificationManagerCompat.from(this).notify(1, builder.build());
        }, 5000);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
