package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.menu_projecto.R;

import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.Proyeto_DAM2_Tienda_Qr.util.PercistenciaUser;

import static com.example.Proyeto_DAM2_Tienda_Qr.ui.perfil.PerfilFragment.txtEmail;
import static com.example.Proyeto_DAM2_Tienda_Qr.ui.perfil.PerfilFragment.txtNombre;


public class DatosPerfil_Fragment extends Fragment {

    // variables
    private TextView perfilNombre;
    private TextView perfilApellido;
    private TextView perfilEmail;
    private TextView perfilTelefono;
    private TextView perfilFecha;

    private TextView perfilCiudad;
    private TextView perfilProvincia;
    private TextView perfilCalle;
    private TextView perfilPiso;
    private TextView perfilPuerta;
    private TextView perfilCp;

    private TextView perfilNomTitoTarjeta;
    private TextView perfilNumTitoTarjeta;
    private TextView perfilFechaTarjeta;


    // conex
    private String mail;

    public static String nombreUser;
    public static String mailUser;


    private OnFragmentInteractionListener mListener;


    public DatosPerfil_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DatosPerfil_Fragment newInstance(String param1, String param2) {
        DatosPerfil_Fragment fragment = new DatosPerfil_Fragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
//            mParam1 = getArguments().getString( ARG_PARAM1 );

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate( R.layout.fragment_datos_perfil_, container, false );

        // cast de las varibales


        perfilNombre = (TextView) root.findViewById( R.id.textNombrePerVisual );
        perfilApellido = (TextView) root.findViewById( R.id.textApellidoPerVisual );
        // perfilEmail=(TextView) root.findViewById( R.id.textApellidoPerVisual );
        perfilTelefono = (TextView) root.findViewById( R.id.textFonePerVisual );
        perfilFecha = (TextView) root.findViewById( R.id.textFecha_nascPerVisual );

        perfilProvincia = (TextView) root.findViewById( R.id.texProvinciaPerVisual );
        perfilCiudad = (TextView) root.findViewById( R.id.texCiudadPerVisual );
        perfilCalle = (TextView) root.findViewById( R.id.textCallePerVisual );
        perfilPiso = (TextView) root.findViewById( R.id.texPisoPerVisual );
        perfilPuerta = (TextView) root.findViewById( R.id.texPuertaPerVisual );
        perfilCp = (TextView) root.findViewById( R.id.texCpPerVisual );


        perfilNomTitoTarjeta = (TextView) root.findViewById( R.id.texNomTitularTargetaPerVisual );
        perfilNumTitoTarjeta = (TextView) root.findViewById( R.id.texNumTargetaPerVisual );
        perfilFechaTarjeta = (TextView) root.findViewById( R.id.texFechaTargetaPerVisual );

        pasarLosdatosDelPerfil();

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


    private void pasarLosdatosDelPerfil() {


        // variables intermedias armazenan los datos del usuario


        if (Usuario.getNombre() == null) {
            PercistenciaUser per = new PercistenciaUser();
            per.lerUser22( getContext() );
        }


        String nombrePrefil = Usuario.getNombre();
        String apellidoPerfil = Usuario.getApellidos();
        String telefonoPerfil = Usuario.getTelefono();
        String fechaPerfil = Usuario.getFecha_nasc();
        String provinciaPerfil = Usuario.getProvincia();
        String ciudadPerfil = Usuario.getCiudad();
        String callePerfil = Usuario.getCalle();
        String pisoPerfil = Usuario.getPiso();
        String puertaPerfil = Usuario.getPuerta();
        String cpPerfil = Usuario.getCodigoPostal();

        String nomTitoTarjetaPerfil = Usuario.getNombreProprietarioTarjeta();
        String numTarjetaPerfil = Usuario.getNumeroTarjeta();
        String fechaTarjetaPerfil = Usuario.getFechaCaducidadTarjeta();


        // seteando los valores en sus posiciones del perfil del usuario

        perfilNombre.setText( "Nombre: " + nombrePrefil );
        perfilApellido.setText( "Apellido: " + apellidoPerfil );
        perfilTelefono.setText( "Telefono: " + telefonoPerfil );
        perfilFecha.setText( "Fecha nascimineto: " + fechaPerfil );


        perfilProvincia.setText( "Provincia: " + provinciaPerfil );
        perfilCiudad.setText( "Ciudad: " + ciudadPerfil );
        perfilCalle.setText( "Calle: " + callePerfil );
        perfilPiso.setText( "Piso: " + pisoPerfil );
        perfilPuerta.setText( "Puerta: " + puertaPerfil );
        perfilCp.setText( "C.P: " + cpPerfil );

        perfilNomTitoTarjeta.setText( "Titular tarjeta de Pago: " + nomTitoTarjetaPerfil );
        perfilNumTitoTarjeta.setText( "Tarjeta Nº: " + numTarjetaPerfil );
        perfilFechaTarjeta.setText( "Fecha validad de la tarjeta: " + fechaTarjetaPerfil );

        //  datos del bannerPERFIL

        txtNombre.setText( Usuario.getNombre() + " " + Usuario.getApellidos() );
        txtEmail.setText( Usuario.getEmail() );


    }

}
