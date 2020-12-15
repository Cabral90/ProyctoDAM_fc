package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.Proyeto_DAM2_Tienda_Qr.connecion.Connecion;
import com.example.Proyeto_DAM2_Tienda_Qr.MainActivity;
import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;

import com.example.Proyeto_DAM2_Tienda_Qr.util.PercistenciaUser;
import com.example.Proyeto_DAM2_Tienda_Qr.util.ValidarInpustEntrada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity_login extends AppCompatActivity {

    // variables de la activity
    private AutoCompleteTextView txt_email;
    private EditText txt_paswd;
    private Button bt_log;
    private Button btn_recuperarPwd;

    private ImageButton bt_social_fb;
    private ImageButton bt_social_tw;
    private ImageButton bt_social_google;


    // variables auxiliares


    private String email = null, password = null;


    // para la conexion con la libraria volly

    private RequestQueue requestQueue;

    // componentes de la entidad Usuario
    private Usuario User = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        getSupportActionBar().hide(); // quita la barra de encia

        txt_email = (AutoCompleteTextView) findViewById( R.id.email );
        txt_paswd = (EditText) findViewById( R.id.pass );


        bt_log = (Button) findViewById( R.id.btn_log );
        btn_recuperarPwd = (Button) findViewById( R.id.btn_recuperaPwdL );

        bt_social_fb = (ImageButton) findViewById( R.id.btn_fb );
        bt_social_tw = (ImageButton) findViewById( R.id.btn_twi );
        bt_social_google = (ImageButton) findViewById( R.id.btn_go );


        recuperPreferencias(); // nos mostrara el ultimo usuario y password correcto


        // eventos de los botones
        bt_log.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = txt_email.getText().toString();
                password = txt_paswd.getText().toString();

                String passEncript = ValidarInpustEntrada.ByteToHex( ValidarInpustEntrada.encriptyPass( password ) );

                if (!email.isEmpty() && !password.isEmpty()) {
                    String URL = Connecion.IP + Connecion.LOGIN + "mail=" + email + "&pass=" + passEncript;
                    login( URL );

                } else {
                    Toast.makeText( Activity_login.this, "No se permite los Campos Vacios", Toast.LENGTH_LONG ).show();
                }

            }
        } );


        btn_recuperarPwd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(), Activity_recuperar_pwd.class );
                startActivity( intent );
                finish();
            }
        } );


    }


    private void guardarPreferencias() {
        Usuario usuario = new Usuario();

        SharedPreferences preferences = getSharedPreferences( "percistenciaUser", Context.MODE_PRIVATE );

        SharedPreferences.Editor editor = preferences.edit();

        String user = Usuario.getNombre();

        email = txt_email.getText().toString();
        password = txt_paswd.getText().toString();

//
        editor.putString( "mail", email );
        editor.putString( "pass", password );

        editor.putBoolean( "sesion", true );
        editor.apply();

    }

    private void recuperPreferencias() { // lle el archivo guardao y ensea los datos
        // para que se muestre

        SharedPreferences preferences = getSharedPreferences( "percistenciaUser", Context.MODE_PRIVATE );
        txt_email.setText( preferences.getString( "mail", "" ) );
        txt_paswd.setText( preferences.getString( "pass", "" ) );

    }


    // ================= TAREA CONTINUA  ======================//

    private void login(String URL) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                guardarPreferencias(); // preferencias
                JSONObject jsonObject = null;

                String fechaUsuario = null;
                String fechaSistema = null;

                //if (response.equals( "null" )) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        // passar los datos en las variables

                        jsonObject = response.getJSONObject( i );

                        User.setNombre( jsonObject.getString( "nombre" ) );
                        User.setApellidos( jsonObject.getString( "apellidos" ) );
                        User.setEmail( jsonObject.getString( "mail" ) );
                        // User.setPassword( jsonObject.getString( "pass" ) );
                        fechaUsuario = jsonObject.getString( "fecha_nasc" );
                        // hacer el explit para el formato de fecha

                        String[] fecha = fechaUsuario.split( "-" );
                        String anio = fecha[0];
                        String mes = fecha[1];
                        String dia = fecha[2];
                        fechaSistema = dia + "/" + mes + "/" + anio;
                        User.setFecha_nasc( fechaSistema );

                        // hacer el explit para el formato de fecha
                        User.setCalle( jsonObject.getString( "calle" ) );
                        User.setPiso( jsonObject.getString( "pisto" ) ); // corregir PISO
                        User.setPuerta( jsonObject.getString( "puerta" ) );
                        User.setCiudad( jsonObject.getString( "ciudad" ) );
                        User.setTelefono( jsonObject.getString( "telefono" ) );
                        User.setCodigoPostal( jsonObject.getString( "codigo_postal" ) );
                        User.setProvincia( jsonObject.getString( "provincia" ) );

                        User.setNombreProprietarioTarjeta( jsonObject.getString( "nomTarjeta" ) );
                        User.setNumeroTarjeta( jsonObject.getString( "numTarjeta" ) );
                        User.setFechaCaducidadTarjeta( jsonObject.getString( "fechaTarjeta" ) );

                        // escrevendo el alcrivo
                        PercistenciaUser persi = new PercistenciaUser();

                        persi.guardarUsuario( getApplicationContext() );


                        Toast.makeText( getApplicationContext(), "El usuario es: " + Usuario.getNombre(), Toast.LENGTH_LONG ).show();

                        Intent intent = new Intent( getApplicationContext(), MainActivity.class ); // class reneral
                        startActivity( intent );
                        finish();

                    } catch (JSONException e) {

                        Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getApplicationContext(), "Error de conexion", Toast.LENGTH_LONG ).show();
            }
        } );

        requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( jsonArrayRequest );
    }


}
