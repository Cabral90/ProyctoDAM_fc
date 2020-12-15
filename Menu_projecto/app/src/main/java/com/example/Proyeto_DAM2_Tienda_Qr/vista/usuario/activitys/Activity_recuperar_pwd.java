package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.Proyeto_DAM2_Tienda_Qr.connecion.Connecion;
import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.util.ValidarInpustEntrada;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class Activity_recuperar_pwd extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private EditText recuperaPwdEmail;
    private EditText recuperaPwdPass1;
    private EditText recuperaPwdPass2;
    // errores
    private TextInputLayout errorMail;
    private TextInputLayout errorPass1;
    private TextInputLayout errorPass2;


    private Button btn_guardarPwdNuevo;

    private Button btn_volver;

    // vollye
    private RequestQueue rq;
    private RequestQueue requestQueue;

    // variables auxiliares
    private String mail;
    private String pass1;
    private String pass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recuperar_pwd );

        getSupportActionBar().hide(); // quita la barra de encia

        // cast de las variables


        recuperaPwdEmail = (EditText) findViewById( R.id.texRecupEmail );
        recuperaPwdPass1 = (EditText) findViewById( R.id.texRecupPass1 );
        recuperaPwdPass2 = (EditText) findViewById( R.id.texRecupPass2 );

        errorMail = (TextInputLayout) findViewById( R.id.inputRecuperaMail );
        errorPass1 = (TextInputLayout) findViewById( R.id.inputRecuperaPass1 );
        errorPass2 = (TextInputLayout) findViewById( R.id.inputRecuperaPass2 );

        btn_guardarPwdNuevo = (Button) findViewById( R.id.btn_recuperaPwdUsuario );
        btn_volver = (Button) findViewById( R.id.btn_volver );

        rq = Volley.newRequestQueue( getApplicationContext() ); // rever eso

        // metodo del buton guardar

        btn_guardarPwdNuevo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarEntrada();


//                Intent intent = new Intent( getApplicationContext(), Activity_login.class );
//                startActivity( intent );
//                finish();
            }
        } );

        btn_volver.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), Activity_login.class );
                startActivity( intent );
                finish();
            }
        } );


    }

    private void limpiarCambos() {
        recuperaPwdEmail.setText( "" );
        recuperaPwdPass1.setText( "" );
        recuperaPwdPass2.setText( "" );
    }


    private void validarEntrada() {

        // boolean esValido = true;

        mail = recuperaPwdEmail.getText().toString();
        pass1 = recuperaPwdPass1.getText().toString();
        pass2 = recuperaPwdPass2.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean esDatoValido = true;

        if (mail.isEmpty()) {
            // mensaje de vacio
            errorMail.setErrorEnabled( false );
            errorMail.setError( "* El correo no puede estar vacio" );
        } else if (!mail.trim().matches( emailPattern )) {
            // email no valido
            errorMail.setErrorEnabled( false );
            errorMail.setError( "* correo inválido" );
        } else if (pass1.isEmpty()) {
            // pass vacio
            errorPass1.setErrorEnabled( false );
            errorPass1.setError( " campo obrigatorio" );

        } else if (pass2.isEmpty()) {
            errorPass2.setErrorEnabled( false );
            errorPass2.setError( " * campo obrigatorio" );
            // pass vacio
        } else if (!pass1.equals( pass2 )) {
            errorPass2.setErrorEnabled( false );
            errorPass2.setError( " * las contraseña no son iguales" );
// pass no son iguales
        } else if ((!mail.isEmpty()) && (pass1.equals( pass2 ))) {
            errorMail.setErrorEnabled( false );
            errorPass1.setErrorEnabled( false );
            errorPass2.setErrorEnabled( false );

            String passEncript = ValidarInpustEntrada.ByteToHex( ValidarInpustEntrada.encriptyPass(  pass1 ) );

            String url = Connecion.IP + Connecion.RECUPERAR_PWD + "pass=" + passEncript + "&mail=" + mail;
            recuperarPWD( url );
            esDatoValido = false;
            limpiarCambos();
        }

    }

    private void recuperarPWD(String url) {


        Log.d( "cod", url );


        url = url.replace( " ", "%20" );

        System.out.println( "El URL: " + url );


        JsonRequest jrs = new JsonObjectRequest( Request.Method.DEPRECATED_GET_OR_POST, url, null, (Response.Listener<JSONObject>) this, this );
        rq.add( jrs );


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //Toast.makeText( getApplicationContext(), "Error al recuperar la contraseña" + error.toString(), Toast.LENGTH_SHORT ).show();
        Toast.makeText( getApplicationContext(), "Se ha recuparado la contraseña correctamente", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText( getApplicationContext(), "Error al recuperar la contraseña", Toast.LENGTH_SHORT ).show();

        //buscarDatosUsuario();
       // Toast.makeText( getApplicationContext(), "Se ha recuparado la contraseña correctamente", Toast.LENGTH_LONG ).show();

    }


}
