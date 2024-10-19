package com.example.saborfuria.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.saborfuria.R;
import com.example.saborfuria.adapters.AdapterMenu;
import com.example.saborfuria.entidades.Menu;
import com.example.saborfuria.modelos.DAOMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;


public class MenuFragment extends Fragment {
    private AdapterMenu adaptador;
    private RecyclerView rcListaP, rcListaH, rcListaB, rcListaHelados;
    private ImageView imgCarrito22;
    private List<Menu> listaMenu = new ArrayList<>();
    private List<Menu> carroCompras = new ArrayList();
    Menu m ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyt_menu, container, false);
        imgCarrito22 = view.findViewById(R.id.imgCarrito);
        initRecyclerView(view);
        return view;
    }



    private void initRecyclerView(View view) {
        rcListaH=view.findViewById(R.id.rvHamburguesa);
        rcListaP=view.findViewById(R.id.rvPromociones);
        rcListaB=view.findViewById(R.id.rvBebidas);
        rcListaHelados=view.findViewById(R.id.rvHelados);

        //Promociones
        listaMenu.add(new Menu(R.drawable.ham_parados,"Promociones","Combo para Dos","Contiene papas, una hamburguesa y una gaseosasa",15.00,1));
        listaMenu.add(new Menu(R.drawable.combo_uno,"Promociones","Combo Uno","Contiene papas, una hamburguesa y una gaseosasa",10.00,1));
        listaMenu.add(new Menu(R.drawable.combo_simple,"Promociones","Combo Simple","Contiene una hamburguesa royal",15.00,1));
        //Hamburguesas
        listaMenu.add(new Menu(R.drawable.ham_principal,"Hamburguesa","Hamburguesa Simple","Contiene una hamburguesa royal",13.00,1));
        listaMenu.add(new Menu(R.drawable.hamburguesa_dobleu,"Hamburguesa","Hamburguesa Doble","Contiene una hamburguesa royal",20.00,1));
        listaMenu.add(new Menu(R.drawable.ham_triple,"Hamburguesa","Hamburguesa Triple","Contiene una hamburguesa royal",25.00,1));
        listaMenu.add(new Menu(R.drawable.ham_pollo,"Hamburguesa","Hamburguesa de Pollo","Contiene una hamburguesa de pollo",12.00,1));
        listaMenu.add(new Menu(R.drawable.ham_integral,"Hamburguesa","Hamburguesa Integral","Contiene una hamburguesa integral",13.00,1));
        //Bebidas
        listaMenu.add(new Menu(R.drawable.cocacola_500ml,"Bebidas","Coca Cola 500ml","Contiene una gaseosa coca cola",5.00,1));
        listaMenu.add(new Menu(R.drawable.prite_500ml,"Bebidas","Sprite 500ml","Contiene una gaseosa fanta",5.00,1));
        listaMenu.add(new Menu(R.drawable.sanluis_500ml,"Bebidas","San Luis 500ml","Contiene un agua San Luis",4.00,1));

        //Helados
        listaMenu.add(new Menu(R.drawable.helado_cono,"Helados","Helado En Cono","Contiene un helado simple",3.00,1));
        listaMenu.add(new Menu(R.drawable.helado_princesa,"Helados","Princesa Familiar","Contiene un helado princesa familiar",15.00,1));
        listaMenu.add(new Menu(R.drawable.helado_sublime_dos,"Helados","Sublime Familiar","Contiene un helado sublime familiar",15.00,1));

        /*rcListaP.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptador= new AdapterMenu(listaMenu,carroCompras,imgCarrito);
        rcListaP.setAdapter(adaptador);*/

        List<Menu> hamburguesas = new ArrayList<>();
        List<Menu> promociones = new ArrayList<>();
        List<Menu> bebidas = new ArrayList<>();
        List<Menu> helados = new ArrayList<>();
        for (Menu menu : listaMenu) {
            switch (menu.getTipMenu()) {
                case "Hamburguesa":
                    hamburguesas.add(menu);
                    break;
                case "Promociones":
                    promociones.add(menu);
                    break;
                case "Bebidas":
                    bebidas.add(menu);
                    break;
                case "Helados":
                    helados.add(menu);
                    break;

            }
        }



        rcListaH.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcListaH.setAdapter(new AdapterMenu(requireContext(),hamburguesas, carroCompras, imgCarrito22));

        rcListaP.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcListaP.setAdapter(new AdapterMenu(requireContext(),promociones, carroCompras, imgCarrito22));

        rcListaB.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcListaB.setAdapter(new AdapterMenu(requireContext(),bebidas, carroCompras, imgCarrito22));

        rcListaHelados.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcListaHelados.setAdapter(new AdapterMenu(requireContext(),helados, carroCompras, imgCarrito22));
    }

}
