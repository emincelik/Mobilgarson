package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class MasaActivity extends AppCompatActivity {

    Button btnmenu,btnverilenmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa);

        btnmenu=(Button) findViewById(R.id.btnmenuac);
        btnverilenmenu=(Button) findViewById(R.id.btn_verilensiparis);

        final Intent intentmenu=new Intent(this,MenuActivity.class);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentmenu);
            }
        });

        final Intent intentsecilenmenu=new Intent(this,IstenenMenuActivity.class);
        btnverilenmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentsecilenmenu);
            }
        });

    }
}