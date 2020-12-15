package com.example.Proyeto_DAM2_Tienda_Qr.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Adapter;
import com.example.Proyeto_DAM2_Tienda_Qr.vista.tienda.Model;
import com.example.menu_projecto.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment { // implements Detalles_fragment.OnFragmentInteractionListener

    private Button btn_serrar;
    private TextView txt;

    private HomeViewModel homeViewModel;

    // slider
    ViewPager viewPager, viewPager2;
    Adapter adapter;
    List<Model> models;
//    Integer[] colors = null;
//    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of( this ).get( HomeViewModel.class );
        View root = inflater.inflate( R.layout.fragment_home, container, false );

//        txt = (TextView) root.findViewById( R.id.textViewFrag );
        //btn_serrar = (Button)root.findViewById(R.id.btn_serrar);


        // slider


        models = new ArrayList<>();

        models.add( new Model( R.raw.brocolis, "1,39 €", "Brocolí" ) );
        models.add( new Model( R.raw.ternera, "3,55 €", "Ternera" ) );
        models.add( new Model( R.raw.gamba1, "4,99 €", "Gamba" ) );
        models.add( new Model( R.raw.azeite, "4,97 €", "Azeite" ) );
        models.add( new Model( R.raw.macarron, "4,66 €", "Macarrón" ) );

        adapter = new Adapter( models, getContext() );

        viewPager = (ViewPager) root.findViewById( R.id.viewPager1 );
        viewPager.setAdapter( (PagerAdapter) adapter );
        viewPager.setPadding( 100, 0, 100, 0 );
        viewPager.setBackgroundColor( getResources().getColor( R.color.color5 ) );


        return root;
    }


}