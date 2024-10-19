package com.example.saborfuria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Menu;

import java.util.List;

public class AdaptadorCarroComprasMenu extends RecyclerView.Adapter<AdaptadorCarroComprasMenu.ProductosViewHolder> {


    Context context;
    List<Menu> carroCompra;
    TextView total;

    double totalP =0.0;


    public AdaptadorCarroComprasMenu(Context context, List<Menu> carroCompra, TextView total) {
        this.context = context;
        this.carroCompra = carroCompra;
        this.total = total;

        if (carroCompra != null && total != null) {
            for (Menu menu : carroCompra) {
                if (menu.getCantidad() == 0) {
                    menu.setCantidad(1); // Establece la cantidad predeterminada en 1 si no se ha establecido
                }
                totalP += menu.getPrecio() * menu.getCantidad();
            }
            total.setText(String.valueOf(totalP));
        }
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_com,null,false);
        return new ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, int i) {
        final Menu menu = carroCompra.get(i);

        productosViewHolder.nomProducto.setText(carroCompra.get(i).getNomMen());
        productosViewHolder.precio.setText(String.valueOf(carroCompra.get(i).getPrecio()));
        productosViewHolder.imagePro.setImageResource(carroCompra.get(i).getFoto());

        productosViewHolder.txtAumentar.setText(String.valueOf(menu.getCantidad()));

        // Agrega funcionalidad al botón de aumento (imgMas)
        productosViewHolder.imgMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = menu.getCantidad();
                cantidad++;
                menu.setCantidad(cantidad);
                productosViewHolder.txtAumentar.setText(String.valueOf(cantidad));

                // Actualiza el total
                totalP += menu.getPrecio();
                total.setText(String.valueOf(totalP));
            }
        });

        // Agrega funcionalidad al botón de disminución (imgMenos)
        productosViewHolder.imgMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = menu.getCantidad();
                if (cantidad > 1) {
                    cantidad--;
                    menu.setCantidad(cantidad);
                    productosViewHolder.txtAumentar.setText(String.valueOf(cantidad));

                    // Actualiza el total
                    totalP -= menu.getPrecio();
                    total.setText(String.valueOf(totalP));
                }
            }
        });


    }

    public double getTotalP() {
        return totalP;
    }

    @Override
    public int getItemCount() {
        if (carroCompra != null) {
            return carroCompra.size();
        } else {
            return 0; // Otra acción adecuada si la lista es nula
        }
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView nomProducto, precio, txtAumentar;
        ImageView imagePro;
        ImageView imgMas, imgMenos;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            nomProducto = itemView.findViewById(R.id.txtProducItem);
            precio=itemView.findViewById(R.id.txtPrec);
            imgMas = itemView.findViewById(R.id.imgMas);
            imgMenos = itemView.findViewById(R.id.imgMenos);
            imagePro = itemView.findViewById(R.id.imgCarrito2);
            txtAumentar = itemView.findViewById(R.id.textView10);
        }
    }
}
