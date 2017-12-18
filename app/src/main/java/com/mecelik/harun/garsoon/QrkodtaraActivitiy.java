package com.mecelik.harun.garsoon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QrkodtaraActivitiy extends AppCompatActivity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    FirebaseDatabase database;
    public TextView txt,edt;
    public String barkodKntrl;
    public String qrkoddeger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the main content layout of the Activity
        setContentView(R.layout.activity_qrkod);

        txt=(TextView) findViewById(R.id.textView1);
        edt=(TextView) findViewById(R.id.edt);
    }

    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.setPackage("com.google.zxing.client.android");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(QrkodtaraActivitiy.this, "QR Kodu tarayıcısı bulunamadı", "Indirmek ister misiniz ?", "Evet", "Hayır").show();
        }

    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                qrkoddeger=contents;
                deger(qrkoddeger);

                Toast toast = Toast.makeText(this, "Icerik:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show(
                );

            }
        }
    }

    public void deger(String b){
        final String kontrol=b;
        database=FirebaseDatabase.getInstance();
        final DatabaseReference dbref=database.getReference("qrkodlar");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String  qrkodno=ds.child("QrkodNo").getValue().toString();
                    String masano=ds.child("MaasaNo").getValue().toString();
                    String restaurantadi=ds.child("RestaurantNo").getValue().toString();
                    barkodKntrl=qrkodno;
                    if(barkodKntrl.equals(kontrol)){

                        Intent yeni=new Intent(QrkodtaraActivitiy.this,MasaActivity.class);
                        startActivity(yeni);
                    }
                    else {
                        edt.setText("Geçersiz");
                    }

                }
                dbref.removeEventListener(this);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
