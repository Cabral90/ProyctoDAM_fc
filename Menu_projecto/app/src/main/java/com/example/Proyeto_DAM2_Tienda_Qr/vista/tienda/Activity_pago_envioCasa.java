package com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Proyeto_DAM2_Tienda_Qr.entidad.ListaArrays;
import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.menu_projecto.R;
import com.example.Proyeto_DAM2_Tienda_Qr.util.PercistenciaUser;

import java.text.DecimalFormat;

import static com.example.Proyeto_DAM2_Tienda_Qr.ui.cesta.CestaFragment.PASAR_TOTAL;

public class Activity_pago_envioCasa extends AppCompatActivity {

    private TextView nomTarjeta;
    private TextView numTargeta;
    private TextView fechaTarjeta;
    private TextView totalPagar;
    private TextView totalFinal; //
    private TextView taxaTransporte;
    private EditText codigoSeguro;

    private ScrollView scrollViewAldominicio;
    private LinearLayout layoutAldomicilio;


    private Button btn_enviarAcasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pago_envio_casa );

        // cast
        nomTarjeta = (TextView) findViewById( R.id.nomTarjectaP_c );
        numTargeta = (TextView) findViewById( R.id.numTarjectaP_c );
        fechaTarjeta = (TextView) findViewById( R.id.fechaTarjectaP_c );
        totalPagar = (TextView) findViewById( R.id.totalPagarP_c ); // totalG_c
        totalFinal =(TextView)findViewById( R.id.totalG_c ); // totalG_c
        taxaTransporte =(TextView)findViewById( R.id.trasport ); // totalG_c

        codigoSeguro = (EditText) findViewById( R.id.cod_seguridad_c );

        btn_enviarAcasa = (Button) findViewById( R.id.btn_pagarP_c );

        scrollViewAldominicio=(ScrollView)findViewById( R.id.scrollViewAlDomicilio );
        layoutAldomicilio=(LinearLayout)findViewById( R.id.layoutAldomicilio );

        layoutAldomicilio.setVisibility( View.INVISIBLE );

        // seter los valores
        setearValores();


        // metodo del buton
        btn_enviarAcasa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsistenciaTienda asistencia = new AsistenciaTienda();

                String codigo = codigoSeguro.getText().toString();

                if (!codigo.isEmpty()) {
                    // metodo pagar
                    asistencia.escribirProductos( getApplicationContext(), ListaArrays.PRODUCTOS_ELEGIDOS, PASAR_TOTAL );

                    Toast.makeText( getApplicationContext(), "Operacion hecha con suceso", Toast.LENGTH_LONG ).show();
                    scrollViewAldominicio.setVisibility( View.INVISIBLE );
                    layoutAldomicilio.setVisibility( View.VISIBLE );

                } else {
                    Toast.makeText( getApplicationContext(), "Campo obrigatorio", Toast.LENGTH_LONG ).show();
                }

            }
        } );

    }


    private void setearValores() {
        DecimalFormat df2 = new DecimalFormat( "#.00" );

        if (Usuario.getNombreProprietarioTarjeta() == null) { // se el usuario entra por la segunra vez
            PercistenciaUser persi = new PercistenciaUser();
            persi.lerUser22( getApplicationContext() );
        }

        double taxaTransponte = 2.99;

        if(PASAR_TOTAL== null){
            PASAR_TOTAL ="00,00";
        }

        String StringTotal = PASAR_TOTAL.replace( ",", "." );

        double totalG = Double.parseDouble( StringTotal );

        double totalGeneral = totalG + taxaTransponte;

        String StringTotalFinal = String.valueOf( df2.format( totalGeneral ));

        nomTarjeta.setText( "Proprietario: " + Usuario.getNombreProprietarioTarjeta() );
        numTargeta.setText( "Tarjecta Nº: " + Usuario.getNumeroTarjeta() );
        fechaTarjeta.setText( "Fecha de Validad: " + Usuario.getFechaCaducidadTarjeta() );

        if (ListaArrays.PRODUCTOS_ELEGIDOS.size() ==0) {
            totalPagar.setText( "Total Cesta: 00.00 €" );
            totalFinal.setText( "00,00 €" );
            taxaTransporte.setText( "Taxa de Transporte: 0,00 €" );
        } else {
            totalPagar.setText( "Total Cesta: "+PASAR_TOTAL + " €" );
            taxaTransporte.setText( "Taxa de Transporte: 2,99 €" );
            totalFinal.setText(StringTotalFinal + " €" );
        }

    }
}
