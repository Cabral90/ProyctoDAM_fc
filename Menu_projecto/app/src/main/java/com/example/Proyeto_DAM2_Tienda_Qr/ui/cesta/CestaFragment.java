package com.example.Proyeto_DAM2_Tienda_Qr.ui.cesta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Cesta;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.ListaArrays;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.RecyclerViewAdapter;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Activity_pago;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Activity_pago_envioCasa;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.AsistenciaTienda;
import com.example.menu_projecto.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CestaFragment extends Fragment {


    public String cantidadProdcuto = "1";

    public static String PASAR_TOTAL;
    public static String CANTIDAD_PRODUTO = null;


    public ArrayList<Cesta> productosElegidos;

    // setando el total geneta le la variable
    public static DecimalFormat df = new DecimalFormat( "#,00" );

    // assistencia
    private AsistenciaTienda tienda = new AsistenciaTienda();

    private int cantidad;

    // objectos y variables enviadas por putExtra desde Activity Compra
    ArrayList<Cesta> listaProductos;

    private RecyclerView myLista;
    private RecyclerViewAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    public int CODIGO_BORRAR;

    // Butones

    private Button btn_recogerTienda;
    private Button btn_enviarAcasa;
    // textos
    public static TextView txt_totalGenetal;
    private TextView contadorProductos;
    private TextView txt_RestoPresupuesto;

    private String cantString = null;

    private Button btn_anadir_pro;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_cesta, container, false );



        btn_recogerTienda = (Button) root.findViewById( R.id.btn_facturar );
        btn_enviarAcasa = (Button) root.findViewById( R.id.btn_irCasa);
        txt_totalGenetal = (TextView) root.findViewById( R.id.textTotalG ); // totalProCesta
        contadorProductos = (TextView) root.findViewById( R.id.totalProCesta );


        // dar vida a la lista
        listaProductos = new ArrayList<>();

        // llena RecyclerView
        //rellenarRecyclerView();

        myLista = (RecyclerView) root.findViewById( R.id.recyclerView );
        myLista.setHasFixedSize( true );

        myLayoutManager = new LinearLayoutManager( getContext() );


        myAdapter = new RecyclerViewAdapter( ListaArrays.PRODUCTOS_ELEGIDOS); // listaProductos

        myLista.setLayoutManager( myLayoutManager );
        myLista.setAdapter( myAdapter ); // listaProductos



        TotalGeneralCesta( ListaArrays.PRODUCTOS_ELEGIDOS ); // carga el total

        if (ListaArrays.PRODUCTOS_ELEGIDOS.size()==0){
            contadorProductos.setText( "Productos existente en la Cesta(0)");
            txt_totalGenetal.setText( "Total: 00,00 €" );

            //tienda.llerDatosCesta( getContext() );

        }


//        if(cantString.equals( "1" )){
//           // btn_boorar.setV
//           // btn_boorar.setVisibility( View.VISIBLE );
//        }


        myAdapter.setOnItemClickListener( new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int posicion) {
                borrarItem( posicion );

            }


            @Override
            public void calculosCantidadYsubTotal(int posicion) {

                // CANTIDAD
                int valorCant = RecyclerViewAdapter.CANTIDAD1;
                 cantString = Integer.valueOf( valorCant ).toString();

                calculos( posicion, cantString, RecyclerViewAdapter.PRECIOP, RecyclerViewAdapter.SUBTOTALP );
            }

        } );


        btn_recogerTienda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                tienda.guadarDatosCesta(  getContext(),  ListaArrays.PRODUCTOS_ELEGIDOS ); //
//                tienda.llerDatosCesta( getContext() );

                Intent intent = new Intent( getContext(), Activity_pago.class ); // class reneral
                startActivity( intent );
//
            }
        } );


        btn_enviarAcasa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getContext(), Activity_pago_envioCasa.class ); // class reneral
                startActivity( intent );
            }
        } );


        return root;
    }


    // metodos auxiliares


    private void borrarItem(int posicion) {

        ListaArrays.PRODUCTOS_ELEGIDOS.remove( posicion ); // listaProductos.remove( posicion );
        myAdapter.notifyItemRemoved( posicion );

        TotalGeneralCesta( ListaArrays.PRODUCTOS_ELEGIDOS );

    }

    private void calculos(int posicion, String cantidad, String precioP, String Subtotal) {

        ListaArrays.PRODUCTOS_ELEGIDOS.get( posicion ).calculosVaDatos( cantidad, precioP, Subtotal );
        myAdapter.notifyItemChanged( posicion );
        TotalGeneralCesta( ListaArrays.PRODUCTOS_ELEGIDOS );

    }


    //===========================




    private void TotalGeneralCesta(ArrayList<Cesta> productosElegidos1) { // recorre el total e actualiza el mismo
        DecimalFormat df2 = new DecimalFormat( "#.00" );
        double total = 0;
        String StringTotal = null;

       String StringContaProduto = null;
       int intCatidadProduto = 0;

        if (productosElegidos1.size() == 0) {
            contadorProductos.setText( "Productos existente en la Cesta(0)");
            txt_totalGenetal.setText( "Total: 00,00 €" );

        } else {
            for (Cesta cesta : productosElegidos1) {

                StringTotal =  cesta.getSubTotal();
                StringContaProduto = cesta.getCantidad_User();

                StringTotal = StringTotal.replace( ",", "." ); // importante para remplacar la comilla por punto

                total += Double.parseDouble( StringTotal );
                intCatidadProduto +=Integer.parseInt( StringContaProduto );
            }
            CANTIDAD_PRODUTO = String.valueOf( intCatidadProduto );
            System.out.println( "Catntida de productos: "+CANTIDAD_PRODUTO );
            contadorProductos.setText( "Productos existente en la Cesta("+CANTIDAD_PRODUTO +")");
            String TotalString = String.valueOf( df2.format(total) ); //
            PASAR_TOTAL = TotalString;
            txt_totalGenetal.setText( "Total: " + TotalString + "€" );
        }

    }



}