package com.example.saborfuria.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Menu;

import java.util.List;

public class AdapterMenuAdmi extends RecyclerView.Adapter<AdapterMenuAdmi.MenuViewHolder> {

    Context context;

    List<Menu> listaMenu;
    private AdapterMenuAdmi.OnItemClickListener clickListener;

    public AdapterMenuAdmi(Context context, List<Menu> listaMenu) {
        this.context = context;
        this.listaMenu = listaMenu;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterMenuAdmi.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_menu_uno, viewGroup, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Menu menu = listaMenu.get(position);
        holder.txtTipMen.setText(menu.getTipMenu());
        holder.txtNomMen.setText(menu.getNomMen());
        holder.txtPreMen.setText(menu.getPrecio()+"");
        holder.txtDesMen.setText(menu.getDesc());
        holder.imageMenuItem.setImageResource(menu.getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView txtTipMen, txtNomMen, txtDesMen, txtPreMen;
        ImageView imageMenuItem;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTipMen=itemView.findViewById(R.id.txtTipMenu);
            txtNomMen=itemView.findViewById(R.id.txtNomMenu);
            txtDesMen=itemView.findViewById(R.id.txtDescriMenu);
            txtPreMen=itemView.findViewById(R.id.txtPrecioMenu);
            imageMenuItem=itemView.findViewById(R.id.imageMenuItem2);
        }
    }
}
