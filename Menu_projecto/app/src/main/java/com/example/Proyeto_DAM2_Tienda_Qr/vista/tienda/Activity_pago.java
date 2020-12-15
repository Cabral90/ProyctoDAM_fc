package com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
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

import static com.example.Proyeto_DAM2_Tienda_Qr.ui.cesta.CestaFragment.PASAR_TOTAL;

public class Activity_pago extends AppCompatActivity {

    private TextView nomTarjeta;
    private TextView numTargeta;
    private TextView fechaTarjeta;
    private TextView totalPagar;
    private EditText codigoSeguro;

    private LinearLayout mesajeExito;
    private ScrollView scrollViewPago;

    private Button btn_pagar;

    // private DatosPerfil_Fragment datosPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pago );

        // cast
        nomTarjeta = (TextView) findViewById( R.id.nomTarjectaP );
        numTargeta = (TextView) findViewById( R.id.numTarjectaP );
        fechaTarjeta = (TextView) findViewById( R.id.fechaTarjectaP );
        totalPagar = (TextView) findViewById( R.id.totalPagarP );

        codigoSeguro = (EditText) findViewById( R.id.cod_seguridad );

        btn_pagar = (Button) findViewById( R.id.btn_pagarP );

        mesajeExito =(LinearLayout)findViewById( R.id.layutMesaje);
        scrollViewPago=(ScrollView)findViewById( R.id.scrollViewEnLaTienda );

        mesajeExito.setVisibility( View.INVISIBLE );

        setearValores();


        // buton

        btn_pagar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsistenciaTienda asistencia = new AsistenciaTienda();

                String codigo = codigoSeguro.getText().toString();

                if (!codigo.isEmpty()) {

                    Toast.makeText( getApplicationContext(), "Operacion hecha con suceso", Toast.LENGTH_LONG ).show();
                    scrollViewPago.setVisibility( View.INVISIBLE );
                    mesajeExito.setVisibility( View.VISIBLE );

                } else {
                    Toast.makeText( getApplicationContext(), "Campo obrigatorio", Toast.LENGTH_LONG ).show();
                }


            }
        } );
    }

    // metodos


    private void setearValores() {

        if (Usuario.getNombreProprietarioTarjeta() == null) { // se el usuario entra por la segunra vez
            PercistenciaUser persi = new PercistenciaUser();
            persi.lerUser22( getApplicationContext() );
        }


        nomTarjeta.setText( "Proprietario: " + Usuario.getNombreProprietarioTarjeta() );
        numTargeta.setText( "Tarjecta Nº: " + Usuario.getNumeroTarjeta() );
        fechaTarjeta.setText( "Fecha de Validad: " + Usuario.getFechaCaducidadTarjeta() );


        if (ListaArrays.PRODUCTOS_ELEGIDOS.size() ==0) {
            totalPagar.setText( "00,00 €" );

        } else {
            totalPagar.setText( PASAR_TOTAL + " €" );

        }

    }


}
