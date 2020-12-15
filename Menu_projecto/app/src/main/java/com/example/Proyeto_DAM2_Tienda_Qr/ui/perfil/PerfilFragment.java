package com.example.Proyeto_DAM2_Tienda_Qr.ui.perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Activity_inicio;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos.DatosPerfil_Fragment;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.usuario.fragmentos.EditarPerfil_Fragment;
import com.example.menu_projecto.R;


public class PerfilFragment extends Fragment implements DatosPerfil_Fragment.OnFragmentInteractionListener,
        EditarPerfil_Fragment.OnFragmentInteractionListener {

    private PerfilViewModel perfilViewModel;

    // variables principais

    private ImageView imgPerfil;
    private ImageView imgPower;
    private ImageView imgEditar;

//    private TextView txtNombre;
//    private TextView txtEmail;

    public static TextView txtNombre;
    public static TextView txtEmail;

    public Button btn_guardarEdit;

    // istanciar los fragmento de auxilio
    DatosPerfil_Fragment datosPerfil;
    EditarPerfil_Fragment editarPerfil;

    public String nom;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of( this ).get( PerfilViewModel.class );

        View root = inflater.inflate( R.layout.fragment_perfil, container, false );

        imgPerfil = (ImageView) root.findViewById( R.id.imgPerfil );
        imgPower = (ImageView) root.findViewById( R.id.imgSerrarSecion );
        imgEditar = (ImageView) root.findViewById( R.id.btn_editar );

        txtNombre = (TextView) root.findViewById( R.id.txtNombrePerfil );
        txtEmail = (TextView) root.findViewById( R.id.textEmailPerfil );


        // instanciar los fragmentos

        datosPerfil = new DatosPerfil_Fragment();
        editarPerfil = new EditarPerfil_Fragment();



        getChildFragmentManager().beginTransaction().add( R.id.contenedorFragmentPerfil, datosPerfil ).commit();



        imgEditar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                switch (v.getId()) {

                    case R.id.btn_editar:
                        transaction.replace( R.id.contenedorFragmentPerfil, editarPerfil );
                        //btn_guardar.setVisibility( View.INVISIBLE );
                        //btn_guardar.setVisibility( View.VISIBLE );
                        break;

                }
                transaction.commit();

            }
        } );


        imgPower.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =  getContext().getSharedPreferences( "percistenciaUser", Context.MODE_PRIVATE );
                //SharedPreferences preferences = getSharedPreferences("percistenciaUser", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent intent = new Intent(getContext(), Activity_inicio.class);
                startActivity(intent);
                //finish();

            }
        } );


        return root;
    }

    public void cambiandoFragment(View view) {

        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        switch (view.getId()) {
//
//            case R.id.btn_editar:
//                transaction.replace( R.id.contenedorFragmentPerfil, editarPerfil );
//                //btn_guardar.setVisibility( View.INVISIBLE );
//                //btn_guardar.setVisibility( View.VISIBLE );
//                break;
//
//        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


//    public void mustaDatosBarPerfil(){
////        txtNombre = (TextView) root.findViewById( R.id.txtNombrePerfil );
////        txtEmail = (TextView) root.findViewById( R.id.textEmailPerfil );
//
//        String nombreBarPerfil = Usuario.getNombre() +" "+Usuario.getApellidos();
//        String mailBarPerfil = Usuario.getEmail();
////        mailUser = Usuario.getEmail();
////        nombreUser = Usuario.getNombre()+ " "+Usuario.getApellidos();
//
////        txtNombre.setText(nombreUser   );
////        txtEmail.setText( mailUser  );
//
//       //txtEmail.setText( nomprePreferencia ); // ha ver se iso funciona
//    }


    // =========================== preferencias



//    private void llerUser21() {
//        Context context = getContext(); // null
//        Usuario User = new Usuario();
//        String fichero = null;
//
//        String texto = null;
////        String nom =null;
////        String ape =null;
//
//        try {
//
//            BufferedReader fin =
//                    new BufferedReader(
//                            new InputStreamReader(
//                                    context.openFileInput( fichero ) ) );
//
//            while ((texto = fin.readLine())!= null){
//
//                String dado = texto.toString();
//
//                dado = dado.replace( "---", "--" );
//                String[] parts = dado.split( "--" );
////                nom = parts[0];
////                ape = parts[1];
//
//                txtNombre.setText( parts[0] + " "+ parts[1]);
//                txtEmail.setText( parts[2] );
//
//
//            }
//
//            // texto = fin.readLine();
//            //System.out.println( "Nombre es PERFIL: "+texto );
//            fin.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            //Log.e( "Ficheros", "Error al leer fichero desde memoria interna" );
//        }
//
//    }



}