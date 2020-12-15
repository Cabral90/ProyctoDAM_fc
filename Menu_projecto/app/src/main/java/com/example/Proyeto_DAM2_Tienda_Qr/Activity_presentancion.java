package com.example.Proyeto_DAM2_Tienda_Qr;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Activity_inicio;
import com.example.menu_projecto.R;

public class Activity_presentancion extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_presentancion );

        progressBar = (ProgressBar) findViewById( R.id.progressBar );
        // estetica de visibilidad
        progressBar.setVisibility( View.VISIBLE );


        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferencias = getSharedPreferences( "percistenciaUser", Context.MODE_PRIVATE );
                boolean sesion = preferencias.getBoolean( "sesion", false );


                // verificamos se hay sesion verdadera
                if (sesion) {
                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                    startActivity( intent );
                    finish();
                } else {
                    Intent intent = new Intent( getApplicationContext(), Activity_inicio.class );
                    startActivity( intent );
                    finish();
                }
            }
        }, 2000 );
    }

}
