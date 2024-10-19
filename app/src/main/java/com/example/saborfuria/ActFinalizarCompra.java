package com.example.saborfuria;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.saborfuria.activity.ActInicio;
import android.media.AudioManager;




public class ActFinalizarCompra extends AppCompatActivity {

    MediaPlayer md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        md = MediaPlayer.create(this, R.raw.sonido_finalizar_pedido);

        // Aumenta el volumen del reproductor de medios
        md.setVolume(1.0f, 1.0f); // Valores de volumen entre 0.0f (mudo) y 1.0f (volumen máximo)

        setContentView(R.layout.lyt_splash_finalizar_compra);

        md.start(); // Inicia la reproducción con el nuevo volumen

        // Espera durante un período de tiempo (2 segundos en este ejemplo)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ActFinalizarCompra.this, ActInicio.class));
                finish();
            }
        }, 2000);
    }
}
