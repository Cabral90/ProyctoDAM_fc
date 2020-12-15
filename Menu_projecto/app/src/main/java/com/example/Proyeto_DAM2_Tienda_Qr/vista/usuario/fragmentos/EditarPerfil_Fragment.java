package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.Proyeto_DAM2_Tienda_Qr.util.PercistenciaUser;

import org.json.JSONObject;

public class EditarPerfil_Fragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private OnFragmentInteractionListener mListener;

    private EditText editarPerfilNombre;
    private EditText editarPerfilApellido;
    private EditText editarPerfilTelefono;
    private EditText editarPerfilFecha;

    private EditText editarPerfilCiudad;
    private EditText editarPerfilProvincia;
    private EditText editarPerfilCalle;
    private EditText editarPerfilPiso;
    private EditText editarPerfilPuerta;
    private EditText editarPerfilCp;

    private EditText editarPerfilNomTitoTarjeta;
    private EditText editarPerfilNumTitoTarjeta;
    private EditText editarPerfilFechaTarjeta;

    private Button btn_guardarCambios;

    // variables para receber los datos

    private String datoEditNombre;
    private String datoEditApell;
    private String datoEditEmal;

    private String datoEditTelef;
    private String datoEditFecha;

    private String datoEditCalle;
    private String datoEditPiso;
    private String datoEditPuerta;
    private String datoEditCiudad;
    private String datoEditPronvi;
    private String datoEditCp;

    private String datoEditNomProprietarioTarjecta;
    private String datoEditNumertoTarjecta;
    private String datoEditFechaTarjecta;


    // vollye
    private RequestQueue rq;

    // fragment
    DatosPerfil_Fragment datosPerfil;


    public EditarPerfil_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EditarPerfil_Fragment newInstance(String param1, String param2) {
        EditarPerfil_Fragment fragment = new EditarPerfil_Fragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate( R.layout.fragment_editar_perfil_, container, false );


        editarPerfilNombre = (EditText) root.findViewById( R.id.texEditNombre );
        editarPerfilApellido = (EditText) root.findViewById( R.id.texEditApellido );
        // perfilEmail=(TextView) root.findViewById( R.id.textApellidoPerVisual );
        editarPerfilTelefono = (EditText) root.findViewById( R.id.texEditTelefono );
        editarPerfilFecha = (EditText) root.findViewById( R.id.texEditFecha );

        editarPerfilProvincia = (EditText) root.findViewById( R.id.texEditProvincia );
        editarPerfilCiudad = (EditText) root.findViewById( R.id.texEditCiudad );
        editarPerfilCalle = (EditText) root.findViewById( R.id.texEditCalle );
        editarPerfilPiso = (EditText) root.findViewById( R.id.texEditPiso );
        editarPerfilPuerta = (EditText) root.findViewById( R.id.texEditPuerta );
        editarPerfilCp = (EditText) root.findViewById( R.id.texEditCp );


        editarPerfilNomTitoTarjeta = (EditText) root.findViewById( R.id.texEditNomProTarjeta );
        editarPerfilNumTitoTarjeta = (EditText) root.findViewById( R.id.texEditNumProTarjeta );
        editarPerfilFechaTarjeta = (EditText) root.findViewById( R.id.texEditFechaTarjeta );

        btn_guardarCambios = (Button) root.findViewById( R.id.btn_guardarEditado );

        // Butonn

        // istanciar los fragment
        datosPerfil = new DatosPerfil_Fragment();


        setValoresAeditar();

        // volly
        rq = Volley.newRequestQueue( getContext() ); // rever eso


        btn_guardarCambios.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarlosDatosEditados(); // PRUEBA

            }
        } );

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setValoresAeditar() {

        editarPerfilNombre.setText( Usuario.getNombre() );
        editarPerfilApellido.setText( Usuario.getApellidos() );
        editarPerfilTelefono.setText( Usuario.getTelefono() );
        editarPerfilFecha.setText( Usuario.fecha_nasc );


        editarPerfilProvincia.setText( Usuario.getProvincia() );
        editarPerfilCiudad.setText( Usuario.getCiudad() );
        editarPerfilCalle.setText( Usuario.getCalle() );
        editarPerfilPiso.setText( Usuario.getPiso() );
        editarPerfilPuerta.setText( Usuario.getPuerta() );
        editarPerfilCp.setText( Usuario.getCodigoPostal() );

        editarPerfilNomTitoTarjeta.setText( Usuario.getNombreProprietarioTarjeta() );
        editarPerfilNumTitoTarjeta.setText( Usuario.getNumeroTarjeta() );
        editarPerfilFechaTarjeta.setText( Usuario.getFechaCaducidadTarjeta() );

    }

    private void validarDatosAguardar() {


        // Passado los dadots

        //String

        datoEditNombre = editarPerfilNombre.getText().toString();
        datoEditApell = editarPerfilApellido.getText().toString();
        datoEditEmal = editarPerfilTelefono.getText().toString();
        datoEditFecha = editarPerfilFecha.getText().toString();
        datoEditTelef = editarPerfilTelefono.getText().toString();

        datoEditPronvi = editarPerfilProvincia.getText().toString();
        datoEditCiudad = editarPerfilCiudad.getText().toString();
        datoEditCalle = editarPerfilCalle.getText().toString();
        datoEditPiso = editarPerfilPiso.getText().toString();
        datoEditPuerta = editarPerfilPuerta.getText().toString();
        //datoEditTelefFijo = Usuario.getTelefono_fijo();
        datoEditCp = editarPerfilCp.getText().toString();

//
        datoEditNomProprietarioTarjecta = editarPerfilNomTitoTarjeta.getText().toString();
        datoEditNumertoTarjecta = editarPerfilNumTitoTarjeta.getText().toString();
        datoEditFechaTarjecta = editarPerfilFechaTarjeta.getText().toString();


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText( getContext(), "Error al actualizar los datos" + error.toString(), Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        // se volve a escribir los dadtos en el archivo y volve a setear los valores en las variables
        PercistenciaUser persi = new PercistenciaUser();
        persi.mantenerUsuarioLoqueado( getContext() );

        Toast.makeText( getContext(), "Se han actualizado los datos correctamente", Toast.LENGTH_LONG ).show();

    }

    private void guardarlosDatosEditados() {

        validarDatosAguardar();

        String fechaUsuario = datoEditFecha;
        String fechaSistema = null;

        String[] fecha = fechaUsuario.split( "/" );
        String dia = fecha[0];
        String mes = fecha[1];
        String anio = fecha[2];

        fechaSistema = anio + "-" + mes + "-" + dia;
        String concecion = Connecion.IP + Connecion.ACTUALIZAR_USUARIO;


        String url = concecion + "nombre=" + datoEditNombre + "&apellidos=" + datoEditApell + "&fecha_nasc=" + fechaSistema + "&calle=" + datoEditCalle + "&pisto=" + datoEditPiso + "&puerta=" + datoEditPuerta + "&ciudad=" + datoEditCiudad +
                "&telefono=" + datoEditTelef + "&codigo_postal=" + datoEditCp + "&provincia=" + datoEditPronvi + "&nomTarjeta=" + datoEditNomProprietarioTarjecta + "&numTarjeta=" + datoEditNumertoTarjecta + "&fechaTarjeta=" + datoEditFechaTarjecta + "&mail=" + Usuario.getEmail();


        Log.d( "cod", url );


        url = url.replace( " ", "%20" );


        JsonRequest jrs = new JsonObjectRequest( Request.Method.DEPRECATED_GET_OR_POST, url, null, this, this );
        rq.add( jrs );

    }

}
