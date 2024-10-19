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
import com.example.saborfuria.entidades.Cliente;
import java.util.List;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.ClientesViewholder> {
    private Context context;
    private List<Cliente> listaClientes;

    private OnItemClickListener clickListener;

    public AdapterCliente(Context context, List<Cliente> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ClientesViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_clientes, viewGroup, false);
        return new ClientesViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewholder clientesViewholder, @SuppressLint("RecyclerView") int i) {
        Cliente cliente = listaClientes.get(i);
        clientesViewholder.nomCliente.setText("NOMBRES: " + cliente.getNombres());
        clientesViewholder.apellidoCliente.setText("APELLIDOS: " + cliente.getApellidos());
        clientesViewholder.correoCli.setText("CORREO: " + cliente.getCorreo());
        clientesViewholder.celCli.setText("CELULAR: " + cliente.getCelular());
        clientesViewholder.edadCli.setText("EDAD: " + String.valueOf(cliente.getEdad()));
        clientesViewholder.disCli.setText("DISTRITO: " + cliente.getDistrito());
        clientesViewholder.dirCli.setText("DIRECCION: " + cliente.getDireccion());
        clientesViewholder.contraCli.setText("CONTRASEÑA: " + cliente.getContraseña());
        clientesViewholder.imagenCliente.setImageResource(cliente.getFoto());

        clientesViewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(i);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (listaClientes != null) {
            return listaClientes.size();
        } else {
            return 0;
        }
    }

    public class ClientesViewholder extends RecyclerView.ViewHolder {
        TextView nomCliente, apellidoCliente, correoCli, celCli, disCli, dirCli, edadCli, contraCli;
        ImageView imagenCliente;

        public ClientesViewholder(@NonNull View itemView) {
            super(itemView);
            nomCliente = itemView.findViewById(R.id.txtNomCli);
            apellidoCliente = itemView.findViewById(R.id.txtApeCli);
            correoCli = itemView.findViewById(R.id.txtCorCli);
            celCli = itemView.findViewById(R.id.txtCelCli);
            disCli = itemView.findViewById(R.id.txtDisCli);
            dirCli = itemView.findViewById(R.id.txtDirCli);
            contraCli=itemView.findViewById(R.id.txtConCli);
            edadCli = itemView.findViewById(R.id.txtEdadCli);
            imagenCliente = itemView.findViewById(R.id.imgClienteVista); // Si tienes una imagen
        }
    }
}
