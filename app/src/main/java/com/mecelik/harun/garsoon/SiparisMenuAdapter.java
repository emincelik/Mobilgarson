package com.mecelik.harun.garsoon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by HARUN on 09.12.2017.
 */

public class SiparisMenuAdapter extends BaseAdapter{

    LayoutInflater layoutInflater;
    ArrayList<SiparisMenu> secilenMenuList;
    String durumKontrol="aktif";
    FirebaseUser fUser;

    public SiparisMenuAdapter(Activity activity, ArrayList<SiparisMenu> secilenMenuList,FirebaseUser fUser){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.secilenMenuList=secilenMenuList;
        this.fUser=fUser;
    }

    @Override
    public int getCount() {
        return secilenMenuList.size();
    }

    @Override
    public Object getItem(int position){
        return secilenMenuList.get(getCount()-position-1);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satir=null;
        SiparisMenu siparismenu=(SiparisMenu) getItem(position);
        if(siparismenu.getGonderen().equals(fUser.getEmail())){
            if(siparismenu.getYDurum().equals(durumKontrol)) {
                satir = layoutInflater.inflate(R.layout.siparismenu_satir, null);
                TextView yAdi = (TextView) satir.findViewById(R.id.textYadi);
                TextView yFiyat = (TextView) satir.findViewById(R.id.textYfiyat);
                TextView yDurum=(TextView) satir.findViewById(R.id.textYdurum);
                yAdi.setText(siparismenu.getYAdi());
                yFiyat.setText(siparismenu.getYFiyat());
                yDurum.setText(siparismenu.getYDurum());
            }
            else{
                satir = layoutInflater.inflate(R.layout.siparismenubos_satir, null);
            }
        }
        else{
            satir = layoutInflater.inflate(R.layout.siparismenubos_satir, null);
        }
        return satir;
    }

}
