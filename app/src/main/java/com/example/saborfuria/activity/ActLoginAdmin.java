package com.example.saborfuria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saborfuria.ActInicioAdmi;
import com.example.saborfuria.ActPrincipal;
import com.example.saborfuria.R;

public class ActLoginAdmin extends AppCompatActivity {

    EditText edtCorreoAdmi, edtContraAdmin;
    ImageView imgRegresarAdmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_login_admin);
        asignarReferencias();
        imgRegresarAdmi=findViewById(R.id.imgRegresarAdmi);
        imgRegresarAdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ActLoginAdmin.this, ActPrincipal.class);
                startActivity(intent);
            }
        });
    }

    private void asignarReferencias() {
        edtCorreoAdmi=findViewById(R.id.edtCorreoAdmin);
        edtContraAdmin=findViewById(R.id.edtContraseñaAdmin);
    }

    public void validarAdmin(View view) {
        String correoAdm =edtCorreoAdmi.getText().toString();
        String contraAdm = edtContraAdmin.getText().toString();


            if (correoAdm.equalsIgnoreCase("SFAdmin@sf.pe") && contraAdm.equals("Usmp2023")) {
                Toast.makeText(this, "Correo y contraseña validos", Toast.LENGTH_SHORT).show();

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ActLoginAdmin.this, ActInicioAdmi.class);
                        startActivity(intent);
                    }
                }, 2000);
                return;
            }


        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

    }
}
