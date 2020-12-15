package com.example.Proyeto_DAM2_Tienda_Qr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.Proyeto_DAM2_Tienda_Qr.entidad.Usuario;
import com.example.Proyeto_DAM2_Tienda_Qr.ui.cesta.CestaFragment;
import com.example.Proyeto_DAM2_Tienda_Qr.ui.home.HomeFragment;
import com.example.Proyeto_DAM2_Tienda_Qr.ui.lista.ListaFragment;
import com.example.Proyeto_DAM2_Tienda_Qr.ui.perfil.PerfilFragment;
import com.example.menu_projecto.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public  static String MAIL_SECION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportActionBar().hide(); // este quita la barra ActionBar del titulo
        BottomNavigationView navView = findViewById( R.id.nav_view );

        loadFragment( new HomeFragment() );
        navView.setOnNavigationItemSelectedListener( this );

        recuperPreferencias(); // datos preferencias

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_cesta:
                fragment = new CestaFragment();
                break;

            case R.id.navigation_perfil:
                fragment = new PerfilFragment();
                break;

            case R.id.navigation_add:
                fragment = new ListaFragment();
                break;
        }

        return loadFragment( fragment );
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.nav_host_fragment, fragment )
                    .commit();
            return true;
        }
        return false;
    }

    private void recuperPreferencias() { // lle el archivo guardao y ensea los datos
        // para que se muestre

        Usuario usuario = new Usuario();

        SharedPreferences preferences = getSharedPreferences( "percistenciaUser", Context.MODE_PRIVATE );

        MAIL_SECION = preferences.getString( "mail", "" );
        String valorP2 = preferences.getString( "pass", "" );

    }

}
