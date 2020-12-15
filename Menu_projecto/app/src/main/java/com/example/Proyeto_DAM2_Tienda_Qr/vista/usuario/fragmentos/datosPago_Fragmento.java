package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.Proyeto_DAM2_Tienda_Qr.util.ValidarInpustEntrada;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import static com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys.Activity_Registro.btn_guardar;


public class datosPago_Fragmento extends Fragment {


    private OnFragmentInteractionListener mListener;

    // variables

    private EditText txtNomProprietarioTarjeta;
    private EditText txtNumertoTarjeta;
    private EditText txtFechaTarjeta;

    // decladando las variables paa los mensajes de errores
    private TextInputLayout errorNomProprietarioTarjeta;
    private TextInputLayout errorNumeroTarjeta;
    private TextInputLayout errorFechaTarjeta;

    // variables de recepcion de los inputis

    private String nombreProTarjeta;
    private String numeroTarjeta;
    private String fechaTarjeta;


    private CheckBox checkBoxPago;

    Calendar calendario = Calendar.getInstance();
    public String fechaInvertida;

    // isntancia de el usuario
    public Usuario usuario = new Usuario();

    public datosPago_Fragmento() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static datosPago_Fragmento newInstance(String param1, String param2) {
        datosPago_Fragmento fragment = new datosPago_Fragmento();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate( R.layout.fragment_datos_pago__fragmento, container, false );
        // cast de variables

        txtNomProprietarioTarjeta = (EditText) root.findViewById( R.id.nomTarjecta );
        txtNumertoTarjeta = (EditText) root.findViewById( R.id.numTarjecta );
        txtFechaTarjeta = (EditText) root.findViewById( R.id.fechaTarjecta );

        // casteandol as variables de mensaje de errores
        errorNomProprietarioTarjeta = (TextInputLayout) root.findViewById( R.id.inputNomTarjeta );
        errorNumeroTarjeta = (TextInputLayout) root.findViewById( R.id.inputNumerTarjeta );
        errorFechaTarjeta = (TextInputLayout) root.findViewById( R.id.inputFechaTarjeta );

        checkBoxPago = (CheckBox) root.findViewById( R.id.checkBoxPago );

        checkBoxPago.setVisibility( View.INVISIBLE ); // se crea e se torna invisible


        // activar el buton check

        checkBoxPago.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HasidoClikeado();

                btn_guardar.setVisibility( View.VISIBLE );
            }
        } );


        txtFechaTarjeta.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( getContext(), date, calendario
                        .get( Calendar.YEAR ), calendario.get( Calendar.MONTH ), calendario.get( Calendar.DAY_OF_MONTH ) ).show();

                checkBoxPago.setVisibility( View.VISIBLE );
            }
        } );


        return root;
    }


//    public void recogerLosdatos() {
//
//    }


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

    // ==========================  Metodos Mios  ==============================//

    private boolean HasidoClikeado() {
        boolean click = false;

        if (click = true) {
            recogerLosdatosPago();

        }

        return click;
    }

    private void recogerLosdatosPago() {

        // pasando los valores de inputs a la variables
        nombreProTarjeta = txtNomProprietarioTarjeta.getText().toString();
        numeroTarjeta = txtNumertoTarjeta.getText().toString();
        fechaTarjeta = txtFechaTarjeta.getText().toString();

        // pasando los valores al objecto usuario

        esNombreProTarjeta( nombreProTarjeta );
        esNumeroTarjeta( numeroTarjeta );
        esFechaTarjeta( fechaInvertida );

    }


    // =======================  VALIDACACIONES DE DATOS DE ENTRADA ===========================//


    private boolean esNombreProTarjeta(String txtNombre) {

        boolean nombreValido = true;


        if (!txtNombre.isEmpty()) {
            errorNomProprietarioTarjeta.setErrorEnabled( false );
            usuario.setNombreProprietarioTarjeta( txtNombre ); // passar el dato
            nombreValido = false;

        } else {
            errorNomProprietarioTarjeta.setErrorEnabled( false );
            errorNomProprietarioTarjeta.setError( "* Campo nombre obligatorio" );

        }
        return nombreValido;
    }


    private boolean esNumeroTarjeta(String txtNumeroTarjeta) {
        // REver : validaciones de calles
        boolean numeroTarjetaValido = true;

        if (!txtNumeroTarjeta.isEmpty()) {

            if (txtNumeroTarjeta.length() == 16) {
                errorNumeroTarjeta.setErrorEnabled( false );
                usuario.setNumeroTarjeta( txtNumeroTarjeta ); // passar el dato
                numeroTarjetaValido = false;
            } else {
                errorNumeroTarjeta.setErrorEnabled( false );
                errorNumeroTarjeta.setError( "* Campo Nº de tarjeta no es vaálido" );

            }


        } else {
            errorNumeroTarjeta.setErrorEnabled( false );
            errorNumeroTarjeta.setError( "* Campo Nº de tarjeta obligatorio" );

        }
        return numeroTarjetaValido;
    }

    private void esFechaTarjeta(String txtFechaTarjeta) {

        boolean fechaValida =  ValidarInpustEntrada.esFechaValida( txtFechaTarjeta );

        if (!txtFechaTarjeta.isEmpty() && (fechaValida)) {

            errorFechaTarjeta.setErrorEnabled( false );
            usuario.setFechaCaducidadTarjeta( txtFechaTarjeta );

        } else {
            errorFechaTarjeta.setErrorEnabled( false );
            errorFechaTarjeta.setError( "* Fecha no válida" );

        }

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                 int dayOfMonth ) { //
            // TODO Auto-generated method stub
            calendario.set( Calendar.YEAR, year );
            calendario.set( Calendar.MONTH, monthOfYear );
            calendario.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            actualizarInput();
        }

    };

    private void actualizarInput() {
        String fechaSistema = "MM/yy"; // pater para el Sistema

        SimpleDateFormat sdf2 = new SimpleDateFormat( fechaSistema, Locale.getDefault() );
        fechaInvertida = sdf2.format( calendario.getTime() );

        txtFechaTarjeta.setText( sdf2.format( calendario.getTime() ) );
    }

}
