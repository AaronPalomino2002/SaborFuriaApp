package com.example.saborfuria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saborfuria.ActPrincipal;
import com.example.saborfuria.R;
import com.example.saborfuria.entidades.Cliente;
import com.example.saborfuria.modelos.DAOClientes;

import java.util.ArrayList;

public class ActRegistrar extends AppCompatActivity{

    EditText edtNom,edtApe,edtEdad,edtCel,edtCon,edtDir,edtCorreo;
    Spinner spDis;

    Button btnRegis;

    ImageView imgRegresar;

    String distri[]={"Barranco","Breña","La Molina","Lince","Magdalena del Mar","Miraflores","San Isidro",
            "San luis","San Miguel","Santa Anita","Santiago de Surco","Surquillo"};

    DAOClientes daoClientes = new DAOClientes(this);

    Cliente c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar);
        daoClientes.openDB();
        asignarReferencia();
    }

    private void asignarReferencia() {
        edtNom=findViewById(R.id.edtNomRes);
        edtApe=findViewById(R.id.edtApeRes);
        edtEdad=findViewById(R.id.edtEdad);
        edtCel=findViewById(R.id.edtCel);
        edtCon=findViewById(R.id.edtCon);
        edtDir=findViewById(R.id.edtDir);
        edtCorreo=findViewById(R.id.edtCorreo);
        spDis=findViewById(R.id.spDis);
        btnRegis=findViewById(R.id.btnRegis);
        spDis=findViewById(R.id.spDis);
        imgRegresar=findViewById(R.id.imgRegresar);
        imgRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ActRegistrar.this, ActPrincipal.class);
                startActivity(intent);
            }
        });
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,distri);
        spDis.setAdapter(adapter);
    }

    public void registrarCliente(View view){
        String nom, ape, dis, dir, correo, cel, contra;
        int edad;

        nom = edtNom.getText().toString();
        ape = edtApe.getText().toString();
        dis = spDis.getSelectedItem().toString();
        dir = edtDir.getText().toString();
        correo = edtCorreo.getText().toString();
        cel = edtCel.getText().toString();
        contra = edtCon.getText().toString();

        boolean hasError = false;

        if (nom.isEmpty()) {
            edtNom.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (ape.isEmpty()) {
            edtApe.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (dir.isEmpty()) {
            edtDir.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (correo.isEmpty()) {
            edtCorreo.setError("Este campo es obligatorio.");
            hasError = true;
        } else if (!correo.contains("@")) {
            edtCorreo.setError("El correo electrónico debe contener un carácter '@'.");
            hasError = true;
        }

        if (cel.isEmpty()) {
            edtCel.setError("Este campo es obligatorio.");
            hasError = true;
        } else if (cel.length() != 9) {
            edtCel.setError("El número de celular debe tener 9 dígitos.");
            hasError = true;
        }

        if (contra.isEmpty()) {
            edtCon.setError("Este campo es obligatorio.");
            hasError = true;
        }

        if (!hasError) {
            // Si no hay errores en los campos, continúa con la lógica para agregar un nuevo cliente a la lista
            edad = Integer.parseInt(edtEdad.getText().toString());

            if (edad < 18) {
                edtEdad.setError("La edad debe ser mayor a 18 años.");
            } else {
                c= new Cliente(R.drawable.imagen_dos, nom,ape,dis, dir,correo,cel,contra,edad);
                daoClientes.registrarCliente(c);
                if(daoClientes.getCliente()!=null){
                    limpiar();
                    Intent intent = new Intent(this, ActLogin.class);
                    Toast.makeText(this, "Registro existoso", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Esta vacio", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void limpiar(){
        edtNom.setText("");
        edtApe.setText("");
        spDis.setSelection(0);
        edtDir.setText("");
        edtCorreo.setText("");
        edtCel.setText("");
        edtEdad.setText("");
        edtCon.setText("");
        edtNom.requestFocus();
    }



}