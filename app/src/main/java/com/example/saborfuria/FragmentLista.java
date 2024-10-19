package com.example.saborfuria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentLista extends Fragment {

    ArrayList<Persona> lista;
    ListView lstPer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.lyt_lista,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstPer=getView().findViewById(R.id.lstPer);
        lista=new ArrayList<>();

        lista.add(new Persona(1,"Elmer Adrian Castro Ortiz","955025436","elmer_castro1@usmp.pe","Ingeniero de Computación y Sistemas", R.drawable.adrian));
        lista.add(new Persona(2,"Juan Marcial Torvisco Rios","946289666","juan_torvisco@usmp.pe","Ingeniero de Computación y Sistemas", R.drawable.juan_firma));
        lista.add(new Persona(3,"Angel Aaron Arturo Palomino Falcon","904588193","angel_palomino1@usmp.pe","Ingeniero de Computación y Sistemas", R.drawable.arturo));
        lista.add(new Persona(4,"Rodrigo Andres Barrios Caceres","962363249","rodrigo_barrios@usmp.pe","Ingeniero de Computación y Sistemas", R.drawable.rodrigo));
        lista.add(new Persona(5,"David Ethan Cusi Chavez","992915632","david_cusi@usmp.pe","Ingeniero de Computación y Sistemas", R.drawable.ethan));

        ArrayList<String> nombres=new ArrayList<>();
        for(Persona p:lista){
            nombres.add(p.getNom());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,nombres);
        lstPer.setAdapter(adapter);
        lstPer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((IPersona)getActivity()).seleccionarPersona(lista.get(position));
            }
        });
    }
}
