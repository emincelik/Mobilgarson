package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class GecmisActivity extends AppCompatActivity {
    FirebaseDatabase database;
    ArrayList<SiparisMenu> gecmisMenuList;
    ListView gecmisMenuListele;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecmis);

        gecmisMenuListele=(ListView) findViewById(R.id.listView);
        gecmisMenuList=new ArrayList<SiparisMenu>();

        btnMain=(Button) findViewById(R.id.btn_main);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        database= FirebaseDatabase.getInstance();
        final DatabaseReference dbref=database.getReference("secilenmenuler");

        final GecmisMenuAdapter adapter=new GecmisMenuAdapter(this,gecmisMenuList,firebaseUser);

        final Intent intentmain= new Intent(this,MainActivity.class);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String gonderen=ds.child("KullanıcıMail").getValue().toString();
                    String  yAdi=ds.child("YemekAdi").getValue().toString();
                    String yFiyat=ds.child("YemekFiyati").getValue().toString();
                    String yDurum=ds.child("MenuDurum").getValue().toString();
                    gecmisMenuList.add(new SiparisMenu(gonderen,yAdi,yFiyat,yDurum));
                }
                dbref.removeEventListener(this);
                gecmisMenuListele.setAdapter(adapter);
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
