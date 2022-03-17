package com.example.apprechargeligne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button _btnCon;
    EditText _editLogin,_editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnCon = (Button) findViewById(R.id.bntConnexion);
        _editLogin=(EditText) findViewById(R.id.editLogin);
        _editPassword=(EditText) findViewById(R.id.editPassword);




        _btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = _editLogin.getText().toString().trim();
                String password = _editPassword.getText().toString().trim();

                if(login.equals("sami") && password.equals("123456")) {
                    Toast.makeText(MainActivity.this,"Connexion avec succée",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Formulaire.class);

                    intent.putExtra("login",login);
                    startActivity(intent);
                }
            }
        });

    }


}