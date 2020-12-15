package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import  static com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.activitys.Activity_Registro.btn_guardar;


public class datosPersonal_Fragmento extends Fragment {

    private OnFragmentInteractionListener mListener;


    // variables

    private EditText txtNom;
    private EditText txtapell;
    private EditText txtEmal;
    private EditText txtFone;
    private EditText txtPass1;
    private EditText txtPass2;
    private EditText txtfecha;
    private Button checkBox;

    public static CheckBox checkBox2;

    // imputs de alert de errores
    private TextInputLayout errorNombre;
    private TextInputLayout errorApellido;
    private TextInputLayout errorMail;
    private TextInputLayout errorPass1;
    private TextInputLayout errorPass2;
    private TextInputLayout errorfone;
    private TextInputLayout errorFecha;


    // isntancia de el usuario
    public Usuario usuario = new Usuario();


    // variabeles a passar lso datos.

    private String nombre;
    private String apellidos;
    private String mail;
    private String pass1;
    private String pass2;
    private String fone;
    private String fecha;

    // fecha
    Calendar calendario = Calendar.getInstance();
    public String fechaInvertida;


    public datosPersonal_Fragmento() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static datosPersonal_Fragmento newInstance(String param1, String param2) {
        datosPersonal_Fragmento fragment = new datosPersonal_Fragmento();
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

        View root = inflater.inflate( R.layout.fragment_datos_personal__fragmento, container, false );

        //cast variables

        txtNom = (EditText) root.findViewById( R.id.nom );
        txtapell = (EditText) root.findViewById( R.id.apellido );
        txtEmal = (EditText) root.findViewById( R.id.email );
        txtFone = (EditText) root.findViewById( R.id.fone );
        txtPass1 = (EditText) root.findViewById( R.id.pass1 );
        txtPass2 = (EditText) root.findViewById( R.id.pass2 );
        txtfecha = (EditText) root.findViewById( R.id.fecha );
        // checkBox = (Button) root.findViewById( R.id.checkBox );

        checkBox2 = (CheckBox) root.findViewById( R.id.checkBox2 );

        // casteo de las variables de errores
        errorNombre = (TextInputLayout) root.findViewById( R.id.ImpNom );
        errorApellido = (TextInputLayout) root.findViewById( R.id.ImputApellidos );
        errorMail = (TextInputLayout) root.findViewById( R.id.ImputEmail );
        errorfone = (TextInputLayout) root.findViewById( R.id.ImputFoneResi );
        errorPass1 = (TextInputLayout) root.findViewById( R.id.ImputPass1 );
        errorPass2 = (TextInputLayout) root.findViewById( R.id.ImputPass2 );
        errorFecha = (TextInputLayout) root.findViewById( R.id.ImputFecha );


        btn_guardar.setVisibility( View.INVISIBLE );

        checkBox2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HasidoClikeado();


                //Toast.makeText( getContext(), "La fecha es: "+fechaInvertida, Toast.LENGTH_LONG ).show();
            }
        } );

        //checkBox2.setClickable( HasidoClikeado() );


        txtfecha.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( getContext(), date, calendario
                        .get( Calendar.YEAR ), calendario.get( Calendar.MONTH ), calendario.get( Calendar.DAY_OF_MONTH ) ).show();
            }
        } );


        return root;
    }

    public boolean HasidoClikeado() {
        boolean click = false;

        if (click = true) {
            recogerLosdatos();

        }

        return click;
    }


    private void recogerLosdatos() { // implementar un hilo para renderizar el proceso

        // coger los datos de entrada del usuario

        nombre = txtNom.getText().toString();
        apellidos = txtapell.getText().toString();
        mail = txtEmal.getText().toString();
        pass1 = txtPass1.getText().toString();
        pass2 = txtPass2.getText().toString();
        fone = txtFone.getText().toString();
        fecha = txtfecha.getText().toString();

        // pasar los datos validos
        esNombre( nombre );
        esApellido( apellidos );
        esEmail( mail );
        esPassword( pass1, pass2 );
        esTelefono( fone );
       // esTelefono( );
        usuario.setFecha_nasc( fechaInvertida );

       // esFecha( fechaInvertida ); // este da error asi que poner la fecha directo

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


    // =======================  VALIDACACIONES DE DATOS DE ENTRADA ===========================//

    private boolean esNombre(String txtNombre) {
        boolean nombreValido = true;

        if (!txtNombre.isEmpty()) {
            errorNombre.setErrorEnabled( false );
            usuario.setNombre( txtNombre ); // passar el dato
            nombreValido = false;

        } else {
            errorNombre.setErrorEnabled( false );
            errorNombre.setError( "* Campo Nombre obligatorio" );

        }
        return nombreValido;
    }


    private boolean esApellido(String txtApellidos) {
        boolean apellidoValido = true;

        if (!txtApellidos.isEmpty()) {
            errorApellido.setErrorEnabled( false );


            usuario.setApellidos( txtApellidos ); // pasar el dato
            apellidoValido = false;

        } else {
            errorApellido.setErrorEnabled( false );
            errorApellido.setError( "* Campo Apellido obligatorio" );

        }
        return apellidoValido;
    }

    private boolean esEmail(String txtEmail) {
        boolean emailValido = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!txtEmail.isEmpty()) { // && txtEmail.trim().matches( emailPattern )

            if (txtEmail.trim().matches( emailPattern )) {
                errorMail.setErrorEnabled( false );

                usuario.setEmail( txtEmail ); // passar el dato
                emailValido = false;
            } else {
                errorMail.setErrorEnabled( false );
                errorMail.setError( "* Emial no valido" );
            }

        } else {
            errorMail.setErrorEnabled( false );
            errorMail.setError( "* Campo Email obligatorio" );
        }
        return emailValido;
    }


    private boolean esPassword(String txtpass1, String txtpass2) {
        boolean contraseniaValido = true;

        if (!txtpass1.isEmpty() && !txtpass2.isEmpty()) {

            if (txtpass1.equals( txtpass2 )) {

                usuario.setPassword( txtpass2 );
                errorPass1.setErrorEnabled( false );
                errorPass2.setErrorEnabled( false );

                contraseniaValido = false;

            } else {
                errorPass1.setErrorEnabled( false );
                errorPass1.setError( "* Las contraseña no considen." );

                errorPass2.setErrorEnabled( false );
                errorPass2.setError( "* Las contraseña no considen." );

            }

        } else {
            errorPass1.setErrorEnabled( false );
            errorPass1.setError( "* Campo Contraseña obligatorio" );

            errorPass2.setErrorEnabled( false );
            errorPass2.setError( "* Confirme la contraseña considen" );
        }
        return contraseniaValido;
    }

    private boolean esFecha(String txtFecha) {

        boolean fechaValido = true;

        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy", Locale.getDefault() );
        Date date = new Date();
        String fecha = dateFormat.format( date );

        if (!txtFecha.isEmpty()) {

            errorFecha.setErrorEnabled( false );
            usuario.setFecha_nasc( txtFecha );
            fechaValido = false;

        } else {
            errorFecha.setErrorEnabled( false );
            errorFecha.setError( "* Campo Nombre obligatorio" );

        }
        return fechaValido;
    }


    private boolean esTelefono(String txtTelefono) {

        String paternMobil = "^(\\+34|0034|34)?[89]{8}$";


        boolean telefonoValido = true;

        if (!txtTelefono.isEmpty()) {

            if (!txtTelefono.trim().matches( paternMobil ) && txtTelefono.length() == 9) { //
                errorfone.setErrorEnabled( false );

                usuario.setTelefono( txtTelefono ); // passar datos
                telefonoValido = false;
            } else {
                errorfone.setErrorEnabled( false );
                errorfone.setError( "* EL numero de telefono no es válido" );
            }


        } else {
            errorfone.setErrorEnabled( false );
            errorfone.setError( "* Campo Telefono obligatorio" );

        }
        return telefonoValido;
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set( Calendar.YEAR, year );
            calendario.set( Calendar.MONTH, monthOfYear );
            calendario.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            actualizarInput();
        }

    };

    private void actualizarInput() {
        String fechaSistema = "yyyy-MM-dd";
        String formatoDeFecha = "dd/MM/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat( formatoDeFecha, Locale.getDefault() );  // Locale.US

        SimpleDateFormat sdf2 = new SimpleDateFormat( fechaSistema, Locale.getDefault() );
        fechaInvertida = sdf2.format( calendario.getTime() );

        txtfecha.setText( sdf.format( calendario.getTime() ) );
    }


}
