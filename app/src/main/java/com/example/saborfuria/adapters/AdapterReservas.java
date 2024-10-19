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
import com.example.saborfuria.entidades.Reserva;

import java.util.List;

public class AdapterReservas extends RecyclerView.Adapter<AdapterReservas.ReservasViewHolder> {
    Context context;

    List<Reserva> listaReserva;
    private AdapterReservas.OnItemClickListener clickListener;

    public AdapterReservas(Context context, List<Reserva> listaReserva) {
        this.context = context;
        this.listaReserva = listaReserva;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterReservas.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public AdapterReservas.ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_reserva, viewGroup, false);
        return new ReservasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReservas.ReservasViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Reserva reserva=listaReserva.get(position);
        holder.txtNomRes.setText(reserva.getNom());
        holder.txtApeRes.setText(reserva.getApe());
        holder.txtNumMesRes.setText(reserva.getNumMesa());
        holder.txtNumPerRes.setText(reserva.getCantPer());
        holder.txtFecRes.setText(reserva.getFecRes());
        holder.txtHoraRes.setText(reserva.getHorares());

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
        return listaReserva.size();
    }

    public class ReservasViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomRes, txtApeRes, txtNumMesRes, txtNumPerRes, txtFecRes, txtHoraRes;
        public ReservasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomRes=itemView.findViewById(R.id.txtNomRes);
            txtApeRes=itemView.findViewById(R.id.txtApeRes);
            txtNumMesRes=itemView.findViewById(R.id.txtNumMesRes);
            txtNumPerRes=itemView.findViewById(R.id.txtCantPerRes);
            txtFecRes=itemView.findViewById(R.id.txtFecRes);
            txtHoraRes=itemView.findViewById(R.id.txtHoraRes);
        }
    }
}
