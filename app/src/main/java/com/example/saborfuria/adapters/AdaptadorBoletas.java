package com.example.saborfuria.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Boleta;

import java.util.List;

public class AdaptadorBoletas extends RecyclerView.Adapter<AdaptadorBoletas.BoletasViewHolder> {

    Context context;

    List<Boleta> listaBoletas;
    private AdaptadorBoletas.OnItemClickListener clickListener;

    public AdaptadorBoletas(Context context, List<Boleta> listaBoletas) {
        this.context = context;
        this.listaBoletas = listaBoletas;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdaptadorBoletas.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public BoletasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_boleta, viewGroup, false);
        return new BoletasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoletasViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Boleta boleta = listaBoletas.get(position);
        holder.txtNomB.setText(boleta.getNom());
        holder.txtCelB.setText(boleta.getCelular());
        holder.txtDirB.setText(boleta.getDireccion());
        holder.txtMetPagB.setText(boleta.getMetPag());
        holder.txtTotalB.setText(String.valueOf(boleta.getTotal()));

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
        return listaBoletas.size();
    }

    public class BoletasViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomB, txtCelB, txtDirB, txtMetPagB, txtTotalB;
        public BoletasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomB=itemView.findViewById(R.id.txtNomB);
            txtCelB=itemView.findViewById(R.id.txtCelB);
            txtDirB=itemView.findViewById(R.id.txtDirB);
            txtMetPagB=itemView.findViewById(R.id.txtMetPagB);
            txtTotalB=itemView.findViewById(R.id.txtTotalB);

        }
    }
}
