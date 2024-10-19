package com.example.saborfuria.fragments;

import com.example.saborfuria.entidades.Reserva;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.example.saborfuria.R;
import com.example.saborfuria.modelos.DAOReservas;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


public class ReservaFragment extends Fragment {

    int notificationId = 1;

    EditText edtNom, edtApe, edtFecha;
    Spinner spNumMesa, spNumPer, spHora;
    Button btnReserva;

    DAOReservas daoReservas;

    Reserva r;

    String mesa[] = {"SF-1", "SF-2", "SF-3", "SF-4", "SF-5", "SF-6", "SF-7", "SF-8", "SF-9", "SF-10"};
    String per[] = {"1", "2", "3", "4", "5", "6"};
    String hora[] = {"12:00 PM", "13:00 PM", "14:00 PM", "15:00 PM", "16:00 PM", "17:00 PM", "18:00 PM", "19:00 PM"};


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyt_reserva, container, false);
        daoReservas = new DAOReservas(getContext());
        daoReservas.openDB();
        edtNom = view.findViewById(R.id.edtNomRes);
        edtApe = view.findViewById(R.id.edtApeRes);
        edtFecha = view.findViewById(R.id.edtFecha);
        spHora = view.findViewById(R.id.spHoraRes);
        spNumMesa = view.findViewById(R.id.spNumMesa);
        spNumPer = view.findViewById(R.id.spNumPer);
        btnReserva = view.findViewById(R.id.btnRes);
        ArrayAdapter<String> mesaAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, mesa);
        mesaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumMesa.setAdapter(mesaAdapter);

        ArrayAdapter<String> perAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, per);
        perAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumPer.setAdapter(perAdapter);

        ArrayAdapter<String> horaAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, hora);
        horaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHora.setAdapter(horaAdapter);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDatosReserva();
            }
        });
        return view;
    }

    public void enviarDatosReserva() {
        String nom = edtNom.getText().toString();
        String ape = edtApe.getText().toString();
        String numMesa = spNumMesa.getSelectedItem().toString();
        String numPer = spNumPer.getSelectedItem().toString();
        String hora = spHora.getSelectedItem().toString();
        String fecha = edtFecha.getText().toString();

        if (nom.isEmpty()) {
            edtNom.setError("Ingresa un nombre");
            return; // Detiene la ejecución si el nombre está vacío
        } else if (!nom.matches("[a-zA-Z]+")) {
            edtNom.setError("El nombre solo debe contener letras");
            return; // Detiene la ejecución si el nombre contiene caracteres no válidos
        }

        if (ape.isEmpty()) {
            edtApe.setError("Ingresa un apellido");
            return; // Detiene la ejecución si el apellido está vacío
        } else if (!ape.matches("[a-zA-Z]+")) {
            edtApe.setError("El apellido solo debe contener letras");
            return; // Detiene la ejecución si el apellido contiene caracteres no válidos
        }

        if (fecha.isEmpty() || !isValidDate(fecha)) {
            edtFecha.setError("Ingresa una fecha válida en el formato dd/MM/yyyy");
            return; // Detiene la ejecución si la fecha está vacía o no es válida
        } else {
            // Valida si la fecha ingresada es mayor o igual a la fecha de hoy
            if (!isFutureDateInPeru(fecha)) {
                edtFecha.setError("La fecha debe ser hoy o una fecha futura");
                return; // Detiene la ejecución si la fecha no es válida
            }
        }

        // Si todos los campos son válidos, agrega la reserva
        r = new Reserva(nom, ape, numMesa, numPer, fecha, hora);
        daoReservas.registrarReserva(r);
        Log.d("reservas", "Número de Reservas en la lista: " + daoReservas.getReserva().size());
        mostrarRecordatorio();
        limpiar();
    }

    public boolean isFutureDateInPeru(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Lima")); // Establece la zona horaria de Perú

        try {
            Date currentDateInPeru = sdf.parse(sdf.format(new Date())); // Obtiene la fecha actual en la zona horaria de Perú
            Date parsedDate = sdf.parse(date);

            if (parsedDate != null) {
                // Compara las fechas teniendo en cuenta la zona horaria
                return !parsedDate.before(currentDateInPeru);
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }



    private boolean isValidDate(String date) {
        // Define un formato de fecha válido (dd/MM/yyyy)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // No permite fechas inválidas (por ejemplo, 30/02/2023)

        try {
            // Intenta analizar la fecha
            Date parsedDate = sdf.parse(date);
            return parsedDate != null;
        } catch (ParseException e) {
            // Si hay una excepción, la fecha no es válida
            return false;
        }
    }

    public void mostrarRecordatorio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Recordatorio");
        builder.setMessage("Le esperaremos hasta 15 minutos después de la hora programada.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cierra el diálogo cuando se hace clic en OK
                mostrarToastRegistro();
                notificacion();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void mostrarToastRegistro() {
        Toast.makeText(requireContext(), "Su reserva ha sido registrada", Toast.LENGTH_SHORT).show();
    }

    public void limpiar() {
        edtNom.setText("");
        edtApe.setText("");
        edtFecha.setText("");
        spHora.setSelection(0);
        spNumMesa.setSelection(0);
        spNumPer.setSelection(0);
        edtNom.requestFocus();
    }
    public void notificacion(){
        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // Crear un canal de notificación (solo necesario para Android 8.0 y versiones posteriores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "mi_canal";
            CharSequence channelName = "Mi Canal de Notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // Crear el contenido de la notificación con saltos de línea
        String contentText = "Número de mesa: " + r.getNumMesa() + "\n"
                + "Hora de reserva: " + r.getHorares() + "\n"
                + "Fecha de reserva: " + r.getFecRes();

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "mi_canal")
                .setSmallIcon(R.drawable.icon_notification2)
                .setContentTitle("Reserva de Mesa")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(contentText)) // Muestra el texto completo con saltos de línea
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Mostrar la notificación
        notificationManager.notify(notificationId, builder.build());
    }
}

