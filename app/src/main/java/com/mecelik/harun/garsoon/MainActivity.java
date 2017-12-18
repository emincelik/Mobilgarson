package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button btnMasa,btnHakkimizda,btnGecmis;
    public Intent yeni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMasa=(Button) findViewById(R.id.btnmasa);
        btnHakkimizda=(Button) findViewById(R.id.btnhkkmz);
        btnGecmis=(Button) findViewById(R.id.btngcms) ;

        btnMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                yeni=new Intent(MainActivity.this,QrkodtaraActivitiy.class);
                startActivity(yeni);
            }
        });
        btnGecmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yeni=new Intent(MainActivity.this,GecmisActivity.class);
                startActivity(yeni);
            }
        });

        btnHakkimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yeni=new Intent(MainActivity.this,HakkimizdaActivity.class);
                startActivity(yeni);
            }
        });
    }



}
