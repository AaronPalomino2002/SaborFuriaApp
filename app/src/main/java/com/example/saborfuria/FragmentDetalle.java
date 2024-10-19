package com.example.saborfuria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment {

    TextView txtNom,txtCel,txtEma,txtPro;
    ImageView imgFoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.lyt_detalle,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNom=getView().findViewById(R.id.txtNom);
        txtCel=getView().findViewById(R.id.txtCel);
        txtEma=getView().findViewById(R.id.txtEma);
        txtPro=getView().findViewById(R.id.txtPro);
        imgFoto=getView().findViewById(R.id.imgDesarrolladores);
    }

    public void mostrarDatos(Persona p){
        txtNom.setText(p.getNom());
        txtCel.setText(p.getCel());
        txtEma.setText(p.getEma());
        txtPro.setText(p.getPro());
        imgFoto.setImageResource(p.getIdImg());
    }

}
