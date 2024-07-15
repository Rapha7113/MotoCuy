package com.utp.motocuy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class VideoActivity extends AppCompatActivity {

    private static final int VIDEO_TIME_OUT = 4000; // 4 segundos de duración
    private final static String CHANNEL_ID = "MotoCuy";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Configurar y reproducir el video
        VideoView videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_motocuy);
        videoView.setVideoURI(videoUri);
        videoView.start();

        // Crear el canal de notificación al iniciar la actividad
        crearCanalDeNotificacion();

        // Mostrar la notificación después de un pequeño retraso
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarNotificacion("Inicio de sesión exitoso", "Bienvenido a MotoCuy");
                Intent intent = new Intent(VideoActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        }, VIDEO_TIME_OUT);
    }

    private void crearCanalDeNotificacion() {
        // Crear un canal de notificación (requerido para Android 8.0 y superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri sonidoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sonido_motocuy);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            CharSequence nombre = "Canal MotoCuy";
            String descripcion = "Canal para notificaciones de MotoCuy";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nombre, importancia);
            channel.setDescription(descripcion);
            channel.setSound(sonidoUri, audioAttributes); // Establecer el sonido personalizado

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void mostrarNotificacion(String titulo, String contenido) {
        // Configurar el sonido de la notificación
        Uri sonidoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sonido_motocuy);

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher) // Icono de la notificación
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(sonidoUri) // Establecer el sonido personalizado
                .setAutoCancel(true); // La notificación se cierra cuando se hace clic en ella

        // Mostrar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICACION_ID, builder.build());
    }
}
