package com.example.Proyeto_DAM2_Tienda_Qr.entidad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_projecto.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EjemploViewHolder> {

    private ArrayList<Cesta> myEjemploLista;
    private OnItemClickListener myListener;

    public String CantidadEtitando;
    public String cantidadFinal;


    public String CANTIDAD_Of = "1";

    public static int CANTIDAD1 = 1;
    public static String PRECIOP;
    public static String SUBTOTALP;

    public double total;

    public interface OnItemClickListener {

        void onDeleteClick(int posicion);

        void calculosCantidadYsubTotal(int posicion);


    }

    //
    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    public class EjemploViewHolder extends RecyclerView.ViewHolder {

        public TextView nomPro, precioPro, subTotalPro, descripcionPro, cantidadUser2;
        public ImageView imgPro;
        public ImageView btn_boorar, btn_Mais, btn_Menos;


        private DecimalFormat df2 = new DecimalFormat( "#.00" );

        //public Button btn_Mais, btn_Menos;

        // outros

        public EjemploViewHolder(@NonNull View itemView, final OnItemClickListener listener) { // , final OnItemClickListener listener
            super( itemView );

            nomPro = (TextView) itemView.findViewById( R.id.nombreR );
            precioPro = (TextView) itemView.findViewById( R.id.precioR );
            subTotalPro = (TextView) itemView.findViewById( R.id.subTotalR );
            descripcionPro = (TextView) itemView.findViewById( R.id.descripcionR );
            imgPro = (ImageView) itemView.findViewById( R.id.imagProdutoR );

            btn_boorar = (ImageView) itemView.findViewById( R.id.imageViewDeleteR );

            // botones cantidad
            btn_Mais = (ImageView) itemView.findViewById( R.id.btn_mas2 );
            btn_Menos = (ImageView) itemView.findViewById( R.id.btn_menos2 );
            cantidadUser2 = (TextView) itemView.findViewById( R.id.textCantUser2 );


            btn_boorar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int posicion = getAdapterPosition();
                        if (posicion != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick( posicion );
                        }
                    }

                }

            } );


            // butones cantidades


            btn_Mais.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // cantidad
                    CantidadEtitando = cantidadUser2.getText().toString();

                    int v2 = Integer.parseInt( CantidadEtitando );
                    CANTIDAD1 = v2;

                    CANTIDAD1++;

                    // Subtotal y precio

                    String precioP = precioPro.getText().toString();

                    double precio = Double.parseDouble( precioP );


                    // calculos
                    double SubTOTAL = precio * CANTIDAD1; // aqui tenemos el subtotal

                    // allar la cantidad

                    SUBTOTALP = transformaValor( SubTOTAL );
                    PRECIOP = String.valueOf( precio );


                    if (listener != null) {
                        int posicion = getAdapterPosition();
                        if (posicion != RecyclerView.NO_POSITION) {
                            listener.calculosCantidadYsubTotal( posicion );
                        }
                    }


                }
            } );

            btn_Menos.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CantidadEtitando = cantidadUser2.getText().toString();

                    int v2 = Integer.parseInt( CantidadEtitando );
                    CANTIDAD1 = v2;


                    if (CANTIDAD1 > 1) {
                        CANTIDAD1--;

                        String precioP = precioPro.getText().toString();


                        double precio = Double.parseDouble( precioP );

                        // calculos
                        double SubTOTAL = precio * CANTIDAD1;

                        SUBTOTALP = transformaValor( SubTOTAL );

                        PRECIOP = String.valueOf( precio );

                    }


                    if (listener != null) {
                        int posicion = getAdapterPosition();
                        if (posicion != RecyclerView.NO_POSITION) {
                            listener.calculosCantidadYsubTotal( posicion );

                        }
                    }


                }
            } );


        }


    }


    public RecyclerViewAdapter(ArrayList<Cesta> myEjemploLista) {
        this.myEjemploLista = myEjemploLista;
    }

    @NonNull
    @Override
    public EjemploViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.lista_productos_row, parent, false );
        EjemploViewHolder evh = new EjemploViewHolder( view, myListener ); // , myListener


        return evh;
    }


    private String transformaValor(double valor) {
        DecimalFormat df = new DecimalFormat( "#.00" );

        String valorFormatado = null;
        valorFormatado = df.format( valor );

        return valorFormatado;
    }

    @Override
    public void onBindViewHolder(@NonNull EjemploViewHolder holder, int position) {

        Cesta currentItem = myEjemploLista.get( position );

        holder.nomPro.setText( currentItem.getNombre() );
        holder.precioPro.setText( currentItem.getPrecio() );
        holder.subTotalPro.setText( currentItem.getSubTotal() );
        holder.descripcionPro.setText( currentItem.getDescripcion() );
        holder.imgPro.setImageBitmap( currentItem.getImagen() );
        holder.cantidadUser2.setText( currentItem.getCantidad_User() );

    }

    @Override
    public int getItemCount() {
        return myEjemploLista.size();
    }

}
