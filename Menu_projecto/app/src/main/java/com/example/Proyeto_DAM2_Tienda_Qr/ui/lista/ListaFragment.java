package com.example.Proyeto_DAM2_Tienda_Qr.ui.lista;


import android.Manifest;
import android.app.Activity;

import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.Proyeto_DAM2_Tienda_Qr.connecion.Connecion;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Cesta;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.ListaArrays;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Tienda;
import com.example.menu_projecto.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class ListaFragment extends Fragment {

    private ListaViewModel listaViewModel;

    // variables
    Tienda tienda = new Tienda();
    // la ciesta
    Cesta ListCesta = new Cesta();
    ArrayList<Cesta> ProElegigos = new ArrayList<>();

    private ArrayList<Cesta> llenando = new ArrayList<>();

    private RequestQueue requestQueue;

    private TextView nombre;
    private TextView precio;
    private TextView fecha_caducidad;
    private TextView descripcion;


    private ImageView imgProductop;

    private ImageView btnMais;
    private ImageView btnMenos;
    private RadioButton btn_validarPresuposto;
    private Button btn_codQR;

    private Button btnAdd_carrito;
    private TextView cantidadUser;
    private EditText presupuestoUser;
    private TextView restoPresuposto;
    private int resultCantidadUser = 1;

    private ScrollView scrollViewSegundo;
    private LinearLayout linearLayoutPrimer;


    private String valorPresuporUser;
    private double DoublePresupuesto = 0.0;

    // pasar la lista de producto a la otra activity

    private List<Cesta> listaP = new ArrayList<>();


    // camera
    private static final int REQUEST_CODE_QR_SCAN = 101;

    private static final int REQUEST_CAMERA_PERMISSION = 200;
    //private static final int CAMERA_REQUEST = 101;

    private ArrayList<Tienda> productos = new ArrayList<>();

    Cesta listaT = new Cesta();

    // format de double

    private DecimalFormat df = new DecimalFormat( "#.00" );


    //private String codigoQR = null;

    // conexion

    RequestQueue request;


    // construtor Vacio

    public ListaFragment() { // construtor Vacio
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        listaViewModel =
                ViewModelProviders.of( this ).get( ListaViewModel.class );
        final View root = inflater.inflate( R.layout.fragment_lista, container, false );


        nombre = (TextView) root.findViewById( R.id.nombre );
        precio = (TextView) root.findViewById( R.id.precio );

        fecha_caducidad = (TextView) root.findViewById( R.id.fecha_caducidad );
        descripcion = (TextView) root.findViewById( R.id.descripcion );

        imgProductop = (ImageView) root.findViewById( R.id.imageView5 );

        // datos de Compras

        btnMais = (ImageView) root.findViewById( R.id.btn_mas );
        btnMenos = (ImageView) root.findViewById( R.id.btn_menos );
        btnAdd_carrito = (Button) root.findViewById( R.id.btn_Addcesta );

        cantidadUser = (TextView) root.findViewById( R.id.textCantUser );

        btn_codQR = (Button) root.findViewById( R.id.btn_lectorQR );

        linearLayoutPrimer = (LinearLayout) root.findViewById( R.id.layoutPrimer );
        scrollViewSegundo = (ScrollView) root.findViewById( R.id.scrollViwSegundo );

        scrollViewSegundo.setVisibility( View.INVISIBLE );


        request = Volley.newRequestQueue( getContext() );

        // Botones


        btn_codQR.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                scnnerProducto();


            }
        } );


        linearLayoutPrimer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scnnerProducto();

            }
        } );

        btnAdd_carrito.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // metodo que añade los producto en el carro.
                anadirProCesta();

            }
        } );

        btnMais.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // decrementar la cantidad elegida por el usuario

                resultCantidadUser++;

                String cantidadOf = Integer.valueOf( resultCantidadUser ).toString();

                cantidadUser.setText( cantidadOf );

            }
        } );

        btnMenos.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // restar la cantidad elegida por el usuario

                String cant = cantidadUser.getText().toString();
                Integer result = Integer.parseInt( cant );

                if (result > 1) {
                    resultCantidadUser--;
                    String cantidadOf = Integer.valueOf( resultCantidadUser ).toString();
                    cantidadUser.setText( cantidadOf );

                } else {
                    resultCantidadUser = 1;
                    String cantidadOf = Integer.valueOf( resultCantidadUser ).toString();
                    cantidadUser.setText( cantidadOf );

                }


            }
        } );


        return root;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText( getContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT ).show();
            String resultado = data.getStringExtra( "com.blikoon.qrcodescanner.error_decoding_image" );
            if (resultado != null) {
                Toast.makeText( getContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT ).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra( "com.blikoon.qrcodescanner.got_qr_scan_relult" );


                buscarProducto( Connecion.IP + Connecion.CONSULTA + lectura );
                //codigoQR = lectura;
                //Toast.makeText( getContext(), "Leído: " + lectura, Toast.LENGTH_LONG ).show(); // este pilla la lectura

                if (lectura != null) {
                    linearLayoutPrimer.setVisibility( View.INVISIBLE );
                    scrollViewSegundo.setVisibility( View.VISIBLE );
                    Toast.makeText( getContext(), "Leído: " + lectura, Toast.LENGTH_LONG ).show(); // este pilla la lectura
                }

            }
        }
    }


    private void buscarProducto(final String URL) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject( i );

