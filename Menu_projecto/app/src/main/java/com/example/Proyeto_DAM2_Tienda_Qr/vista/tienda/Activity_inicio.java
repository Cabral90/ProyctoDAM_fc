package com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys.Activity_Registro;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys.Activity_login;

import com.example.menu_projecto.R;

public class Activity_inicio extends AppCompatActivity {

    // variables
    private ImageView img_tienda;
    private ImageView img_Map;
    private TextView txt_ingo;
    private Button btn_login;
    private Button btn_registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_inicio );
        getSupportActionBar().hide();

        img_tienda=(ImageView)findViewById( R.id.imageView );
        //img_Map =(ImageView)findViewById( R.id.imgMap );
        txt_ingo =(TextView) findViewById( R.id.text_info);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_registrarse = (Button) findViewById(R.id.btn_recuperaPwd );


        // eventos de los botones

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLog = new Intent(Activity_inicio.this, Activity_login.class);
                Activity_inicio.this.startActivity(intentLog);

            }
        } );

        btn_registrarse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLog = new Intent(Activity_inicio.this, Activity_Registro.class);
                Activity_inicio.this.startActivity(intentLog);
            }
        } );
    }
}
