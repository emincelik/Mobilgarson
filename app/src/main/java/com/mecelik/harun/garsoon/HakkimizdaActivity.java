package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by HARUN on 08.11.2017.
 */

public class HakkimizdaActivity extends AppCompatActivity {
    FirebaseDatabase database;
    ArrayList<Hakkimizda> hakkimizdaList;
    ListView hakkimizdaListele;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);

        hakkimizdaListele=(ListView) findViewById(R.id.listViewHakkimizda);
        hakkimizdaList=new ArrayList<Hakkimizda>();

        btnMain=(Button) findViewById(R.id.btn_main);

        database= FirebaseDatabase.getInstance();
        final DatabaseReference dbref=database.getReference("hakkimizda");

        final HakkimdaAdapter adapter=new HakkimdaAdapter(this,hakkimizdaList);

        final Intent intentmain= new Intent(this,MainActivity.class);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String hakkimda=ds.child("Hakkimda").getValue().toString();

                    hakkimizdaList.add(new Hakkimizda(hakkimda));
                }
                dbref.removeEventListener(this);
                hakkimizdaListele.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentmain);
            }
        });

    }
}