//
                        tienda.setNombre( jsonObject.optString( "nombre" ) );
                        tienda.setPrecio( jsonObject.optString( "precios" ) );

                        String fechaSistema = jsonObject.optString( "fecha_caducidad" );

                        String []valor = fechaSistema.split( "-" );
                        String anio = valor[0];
                        String mes = valor[1];
                        String dia = valor[2];
                        String fechaCaducidadProducto = dia + "/" + mes + "/" + anio;
                        tienda.setFecha_caducidad( fechaCaducidadProducto );
                        tienda.setDescripcion( jsonObject.optString( "descripcion" ) );
                        tienda.setDato( jsonObject.optString( "imagen" ) );
                        // SETEANDO LOS VALORES

                        nombre.setText( "Nombre: " + tienda.getNombre() );
                        precio.setText( "Precio: " + tienda.getPrecio() + " €" );

                        fecha_caducidad.setText( "Fecha de Caducidad: " + tienda.getFecha_caducidad() );
                        descripcion.setText( "Descripcion: " + tienda.getDescripcion() );

                        if (tienda.getImagen() != null) {
                            imgProductop.setImageBitmap( tienda.getImagen() );
                        } else {
                            Uri uriImage = Uri.parse( "android.resource://" + getContext().getPackageName() + "/" + R.drawable.qr ); // rever eso
                            imgProductop.setImageURI( uriImage );
                        }

                        // Rellenar la Array de productos

                         productos.add( tienda );

                    } catch (JSONException e) {

                        Toast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getContext(), "No existe el Producto", Toast.LENGTH_LONG ).show();
            }
        } );

        requestQueue = Volley.newRequestQueue( getContext() );
        requestQueue.add( jsonArrayRequest );
    }

    private void anadirProCesta() {

//
        llenando = ListaArrays.PRODUCTOS_ELEGIDOS;


        if (tienda.getNombre() != null) {


            String cantidProducto = cantidadUser.getText().toString();

            double Subtotal = subtotalPro();

            String subtTOTAL = String.valueOf( Subtotal );


            llenando.add( new Cesta( tienda.getNombre(), tienda.getPrecio(), cantidProducto,
                    tienda.getDescripcion(), subtTOTAL, tienda.getImagen() ) );


            Toast.makeText( getContext(), "Añadido con suceso al carrito ", Toast.LENGTH_LONG ).show();


        } else {
            Toast.makeText( getContext(), "Scanea el codigo por favor", Toast.LENGTH_LONG ).show();


        }
    }


    private double subtotalPro() {

        double DoubleSubtotal = 0;

        for (Tienda ciestaPro : productos) {
            String StringPrecio = ciestaPro.getPrecio();
            DoubleSubtotal = Double.parseDouble( StringPrecio );
        }
        DoubleSubtotal *= resultCantidadUser;
        return DoubleSubtotal;
    }

    private void scnnerProducto() {

//        ActivityCompat.requestPermissions(activity,
//                new String[]{Manifest.permission.CAMERA},
//                PERMISSIONS_CAMERA);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            ActivityCompat.requestPermissions( Objects.requireNonNull( getActivity() ), new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION );

            Intent i = new Intent( getActivity(), QrCodeActivity.class ); // implementar la libraria en el gradele
            startActivityForResult( i, REQUEST_CODE_QR_SCAN );

        }
    }


}


