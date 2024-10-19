package com.example.saborfuria.fragments;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.saborfuria.ActPerfilUsuario;
import com.example.saborfuria.ActPrincipal;
import com.example.saborfuria.ImageAdapter;
import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Cliente;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class InicioFragment extends Fragment {
    Button btnOrdenar;
    ImageView imgUsuario;
    private ViewPager viewPager;
    private int currentPage = 0;
    private int[] imageIds = {R.drawable.banner_unot, R.drawable.banner_dosd, R.drawable.banner_3};
    private TextView txtNomUsu;

    Cliente cliente;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyt_inicio, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter adapter1 = new ImageAdapter(requireContext(), imageIds);
        viewPager.setAdapter(adapter1);

        // Configura un temporizador para cambiar las imágenes automáticamente
        Timer timer = new Timer();
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            public void run() {
                if (currentPage == imageIds.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 2000, 2000);


        txtNomUsu = view.findViewById(R.id.txtNomUsu);
        mostrarNombreUsuario();
        btnOrdenar= view.findViewById(R.id.btnOrdenar);
        btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imgUsuario=view.findViewById(R.id.imgUsuario);
        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cliente == null ) {
                    // El nombre de usuario no está configurado, mostrar el diálogo de cerrar sesión
                    mostrarDialogoSalir();
                } else {
                    Intent intent = new Intent(getContext(), ActPerfilUsuario.class);
                    intent.putExtra("nombreUsuario", cliente);
                    startActivity(intent);
                    // El nombre de usuario está configurado, no hacer nada
                }
            }
        });

        return view;
    }
    private void mostrarNombreUsuario() {
        cliente = (Cliente) requireActivity().getIntent().getSerializableExtra("nombreUsuario");

        if (cliente == null) {
            txtNomUsu.setText("!Hola, bienvenido!");
        } else {
            String nombre = cliente.getNombres();
            if (nombre == null) {
                txtNomUsu.setText("!Hola, bienvenido!");
            } else {
                txtNomUsu.setText("Hola, " + nombre);
            }
        }
    }




    private void mostrarDialogoCerrarSesion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Seguro que quieres cerrar la sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción cuando el usuario elige "Sí", por ejemplo, ir a la página principal
                Intent intent = new Intent(requireContext(), ActPrincipal.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No hacer nada, el usuario eligió "No"
            }
        });
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No hacer nada, el usuario eligió "Cancelar"
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void mostrarDialogoSalir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Salir");
        builder.setMessage("Debes iniciar sesión para acceder a las compras.\n¿Deseas volver a la pantalla principal?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí debes agregar la lógica para volver a la pantalla principal
                Intent intent = new Intent(requireContext(), ActPrincipal.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No es necesario hacer nada aquí, el diálogo se cerrará al presionar "Cancelar"
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
