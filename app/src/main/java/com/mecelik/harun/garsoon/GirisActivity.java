package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity {

    EditText et_UserSifre,et_UserMail;
    Button buttonGirisYap;
    TextView tv_uyari;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        et_UserSifre=(EditText) findViewById(R.id.editTextUserPassword);
        et_UserMail=(EditText) findViewById(R.id.editTextUserEmail);
        buttonGirisYap=(Button) findViewById(R.id.buttonGirisYap);
        tv_uyari=(TextView) findViewById(R.id.textViewKayitUyarisi);

        mAuth=FirebaseAuth.getInstance();

        final Intent authIntent=new Intent(this,MainActivity.class);

        final String kontrol="";
        buttonGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=et_UserMail.getText().toString();
                String userSifre=et_UserSifre.getText().toString();
               if((!userEmail.equals(kontrol))&&(!userSifre.equals(kontrol)) ){
                   mAuth.signInWithEmailAndPassword(userEmail, userSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               startActivity(authIntent);
                           } else {
                               Toast.makeText(getApplicationContext(), "Giriş yapılamadı", Toast.LENGTH_SHORT).show();

                           }
                       }
                   });
               }
               else{
                   Toast.makeText(getApplicationContext(), "Lütfen gerekli tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show();
               }
            }
        });

        tv_uyari.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent uyariIntent=new Intent(getApplicationContext(),KayitOlActivity.class);
                startActivity(uyariIntent);
            }
        });

    }


}
