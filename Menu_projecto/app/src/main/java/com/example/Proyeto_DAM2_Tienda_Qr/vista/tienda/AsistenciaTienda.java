package com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Cesta;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AsistenciaTienda {
    private String archivo = "factura.txt";
    private String persistenciaPro = "listaProducto.txt";
    private  double IVA = 0.99;
    private double TAXA_DE_TRANSPORTE = 2.99;

    private DecimalFormat df2 = new DecimalFormat( "#.00" );

    public void escribirProductos(Context context, ArrayList<Cesta> cesta, String total) {

        // sacar la fecha actural
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy", Locale.getDefault() ); // "yyyy-MM-dd"
        Date date = new Date();

        String fecha = dateFormat.format( date );

        String[]diaMes = fecha.split( "/" );
        String dia = diaMes[0];
        String mes = diaMes[1];

        String StringNumero = dia+"/"+mes;

        // sacar la hora actural
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat( "HH:mm" ); // "EEE, d MMM yyyy, HH:mm"
        String hora = df.format( Calendar.getInstance().getTime() );

        // sumar las variables

        String StringTotal = total.replace( ",", "." );
        double DoubleTotal = Double.parseDouble( StringTotal); //
        double totalG = DoubleTotal+IVA+TAXA_DE_TRANSPORTE;

        System.out.println( "Total String1: "+StringTotal );
        System.out.println( "Total Double: "+DoubleTotal );

        String valorTotalGeneral = String.valueOf(df2.format( totalG ));

        System.out.println( "TOTAL G: "+totalG );

        System.out.println( "Valor General: "+valorTotalGeneral );

        // variables de aixilio de excrituras
        String info1 = "--------------------------------------------------------------------";
        //String info2 = "--------------------------------------------------------------------";


        String saludo = "Exm@. Sr@.";
        String usuario = Usuario.getNombre() + " " + Usuario.getApellidos();
        String direcion = Usuario.getCalle() + ", " + Usuario.getPiso() + ", " + Usuario.getPuerta();

        //numero aleatorio para la factura
        int numero = (int)(Math.random()*10+1);
        String valor = String.valueOf( numero );
        String nFactura = "00"+valor+ "/"+StringNumero;


        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            context.openFileOutput( archivo, Context.MODE_PRIVATE ) );


            fout.write(  "_______________________________________________________________________________________\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.nombreTienda + "\t\t\t\t\t|\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.prov + "\t\t\t\t\t\t\t\t\t|\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.DirecionTienda + "\t\t\t\t|\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.mail + "\t\t|\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.Cp + "\t\t\t\t\t\t|\n" );
            fout.write( "\t\t\t\t\t\t\t\t\t| "+IdentidadTienda.tel + "\t\t\t\t\t\t|\n" );
            fout.write(  "_______________________________________________________________________________________\n" );

           // fout.write(  "\n" );
            // informacion de usuario
            fout.write( " " + saludo + "\n" );
            fout.write( " " + usuario + "\n" );
            fout.write( " " + Usuario.getEmail() + "\n" );
            fout.write( " " + Usuario.getTelefono() + "\n" );
            fout.write( " " + Usuario.getEmail() + "\n" );
            fout.write( " " + direcion + "\n" );

            // fwcha, hora y nº pedido
            fout.write(  "_______________________________________________________________________________________\n" );
            fout.write(  "\tFactura Nº: "+nFactura+"\n" );
            fout.write(  "\tFecha: "+fecha+"\n" );
            fout.write(  "\tHora: "+hora+"\n" );
            fout.write(  "_______________________________________________________________________________________\n" );


           // fout.write(  "_______________________________________________________________________________________\n" ); //=========================

            fout.write( "|\tNombre\t\t\tDescripcion \t\t\tPrecio\t\t\tCantidad\t\tSubTotal|\n" );
            fout.write(  "_______________________________________________________________________________________\n" );
            for (Cesta cest1 : cesta) {

                fout.write( "\t" + cest1.getNombre() + "\t\t\t "  +cest1.getDescripcion()+ "\t\t\t\t" + cest1.getPrecio() + " \t\t\tX "+ cest1.getCantidad_User() + "\t\t\t\t"+cest1.getSubTotal() +"\n");

            }

            fout.write(  "_______________________________________________________________________________________\n" );
            fout.write( "\tIVA: ********************************************************************* 0.99 €\n" );
            fout.write( "\tTAXA DE TRANSPORTE: ****************************************************** 2.99 €\n" );
            fout.write( "\tTOTAL DE LA COMPRA: ****************************************************** "+total+" €\n" );
            fout.write( "\tTOTAL GENERAL: *********************************************************** "+valorTotalGeneral+" €\n" );
            fout.write(  "-------------------------------------------------------------------------------------------\n" );

            //fout.write(  "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" );
            fout.write( "\t\t\t\t\t\t\t **** www.elproductoasugusto.es **** \t\t\t\n" );
            fout.write(  "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" );


            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e( "Ficheros", "Error al escribir fichero a memoria interna" );
        }


    }


}
