package com.utp.motocuy;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {
    private EditText editTextMetros;
    private EditText editTextPrecioPorMetro;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // obtener valores
        editTextMetros = findViewById(R.id.editTextMetros);
        editTextPrecioPorMetro = findViewById(R.id.editTextPrecioPorMetro);
        txtResultado = findViewById(R.id.txt_resultadoPrecio);
    }

    public void irLogin(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void calcularPrecio(View view) {
        // Obtener la distancia ingresada por el usuario
        String metrosStr = editTextMetros.getText().toString();
        // Verificar si el campo de distancia está vacío
        if (TextUtils.isEmpty(metrosStr)) {
            Toast.makeText(this, "Ingrese la distancia", Toast.LENGTH_SHORT).show();
            return; // Sale del método si la distancia está vacía
        }
        double metros = Double.parseDouble(metrosStr);

        // Obtener el precio por metro ingresado por el usuario
        String precioPorMetroStr = editTextPrecioPorMetro.getText().toString();
        // Verificar si el campo de precio por metro está vacío
        if (TextUtils.isEmpty(precioPorMetroStr)) {
            Toast.makeText(this, "Ingrese el precio por metro", Toast.LENGTH_SHORT).show();
            return; // Sale del método si el precio por metro está vacío
        }
        double precioPorMetro = Double.parseDouble(precioPorMetroStr);

        // Calcular el precio total
        double precioTotal = metros * precioPorMetro;

        // Mostrar el resultado
        String resultado = "Distancia recorrida: " + metros + " metros\n";
        resultado += "Precio final a pagar: S/." + precioTotal;
        txtResultado.setText(resultado);
    }
}