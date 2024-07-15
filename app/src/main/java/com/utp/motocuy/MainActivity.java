package com.utp.motocuy;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private final static String CHANNEL_ID = "MotoCuy";
    private final static int NOTIFICACION_ID = 0;
    private TextInputEditText correoEditText, contrasenaEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los elementos de la interfaz de usuario
        correoEditText = findViewById(R.id.editTextCorreo);
        contrasenaEditText = findViewById(R.id.editTextContrasena);

        // Crear el canal de notificación al iniciar la actividad
        crearCanalDeNotificacion();
    }

    public void irRegistrarse(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    public void irPrincipal(View view) {
        // Obtén los datos del usuario y la contraseña
        String correo = correoEditText.getText().toString().trim();
        String contrasena = contrasenaEditText.getText().toString().trim();

        // Realiza la autenticación
        if (autenticarUsuario(correo, contrasena)) {
            Intent i = new Intent(this, VideoActivity.class);
            startActivity(i);
        } else {
            // Autenticación fallida, muestra una alerta
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para autenticar al usuario
    private boolean autenticarUsuario(String correo, String contrasena) {
        try {
            InputStream is = openFileInput("usuarios.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 6 && userInfo[2].equals(correo) && userInfo[3].equals(contrasena)) {
                    // Autenticación exitosa
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Autenticación fallida
        return false;
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


}
