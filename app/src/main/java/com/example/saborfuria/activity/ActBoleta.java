package com.example.saborfuria.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saborfuria.ActFinalizarCompra;
import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Boleta;
import com.example.saborfuria.modelos.DAOBoleta;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


public class ActBoleta extends AppCompatActivity implements View.OnClickListener{


    Spinner spMetPago;
    EditText edtDirec, edtNom, edtCel, edtMonPa, edtCom, edtYape;
    LinearLayout lytYape;
    Button btnBoleta, btnFactura, btnFinCom;
    ImageView btnAtras;
    CheckBox chckProm;
    String Meto[] = {"Tarjeta de Crédito", "Tarjeta de Débito", "Yape", "Efectivo"};

    MediaPlayer mp;

    DAOBoleta daoBoleta = new DAOBoleta(this);

    Boleta b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_metodo_pago);
        daoBoleta.openDB();
        edtYape = findViewById(R.id.edtNumYape);
        edtMonPa = findViewById(R.id.edtMonPa);
        lytYape = findViewById(R.id.yape);
        edtCom = findViewById(R.id.edtComentario);
        chckProm = findViewById(R.id.chckReciProm);
        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActBoleta.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro que deseas volver al carrito? Los datos no guardados se perderán.");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "Sí", regresa a la actividad del carrito
                        Intent intent = new Intent(ActBoleta.this, ActCarroCompra.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Si el usuario elige "No", simplemente cierra el diálogo y permanece en la misma actividad
                    }
                });
                builder.show();
            }
        });
        btnBoleta = findViewById(R.id.btnBoleta);
        btnFactura = findViewById(R.id.btnFactura);
        spMetPago = findViewById(R.id.spMetPago);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Meto);
        spMetPago.setAdapter(adapter);
        spMetPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selec = parent.getItemAtPosition(position).toString();


                if ("Tarjeta de Crédito".equals(selec)) {
                    lytYape.setVisibility(View.GONE);
                    edtMonPa.setVisibility(View.GONE);
                } else if ("Tarjeta de Débito".equals(selec)) {
                    lytYape.setVisibility(View.GONE);
                    edtMonPa.setVisibility(View.GONE);
                } else if ("Yape".equals(selec)) {
                    lytYape.setVisibility(View.VISIBLE);
                    edtMonPa.setVisibility(View.GONE);
                } else {
                    lytYape.setVisibility(View.GONE);
                    edtMonPa.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        asignarReferencias();
        btnFinCom=(Button)findViewById(R.id.btnFinCom);
        mp= MediaPlayer.create(this,R.raw.sonido_finalizar_pedido);
        btnFinCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });
    }

    private void asignarReferencias() {
        edtDirec = findViewById(R.id.edtDirec);
        edtNom = findViewById(R.id.edtNomRes);
        edtCel = findViewById(R.id.edtCel);
    }

    public void enviarDatos() {
        String direccion = edtDirec.getText().toString();
        String nombre = edtNom.getText().toString();
        String celular = edtCel.getText().toString();
        String metPag = spMetPago.getSelectedItem().toString();
        Intent intent= getIntent();
        double total= intent.getDoubleExtra("totalPagar",0.0);


        boolean hasError = false;

        if (nombre.isEmpty()) {
            edtNom.setError("Por favor, ingresa un nombre");
            hasError = true;
        } else {
            edtNom.setError(null);
        }

        if (celular.isEmpty()) {
            edtCel.setError("Por favor, ingresa un número de celular");
            hasError = true;
        } else if (celular.length() != 9) {
            edtCel.setError("El número de celular debe tener 9 dígitos");
            hasError = true;
        } else {
            edtCel.setError(null);
        }

        if (!hasError) {
            // Si todos los campos están llenos y el número de celular tiene 9 dígitos
            // Continúa con la lógica para agregar una boleta a la lista

            // Muestra un AlertDialog de confirmación para finalizar la compra
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Estás seguro que quieres finalizar la compra?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Si el usuario elige "Sí", realiza la lógica necesaria para finalizar la compra
                    // y luego limpia los campos
                    // También puedes redirigir al usuario a otra actividad si es necesario
                    b= new Boleta(nombre,celular,direccion,metPag,total);
                    daoBoleta.registrarBoleta(b);
                    Log.d("Boletas", "Número de boletas en la lista: " + daoBoleta.getBoletas().size());
                    Log.d("Son", "Reproduciendo sonido...");
                    Log.d("Sonido", "Reproduciendo sonido...");
                    mp.start();
                    Toast.makeText(ActBoleta.this, "Se generó su boleta", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActBoleta.this, ActFinalizarCompra.class);
                    startActivity(intent);
                    limpiarCampos();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Si el usuario elige "No", simplemente cierra el diálogo y permanece en la misma actividad
                }
            });
            builder.show();
        }


    }

    private void limpiarCampos() {
        edtDirec.setText("");
        edtNom.setText("");
        edtCel.setText("");
        edtCom.setText("");
        spMetPago.setSelection(0); // Esto seleccionará el primer elemento del spinner
    }

    public void onClick(View v) {
        btnBoleta.setBackgroundColor(getResources().getColor(R.color.sb));
        btnFactura.setBackgroundColor(getResources().getColor(R.color.df));

    }

    public void fac(View view) {
        btnFactura.setBackgroundColor(getResources().getColor(R.color.sb));
        btnBoleta.setBackgroundColor(getResources().getColor(R.color.df));

    }
}
