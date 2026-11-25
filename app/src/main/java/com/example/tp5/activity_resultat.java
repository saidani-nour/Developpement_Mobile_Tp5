package com.example.tp5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class activity_resultat extends AppCompatActivity {

    private TextView txtResult, txtEtat;
    private Button btnCall, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        txtResult = findViewById(R.id.txtIMC);
        txtEtat = findViewById(R.id.txtEtat);
        btnCall = findViewById(R.id.btnCall);
        btnBack = findViewById(R.id.btnBack);

        String prenom = getIntent().getStringExtra("prenom");
        String sexe = getIntent().getStringExtra("sexe");
        double imc = getIntent().getDoubleExtra("imc", 0);

        String civilite = "Femme".equals(sexe) ? "Madame" : "Monsieur";
        txtResult.setText(String.format(Locale.getDefault(),
                "%s %s, votre IMC est : %.2f", civilite, prenom, imc));

        // Interprétation OMS + couleur
        String etat;
        int couleur;
        if (imc < 18.5) {
            etat = "Maigreur";
            couleur = R.color.colorOrange;
        } else if (imc < 25) {
            etat = "Normal";
            couleur = R.color.colorGreen;
        } else if (imc < 30) {
            etat = "Surpoids";
            couleur = R.color.colorOrange;
        } else {
            etat = "Obésité";
            couleur = R.color.colorRed;
        }
        txtEtat.setText(etat);
        txtEtat.setTextColor(ContextCompat.getColor(this, couleur));

        // Intent implicite
        btnCall.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"));
            startActivity(call);
        });

        // Retour à l'activité principale
        btnBack.setOnClickListener(v -> finish());
    }
}