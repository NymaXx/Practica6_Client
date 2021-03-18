package com.example.practica6_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button actionUP;
    private Button actionDOWN;
    private Button actionLEFT;
    private Button actionRIGHT;
    private Button actionCOLOR;


    Socket socket;
    BufferedWriter writer;
    boolean isUP = true;
    boolean isDOWN = true;
    boolean isLEFT = true;
    boolean isRIGHT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionUP = findViewById(R.id.actionUP);
        actionDOWN = findViewById(R.id.actionDOWN);
        actionLEFT = findViewById(R.id.actionLEFT);
        actionRIGHT = findViewById(R.id.actionRIGHT);
        actionCOLOR = findViewById(R.id.actionCOLOR);


        new Thread(
                () -> {
                    try {
                        //Esta linea envia solicitud de conexion
                        //En la seccion del host voy a poner la IP del servidor
                        //En el puerto, voy a poner el puerto en el que escucha el servidor
                        socket = new Socket("192.168.18.4", 5000);


                        //------------------------------------------//
                        //Definiendo corriente de salida
                        OutputStream os = socket.getOutputStream();

                        //Como necesitamos mandar String, entonces vamos a mandar  siguientes objetos
                        //Buffer = espacio para almacenar bytes temporalmente
                        OutputStreamWriter osw = new OutputStreamWriter(os);

                        //Esta linea nos permite crear un objeto que manda Strings pero con buffer
                        writer = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        actionUP.setOnTouchListener(
                (v, event) -> {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isUP = false;
                            //accionUp.setText("DOWN");
                            break;

                        case MotionEvent.ACTION_MOVE:
                            //accionUp.setText("MOVE");
                            break;

                        case MotionEvent.ACTION_UP:
                            isUP = true;
                            //accionUp.setText("UP");
                            break;
                    }
                    return true;
                }
        );

        actionDOWN.setOnTouchListener(
                (v, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isDOWN = false;
                            break;

                        case MotionEvent.ACTION_MOVE:
                            break;

                        case MotionEvent.ACTION_UP:
                            isDOWN = true;
                            break;
                    }
                    return true;
                }
        );

        actionLEFT.setOnTouchListener(
                (v, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isLEFT = false;
                            break;

                        case MotionEvent.ACTION_MOVE:
                            break;

                        case MotionEvent.ACTION_UP:
                            isLEFT = true;
                            break;
                    }
                    return true;
                }
        );

        actionRIGHT.setOnTouchListener(
                (v, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isRIGHT = false;
                            break;

                        case MotionEvent.ACTION_MOVE:
                            break;

                        case MotionEvent.ACTION_UP:
                            isRIGHT = true;
                            break;
                    }
                    return true;
                }
        );

        actionCOLOR.setOnClickListener(
                //Envía mensaje para cambiar el color
                (v) -> {
                    new Thread(
                            () -> {
                                try {
                                    Thread.sleep(300);
                                    writer.write("COLOR\n");
                                    writer.flush();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                    ).start();
                });

    }
}