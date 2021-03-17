package com.example.practica6_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.BufferedWriter;
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
    }
}