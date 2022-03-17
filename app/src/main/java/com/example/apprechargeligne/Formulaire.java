package com.example.apprechargeligne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Formulaire extends AppCompatActivity {
    private  static final int REQUEST_CALL =1;

    TextView _textErrorCode,_textNomUser,_textNumTele,_textNomLigne,_textCodeRecharge,_textRechargLigne,_textConsSolde;
    EditText _editNomUser,_editNumTele,_editCodeRecharge,_editRechargLigne,_editConsSolde;
    Button _btnRechargLigne,_btnConsSolde;
    public String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        _textNumTele=(TextView) findViewById(R.id.textNumTel);
        _textNomUser=(TextView) findViewById(R.id.textNomUser);
        _textNomLigne=(TextView) findViewById(R.id.textNomLigne);
        _textCodeRecharge=(TextView) findViewById(R.id.textCodeRech);
        _textRechargLigne=(TextView) findViewById(R.id.textRechLigne);
        _textConsSolde=(TextView) findViewById(R.id.textConsSolde);
        _textErrorCode=(TextView) findViewById(R.id.textErrorCode);

        _editNumTele=(EditText) findViewById(R.id.editNumTel);
        _editCodeRecharge=(EditText) findViewById(R.id.editCodeRech);
        _editRechargLigne=(EditText) findViewById(R.id.editRechLigne);
        _editConsSolde=(EditText) findViewById(R.id.editConsSolde);

       _btnRechargLigne=(Button) findViewById(R.id.btnRechLigne);
        _btnConsSolde=(Button) findViewById(R.id.btnConsSolde);

        Intent data = getIntent();
        login = data.getExtras().getString("login");
        _textNomUser.setText(login);


        // On saisie le numero télé puis on appuie sur ENTRER
        _editNumTele.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               String numero = _editNumTele.getText().toString().trim();
                char Ind1 = _editNumTele.getText().charAt(0);
                String numTel = String.valueOf(Ind1);
                if(numero.length() >8 || numero.length() < 8 ) {
                    _textNomLigne.setText("Le numéro de téléphne doit étre 8 chiffre");
                }
                else{
                    if (numTel.equals("2")) {
                        _textNomLigne.setTextColor(Color.parseColor("#FF0000"));
                        _textCodeRecharge.setText("Entrer votre code de recharge (14 chiffre)");
                        _editRechargLigne.setBackgroundColor(Color.parseColor("#FF0000"));
                        _editConsSolde.setBackgroundColor(Color.parseColor("#FF0000"));
                        _editConsSolde.setText("*100#");
                        _textNomLigne.setText("Votre ligne est ooredoo");
                    } else if (numTel.equals("3")) {
                        _textNomLigne.setTextColor(Color.parseColor("#FF7F00"));
                        _textCodeRecharge.setText("Entrer votre code de recharge (14 chiffre)");
                        _editRechargLigne.setBackgroundColor(Color.parseColor("#FF7F00"));
                        _editConsSolde.setBackgroundColor(Color.parseColor("#FF7F00"));
                        _editConsSolde.setText("*111#");
                        _textNomLigne.setText("Votre ligne est orange");
                    } else if (numTel.equals("9")) {
                        _textNomLigne.setTextColor(Color.parseColor("#0000FF"));
                        _textCodeRecharge.setText("Entrer votre code de recharge (13 chiffre)");
                        _editRechargLigne.setBackgroundColor(Color.parseColor("#0000FF"));
                        _editConsSolde.setBackgroundColor(Color.parseColor("#0000FF"));
                        _editConsSolde.setText("*122#");
                        _textNomLigne.setText("Votre ligne est télécom");
                    }
                }
                return false;
            }
        });

        // On saisie le code de recharge puis on appuie sur ENTRER

        _editCodeRecharge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String code = _editCodeRecharge.getText().toString().trim();
                char numTele = _editNumTele.getText().charAt(0);
               // int tailleCode = _editCodeRecharge.length();

                String ligne = String.valueOf(numTele);

                    if (ligne.equals("2")) {
                        if(code.length() >14 || code.length() < 14 ) {
                            _textErrorCode.setText("Le code de recharge doit étre 14 chiffre");
                        }else {
                            _textErrorCode.setText("");
                            String codeRecharge = _editCodeRecharge.getText().toString().trim();
                            _editRechargLigne.setText("*100*" + codeRecharge + "#");
                        }
                        } else if (ligne.equals("3")) {
                        if(code.length() >14 || code.length() < 14 ) {
                            _textErrorCode.setText("Le code de recharge doit étre 14 chiffre");
                        }else {
                            _textErrorCode.setText("");
                            String codeRecharge = _editCodeRecharge.getText().toString().trim();
                            _editRechargLigne.setText("*101*" + codeRecharge + "#");
                        }
                        } else if (ligne.equals("9")) {
                        if(code.length() >13 || code.length() < 13 ) {
                            _textErrorCode.setText("Le code de recharge doit étre 13 chiffre");
                        }else {
                            _textErrorCode.setText("");
                            String codeRecharge = _editCodeRecharge.getText().toString().trim();
                            _editRechargLigne.setText("*123*" + codeRecharge + "#");
                        }
                     }

                return false;
            }
        });

        // On clique sur le bouton pour recharger le code
      _btnRechargLigne.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              RechargeLigne();
          }
      });
// On clique sur le bouton pour consulter le solde
      _btnConsSolde.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          ConsulterSolde();
          }
      });
    }


    private void RechargeLigne(){
        String code =  _editRechargLigne.getText().toString();
        if( code.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Formulaire.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Formulaire.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(Formulaire.this, "Entrer le code", Toast.LENGTH_LONG).show();
        }
    }


    private void ConsulterSolde(){
        String code =  _editConsSolde.getText().toString();
        if( code.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Formulaire.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Formulaire.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(Formulaire.this, "Entrer le code", Toast.LENGTH_LONG).show();
        }
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               ConsulterSolde();
                RechargeLigne();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }





}