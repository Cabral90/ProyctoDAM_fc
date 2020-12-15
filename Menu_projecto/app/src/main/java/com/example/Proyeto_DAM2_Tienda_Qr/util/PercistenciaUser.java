package com.example.Proyeto_DAM2_Tienda_Qr.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.Proyeto_DAM2_Tienda_Qr.connecion.Connecion;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;


import java.io.FileOutputStream;

import java.io.InputStreamReader;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.Proyeto_DAM2_Tienda_Qr.MainActivity.MAIL_SECION;


public class PercistenciaUser {

    private String archivo = "userLogin.txt";
    private RequestQueue requestQueue;


    private String datoNombre;
    private String datoApellidos;
    private String datoMail;
    private String datoFecha_nas;
    private String datoProvincia;
    private String datoCiudad;
    private String datoCalle;
    private String datoPiso;
    private String datoPuerta;
    private String datoCp;
    private String datoTelefono;

    private String datoNomTarjeta;
    private String datoNumTarjeta;
    private String datoFechaTarjeta;

    public void guardarUsuario(Context context) {

        datoNombre = Usuario.getNombre();
        datoApellidos = Usuario.getApellidos();
        datoMail = Usuario.getEmail();
        datoFecha_nas = Usuario.getFecha_nasc();
        datoProvincia = Usuario.getProvincia();
        datoCiudad = Usuario.getCiudad();
        datoCalle = Usuario.getCalle();
        datoPiso = Usuario.getPiso();
        datoPuerta = Usuario.getPuerta();
        datoCp = Usuario.getCodigoPostal();
        datoTelefono = Usuario.getTelefono();

        datoNomTarjeta = Usuario.getNombreProprietarioTarjeta();
        datoNumTarjeta = Usuario.getNumeroTarjeta();
        datoFechaTarjeta = Usuario.getFechaCaducidadTarjeta();


        System.out.println( "==================== INICIO DE ESCRITORA DEL fichero ================" );

        System.out.println( "DATOS NOMBRE: " + datoNombre );


        try {
            FileOutputStream fout = context.openFileOutput( archivo, MODE_PRIVATE );

            String valorAguardar = "" + datoNombre + "--"
                    + datoApellidos + "--"
                    + datoMail + "--"
                    + datoFecha_nas + "--"
                    + datoCalle + "--"
                    + datoPiso + "--"
                    + datoPuerta + "--"
                    + datoCiudad + "--"
                    + datoTelefono + "--"
                    + datoCp + "--"
                    + datoProvincia + "--"
                    + datoNomTarjeta + "--"
                    + datoNumTarjeta + "--"
                    + datoFechaTarjeta + ""; // atencio los pasos en la s procimas contatenacion

            fout.write( valorAguardar.getBytes() );

            fout.close();
            System.out.println( "==================== FIM DE ESCRITORA DEL fichero ================" );

            lerUser22( context );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //
//
    public void lerUser22(Context context) {


        Usuario User = new Usuario();
        String texto = null;


        System.out.println( "==================== INICIO DE LECTURA DEL fichero ================" );

        try {

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    context.openFileInput( archivo ) ) );

            while ((texto = fin.readLine()) != null) {

                String dado = texto.toString();

                String[] parts = dado.split( "--" );


                User.setNombre( parts[0] );
                User.setApellidos( parts[1] );
                User.setEmail( parts[2] );

                User.setFecha_nasc( parts[3] );
                // hacer el explit para el formato de fecha
                User.setCalle( parts[4] );
                User.setPiso( parts[5] ); // corregir PISO
                User.setPuerta( parts[6] );
                User.setCiudad( parts[7] );
                User.setTelefono( parts[8] );
                User.setCodigoPostal( parts[9] );
                User.setProvincia( parts[10] );

                User.setNombreProprietarioTarjeta( parts[11] );
                User.setNumeroTarjeta( parts[12] );
                User.setFechaCaducidadTarjeta( parts[13] );

            }




            fin.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e( "Ficheros", "Error al leer fichero desde memoria interna" );
        }

    }

    public void mantenerUsuarioLoqueado(final Context context) {


        String URL = null;
        URL = Connecion.IP + Connecion.MATENER_USUARIO + MAIL_SECION; // AQUI BUSCA el usuario en la bd por Email

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                Usuario User = new Usuario();

                // FECHA

                String fechaUsuario = null;
                String fechaSistema = null;


                //System.out.println( "============== XXXXXXXXXXX =============== : " );

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


                        guardarUsuario( context ); // guardar los datos

                        System.out.println( "El nomes de la percistencia es: " + Usuario.getNombre() );

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( context, "Error de conexion", Toast.LENGTH_LONG ).show();
            }
        } );


        requestQueue = Volley.newRequestQueue( context );
        requestQueue.add( jsonArrayRequest );
    }


}
