package com.utp.motocuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
public class RegistroActivity extends AppCompatActivity {

    private EditText nombresEditText, apellidosEditText, correoEditText, contrasenaEditText, direccionEditText, celularEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Obtener referencias a los elementos de la interfaz de usuario
        nombresEditText = findViewById(R.id.editTextNombres);
        apellidosEditText = findViewById(R.id.editTextApellidos);
        correoEditText = findViewById(R.id.editTextCorreoRegistro);
        contrasenaEditText = findViewById(R.id.editTextContrasenaRegistro);
        direccionEditText = findViewById(R.id.editTextDireccion);
        celularEditText = findViewById(R.id.editTextNumeroCelular);

        Button registroButton = findViewById(R.id.Registro);
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para realizar el registro
                registrarUsuario();
            }
        });
    }

    // Método para el registro de usuario
    private void registrarUsuario() {
        String nombres = nombresEditText.getText().toString().trim();
        String apellidos = apellidosEditText.getText().toString().trim();
        String correo = correoEditText.getText().toString().trim();
        String contrasena = contrasenaEditText.getText().toString().trim();
        String direccion = direccionEditText.getText().toString().trim();
        String celular = celularEditText.getText().toString().trim();

        // Validaciones básicas
        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(correo) ||
                TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(direccion) || TextUtils.isEmpty(celular)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar la información del usuario en un archivo de texto
        String usuarioInfo = nombres + "," + apellidos + "," + correo + "," + contrasena + "," + direccion + "," + celular + "\n";
        guardarEnArchivo(usuarioInfo);

        // Muestra un mensaje de éxito
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        // Puedes redirigir a la pantalla principal u otra pantalla después del registro
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para que el usuario no pueda retroceder al formulario de registro
    }

    // Método para guardar la información en un archivo de texto
    private void guardarEnArchivo(String data) {
        try {
            FileOutputStream fos = openFileOutput("usuarios.txt", MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para volver a la pantalla de inicio
    public void irInicio(View view) {
        finish(); // Cierra la actividad actual y vuelve a la actividad anterior (en este caso, MainActivity)
    }
}
