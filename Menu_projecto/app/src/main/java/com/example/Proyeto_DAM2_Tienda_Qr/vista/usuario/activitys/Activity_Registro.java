package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.Proyeto_DAM2_Tienda_Qr.connecion.Connecion;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos.datosPago_Fragmento;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos.datosPersonal_Fragmento;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos.datosResidencia_Fragmento;
import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Activity_inicio;
import com.example.Proyeto_DAM2_Tienda_Qr.util.ValidarInpustEntrada;

import org.json.JSONObject;

public class Activity_Registro extends AppCompatActivity implements datosPersonal_Fragmento.OnFragmentInteractionListener,
        datosResidencia_Fragmento.OnFragmentInteractionListener, datosPago_Fragmento.OnFragmentInteractionListener,
        Response.Listener<JSONObject>, Response.ErrorListener {

    private Button btn_datosPersonal;
    private Button btn_datosResidencial;
    private Button btn_datosPago;

    public static Button btn_guardar;

    public static Button btn_volverInicio;


    // variables para receber los datos

    public String datoNombre;
    public String datoApell;
    public String datoEmal;
    public String datoPass;
    public String datoTelef;
    public String datoFecha;

    public String datoCalle;
    public String datoPiso;
    public String datoPuerta;
    public String datoCiudad;
    public String datoTelefFijo;
    public String datotCp;
    public String datoPronvi;

    public String datoNomProprietarioTarjecta;
    public String datoNumertoTarjecta;
    public String datoFechaTarjecta;


    public Usuario usuario1 = new Usuario();

    // conecion

    // vollye
    private RequestQueue rq;


    // framnetos
    datosPago_Fragmento datosPago;
    datosResidencia_Fragmento datosResidencia;
    datosPersonal_Fragmento datosPersonal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__registro );

        btn_datosPersonal = (Button) findViewById( R.id.datosPersonal );
        btn_datosResidencial = (Button) findViewById( R.id.datosResidencial );
        btn_datosPago = (Button) findViewById( R.id.datosPago );
        btn_datosPago = (Button) findViewById( R.id.datosPago );

        btn_guardar = (Button) findViewById( R.id.btn_guardar );
        btn_volverInicio = (Button) findViewById( R.id.btn_VolverInicio );

        rq = Volley.newRequestQueue( getApplicationContext() );

        // istancia los fragmentos


        datosPersonal = new datosPersonal_Fragmento();
        datosResidencia = new datosResidencia_Fragmento();
        datosPago = new datosPago_Fragmento();


        getSupportFragmentManager().beginTransaction().add( R.id.contenedorFragment, datosPersonal ).commit();


        btn_guardar.setVisibility( View.INVISIBLE );


        btn_guardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receberLosdados();

            }
        } );


        btn_volverInicio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( getApplicationContext(), Activity_inicio.class );
                startActivity( intent );
                finish();
            }
        } );
    }

    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.datosPersonal:
                transaction.replace( R.id.contenedorFragment, datosPersonal );
                btn_guardar.setVisibility( View.INVISIBLE );
                //btn_guardar.setVisibility( View.VISIBLE );
                break;
            case R.id.datosResidencial:
                transaction.replace( R.id.contenedorFragment, datosResidencia );
                btn_guardar.setVisibility( View.INVISIBLE );
                break;
            case R.id.datosPago:
                transaction.replace( R.id.contenedorFragment, datosPago );
                //btn_guardar.setVisibility( View.INVISIBLE );
                break;


        }
        transaction.commit();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText( getApplicationContext(), "Error al Insertar" + error.toString(), Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText( getApplicationContext(), "Se ha Registrado correctamente", Toast.LENGTH_LONG ).show();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void receberLosdados() {


        // Passado los dadots

        datoNombre = Usuario.getNombre();
        datoApell = Usuario.getApellidos();
        datoEmal = Usuario.getEmail();
        datoPass = Usuario.getPassword();
        datoTelef = Usuario.getTelefono();
        datoFecha = Usuario.getFecha_nasc();
//
        datoCalle = Usuario.getCalle();
        datoPiso = Usuario.getPiso();
        datoPuerta = Usuario.getPuerta();
        datoCiudad = Usuario.getCiudad();
        datoTelefFijo = Usuario.getTelefono_fijo();
        datotCp = Usuario.getCodigoPostal();
        datoPronvi = Usuario.getProvincia();
//
        datoNomProprietarioTarjecta = Usuario.getNombreProprietarioTarjeta();
        datoNumertoTarjecta = Usuario.getNumeroTarjeta();
        datoFechaTarjecta = Usuario.getFechaCaducidadTarjeta();


        boolean datosValido = true;

        if ((datoNombre != null) && (datoApell != null) && (datoEmal != null) && (datoPass != null) && (datoTelef != null)
                && (datoFecha != null) && (datoCalle != null) && (datoPiso != null) && (datoPuerta != null) && (datoCiudad != null)
                && (datoNumertoTarjecta != null) && (datoNomProprietarioTarjecta != null) && (datoFechaTarjecta != null)) {

            guardaDatosBD();

            datosValido = false;


            Intent intent = new Intent( getApplicationContext(), Activity_login.class );
            startActivity( intent );
            finish();


        } else if (datosValido = false) {


            Toast.makeText( getApplicationContext(), "Los campos estao vacio", Toast.LENGTH_LONG ).show();


        }


    }

    public void guardaDatosBD() { // String url

        String passEncript = ValidarInpustEntrada.ByteToHex( ValidarInpustEntrada.encriptyPass( datoPass ) );

        String url = Connecion.IP + Connecion.REGISTRO + datoNombre + "&apellidos=" + datoApell + "&mail=" + datoEmal + "&pass=" + passEncript + "&fecha_nasc=" + datoFecha + "&calle=" + datoCalle + "&pisto=" + datoPiso + "&puerta=" + datoPuerta + "&ciudad=" + datoCiudad +
                "&telefono=" + datoTelef + "&codigo_postal=" + datotCp + "&provincia=" + datoPronvi + "&nomTarjeta=" + datoNomProprietarioTarjecta + "&numTarjeta=" + datoNumertoTarjecta + "&fechaTarjeta=" + datoFechaTarjecta;

        Log.d( "cod", url );


        url = url.replace( " ", "%20" );

        JsonRequest jrs = new JsonObjectRequest( Request.Method.DEPRECATED_GET_OR_POST, url, null, this, this );
        rq.add( jrs );

    }

}
