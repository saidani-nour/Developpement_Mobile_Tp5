package com.example.tp5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtPrenom, edtPoids, edtTaille;
    private RadioButton rbHomme, rbFemme;
    private Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrenom = findViewById(R.id.etPrenom);
        edtPoids = findViewById(R.id.etPoids);
        edtTaille = findViewById(R.id.etTaille);
        rbHomme = findViewById(R.id.rbHomme);
        rbFemme = findViewById(R.id.rbFemme);
        btnCalculer = findViewById(R.id.btnCalculer);

        btnCalculer.setOnClickListener(v -> {
            String prenom = edtPrenom.getText().toString().trim();
            String poidsStr = edtPoids.getText().toString().trim().replace(',', '.');
            String tailleStr = edtTaille.getText().toString().trim().replace(',', '.');

            if (prenom.isEmpty() || poidsStr.isEmpty() || tailleStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            float poids, taille;
            try {
                poids = Float.parseFloat(poidsStr);
                taille = Float.parseFloat(tailleStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Valeurs numériques invalides", Toast.LENGTH_SHORT).show();
                return;
            }

            // Le hint demande la taille en mètres ; si l'utilisateur tape 170, on convertit
            if (taille > 3) {
                taille = taille / 100;
            }

            if (poids <= 0 || taille <= 0) {
                Toast.makeText(this, "Poids et taille doivent être positifs", Toast.LENGTH_SHORT).show();
                return;
            }

            double imc = poids / (taille * taille);
            String sexe = rbHomme.isChecked() ? "Homme" : "Femme";

            Intent i = new Intent(MainActivity.this, activity_resultat.class);
            i.putExtra("prenom", prenom);
            i.putExtra("imc", imc);
            i.putExtra("sexe", sexe);
            startActivity(i);
        });
    }
}