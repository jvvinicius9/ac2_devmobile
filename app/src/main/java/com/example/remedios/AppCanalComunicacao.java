package com.example.remedios;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class AppCanalComunicacao extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(
                    "med_channel",
                    "Canal de Lembrete",
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal.setDescription("Canal para avisos de medicamentos");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal);
        }
    }
}
