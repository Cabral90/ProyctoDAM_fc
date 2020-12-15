package com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.google.android.material.textfield.TextInputLayout;


public class datosResidencia_Fragmento extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnFragmentInteractionListener mListener;

    // variables
    private EditText txtCalle;
    private EditText txtPiso;
    private EditText txtPuerta;
    private EditText txtciudad;
    private EditText txtTelef;
    private EditText txtCp;
    private EditText txtPronvi;

    // spiner
    private Spinner spinerS;

    private CheckBox checkDadosResidencia;

    // variables para los mensajes de errores
    private TextInputLayout errorCalle;
    private TextInputLayout errorPiso;
    private TextInputLayout errorPuerta;
    private TextInputLayout errorCiudad;
    private TextInputLayout errorCodigo_postal;
    private TextInputLayout errortelefono_fijo;
    private TextInputLayout errorProvincia;

    // variables a receber los datos de inputs

    private String calle;
    private String piso;
    private String puerta;
    private String ciudad;
    private String codico_postal;
    private String telefono_fijo;
    private String provincia;

    // isntancia de el usuario
    public Usuario usuario = new Usuario();


    public datosResidencia_Fragmento() {
        // Required empty public constructor
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

        View root = inflater.inflate( R.layout.fragment_dados_residencia__fragmento, container, false );

        txtCalle = (EditText) root.findViewById( R.id.calle );
        txtPiso = (EditText) root.findViewById( R.id.piso );
        txtPuerta = (EditText) root.findViewById( R.id.puerta );
        txtciudad = (EditText) root.findViewById( R.id.ciudad );
        txtTelef = (EditText) root.findViewById( R.id.foneFijo );
        txtCp = (EditText) root.findViewById( R.id.cp );
        txtPronvi = (EditText) root.findViewById( R.id.provincia );

        checkDadosResidencia = (CheckBox) root.findViewById( R.id.checkBoxResidencia );

        // casteo de las variables del mensaje de errores
        errorCalle = (TextInputLayout) root.findViewById( R.id.ImputCalle );
        errorPiso = (TextInputLayout) root.findViewById( R.id.ImputPiso );
        errorPuerta = (TextInputLayout) root.findViewById( R.id.ImputPuerta );
        errorCiudad = (TextInputLayout) root.findViewById( R.id.ImputCiudad );
        errortelefono_fijo = (TextInputLayout) root.findViewById( R.id.ImputFoneResi );
        errorCodigo_postal = (TextInputLayout) root.findViewById( R.id.ImputCP );
        errorProvincia = (TextInputLayout) root.findViewById( R.id.ImputProvincia );

        // spinaer
        spinerS = (Spinner) root.findViewById( R.id.spinner );

        // creando la Array que se llenar con la array de string definida en el achivo direcion.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( getContext(),
                R.array.direcion, android.R.layout.simple_spinner_item );

        // especifica el layout que aparecera la lista a donde se eligira una opcion
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        // aplicando el adapter en el spiner
        spinerS.setAdapter( adapter );
        spinerS.setOnItemSelectedListener( this );

        spinerS.setVisibility( View.INVISIBLE );


        // metodo del buton para validar formulario

        checkDadosResidencia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaSidoClikeado();

                //Toast.makeText( getContext(), "La fecha es: "+fechaInvertida, Toast.LENGTH_LONG ).show();
            }
        } );


        txtCalle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinerS.setVisibility( View.VISIBLE );
            }
        } );


        return root;
    }


    private boolean HaSidoClikeado() {
        boolean click = false;

        if (click = true) {
            recogerLosdatosResidencia();
        }

        return click;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition( position ).toString();
        txtCalle.setText( selectedItem ); // aqui pasamos el texto
        spinerS.setVisibility( View.INVISIBLE );


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void recogerLosdatosResidencia() {

        // receber los inputs y passarles en sus variables

        calle = txtCalle.getText().toString();
        piso = txtPiso.getText().toString();
        puerta = txtPuerta.getText().toString();
        ciudad = txtciudad.getText().toString();
        telefono_fijo = txtTelef.getText().toString();
        codico_postal = txtCp.getText().toString();
        provincia = txtPronvi.getText().toString();

        esUnaCalle( calle );
        esUnNumeroPiso( piso );
        esUnNumeroPuerta( puerta );
        esCiudad( ciudad );
        esTelefonoFijo( telefono_fijo );
        esNumeroCodigoPostal( codico_postal );
        esProvincia( provincia );


    }

    // =======================  VALIDACACIONES DE DATOS DE ENTRADA ===========================//


    private boolean esNumeroCodigoPostal(String txtNumCP) {
        // REver : validaciones de calles
        boolean CpValido = true;

        if (!txtNumCP.isEmpty()) {

            if (txtNumCP.length() <= 6) {
                errorCodigo_postal.setErrorEnabled( false );
                usuario.setCodigoPostal( txtNumCP );
                CpValido = false;
            } else {
                errorCodigo_postal.setErrorEnabled( false );
                errorCodigo_postal.setError( "* EL codigo postal nos es válido" );
            }


        } else {
            errorCodigo_postal.setErrorEnabled( false );
            errorCodigo_postal.setError( "* Campo codigo postal obligatorio" );

        }
        return CpValido;
    }


    private boolean esUnNumeroPiso(String txtNumPiso) {
        // REver : validaciones de calles
        boolean pisoValido = true;

        if (!txtNumPiso.isEmpty()) {
            errorPiso.setErrorEnabled( false );
            usuario.setPiso( txtNumPiso ); // passar el dato
            pisoValido = false;

        } else {
            errorPiso.setErrorEnabled( false );
            errorPiso.setError( "* Campo de la Calle obligatorio" );

        }
        return pisoValido;
    }

    private boolean esUnNumeroPuerta(String txtNumPuerta) {
        // REver : validaciones de calles
        boolean puertaValida = true;

        if (!txtNumPuerta.isEmpty()) {
            errorPuerta.setErrorEnabled( false );
            usuario.setPuerta( txtNumPuerta );
            puertaValida = false;

        } else {
            errorPuerta.setErrorEnabled( false );
            errorPuerta.setError( "* Campo puerta es obligatorio" );

        }
        return puertaValida;
    }


    private boolean esProvincia(String txtProvincia) {

        boolean provinciaValida = true;

        if (!txtProvincia.isEmpty()) {
            errorProvincia.setErrorEnabled( false );
            usuario.setProvincia( txtProvincia ); // passar el dato
            provinciaValida = false;

        } else {
            errorProvincia.setErrorEnabled( false );
            errorProvincia.setError( "* Campo provincia obligatorio" );

        }
        return provinciaValida;
    }


    private boolean esCiudad(String txtCiudad) {
        // REver : validaciones de calles
        boolean ciudadValida = true;

        if (!txtCiudad.isEmpty()) {
            errorCiudad.setErrorEnabled( false );
            usuario.setCiudad( txtCiudad ); // passar el dato
            ciudadValida = false;

        } else {
            errorCiudad.setErrorEnabled( false );
            errorCiudad.setError( "* Campo ciudad obligatorio" );

        }
        return ciudadValida;
    }


    private boolean esUnaCalle(String txtCalle) {
        // REver : validaciones de calles
        boolean calleValido = true;

        if (!txtCalle.isEmpty()) {

            if (!txtCalle.equals( "Elige una Direccion" )) {
                errorCalle.setErrorEnabled( false );
                usuario.setCalle( txtCalle ); // passar el dato
                calleValido = false;

            } else {
                errorCalle.setErrorEnabled( false );
                errorCalle.setError( "* EL nombre de la Calle no es Válido" );
            }


        } else {
            errorCalle.setErrorEnabled( false );
            errorCalle.setError( "* Campo nombre de la Calle es obligatorio" );

        }
        return calleValido;
    }

    private boolean esTelefonoFijo(String txtTelefono) {

        String paternMobil = "^(\\+34|0034|34)?[89]{8}$";

        boolean telefonoValido = true;

        if (!txtTelefono.isEmpty()) {

            if (!txtTelefono.trim().matches( paternMobil ) && txtTelefono.length() == 9) { //
                errortelefono_fijo.setErrorEnabled( false );

                usuario.setTelefono_fijo( txtTelefono );
                telefonoValido = false;
            } else {
                errortelefono_fijo.setErrorEnabled( false );
                errortelefono_fijo.setError( "* EL numero de telefono fijo no es válido" );
            }


        } else {
            errortelefono_fijo.setErrorEnabled( false );
            errortelefono_fijo.setError( "* Campo telefono fijo obligatorio" );

        }
        return telefonoValido;
    }


}
