package com.mecelik.harun.garsoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    FirebaseDatabase database;
    ArrayList<MenuModel> menuList,secilenMenuList;
    ListView menuListele,seclenMenuListele;
    Button masaDon,menuSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        secilenMenuList=new ArrayList<MenuModel>();
        menuList=new ArrayList<MenuModel>();

        database= FirebaseDatabase.getInstance();
        final DatabaseReference dbref=database.getReference("menuler");

        seclenMenuListele=(ListView) findViewById(R.id.listViewSecilen);
        menuListele=(ListView) findViewById(R.id.listView);

        menuSec=(Button) findViewById(R.id.btn_menusec) ;
        masaDon=(Button) findViewById(R.id.btn_masa);

        final Intent intentmasa= new Intent(this,MasaActivity.class);
        masaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentmasa);
            }
        });
        final MenuAdapter adapter=new MenuAdapter(this,menuList);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String  yAdi=ds.child("YemekAdi").getValue().toString();
                    String yFiyat=ds.child("YemekFiyati").getValue().toString();
                    menuList.add(new MenuModel(yAdi,yFiyat));
                }
                dbref.removeEventListener(this);
                menuListele.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final MenuAdapter adaptersecilen=new MenuAdapter(this,secilenMenuList);
        menuListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            int id;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                id=(int)i;
                MenuModel secilenmenu=(MenuModel) menuList.get(menuList.size()-id-1);

                secilenMenuList.add(new MenuModel(secilenmenu.getYAdi(),secilenmenu.getYFiyat()));

                seclenMenuListele.setAdapter(adaptersecilen);
                adaptersecilen.notifyDataSetChanged();
            }
        });
        seclenMenuListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int id2;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                id2= (int)l;
                secilenMenuList.remove(secilenMenuList.size()-id2-1);

                seclenMenuListele.setAdapter(adaptersecilen);
                adaptersecilen.notifyDataSetChanged();
            }
        });


        final DatabaseReference dbrefmenu=database.getReference("secilenmenuler");
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Intent intentVerilenSiparisEkran =new Intent(this,IstenenMenuActivity.class);

        menuSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String s_yAdi,s_yFiyat,s_yDurum;

                for(int i=0;i<secilenMenuList.size();i++){
                    MenuModel secilenmenu=(MenuModel) secilenMenuList.get(i);
                    DatabaseReference idRef=dbrefmenu.push();
                    s_yAdi=secilenmenu.getYAdi();
                    s_yFiyat=secilenmenu.getYFiyat();
                    s_yDurum="aktif";

                    idRef.child("KullanıcıMail").setValue(firebaseUser.getEmail());
                    idRef.child("YemekAdi").setValue(s_yAdi);
                    idRef.child("YemekFiyati").setValue(s_yFiyat);
                    idRef.child("MenuDurum").setValue(s_yDurum);

                }


                Toast.makeText(getApplicationContext(),"Sipariş Başarıyla Verildi", Toast.LENGTH_LONG).show();
                secilenMenuList.clear();
                seclenMenuListele.setAdapter(adaptersecilen);
                adaptersecilen.notifyDataSetChanged();
            }
        });

    }
}
