package com.example.saborfuria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.saborfuria.R;
import com.example.saborfuria.fragments.InicioFragment;
import com.example.saborfuria.fragments.MenuFragment;
import com.example.saborfuria.fragments.PersonalizarFragment;
import com.example.saborfuria.fragments.ReservaFragment;
import com.google.android.material.bottomappbar.BottomAppBar;

public class ActInicio extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);
        FragmentManager fragmentManager = getSupportFragmentManager();


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        InicioFragment inicioFragment = new InicioFragment();
        transaction.replace(R.id.fragment_layout, inicioFragment);
        transaction.commit();

        BottomAppBar bottomAppBar = findViewById(R.id.app_bar);
        View homeBtn = findViewById(R.id.homebtn);
        View menuBtn = findViewById(R.id.homebtn0);
        View reservaBtn = findViewById(R.id.homebtn2);
        View personalizarBtn = findViewById(R.id.homebtn3);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia el fragmento a la vista "Home"
                replaceFragment(new InicioFragment());
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia el fragmento a la vista "Menu"
                replaceFragment(new MenuFragment());
            }
        });

        reservaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia el fragmento a la vista "Reserva"
                replaceFragment(new ReservaFragment());
            }
        });

        personalizarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PersonalizarFragment());
            }
        });


    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit();
    }





}
