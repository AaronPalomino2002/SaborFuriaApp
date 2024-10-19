package com.example.saborfuria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActDesarrolladores extends AppCompatActivity implements IPersona{

    FragmentLista fragmentLista;
    FragmentDetalle fragmentDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_desarrolladores);
        asignarReferencias();
    }

    private void asignarReferencias() {
        fragmentLista=(FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fgtLista);
        fragmentDetalle=(FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.fgtDetalle);
    }

    @Override
    public void seleccionarPersona(Persona p) {
        fragmentDetalle.mostrarDatos(p);
    }
}
