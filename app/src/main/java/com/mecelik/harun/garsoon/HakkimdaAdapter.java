package com.mecelik.harun.garsoon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HARUN on 17.12.2017.
 */

public class HakkimdaAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Hakkimizda> hakkimdaList;

    public HakkimdaAdapter(Activity activity, ArrayList<Hakkimizda> hakkimdaList){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.hakkimdaList=hakkimdaList;
    }

    @Override
    public int getCount() {
        return hakkimdaList.size();
    }

    @Override
    public Object getItem(int position){
        return hakkimdaList.get(getCount()-position-1);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Hakkimizda hakkımda=(Hakkimizda) getItem(position);
        View satir=layoutInflater.inflate(R.layout.hakkimda_satir,null);

        TextView hakkimizda=(TextView) satir.findViewById(R.id.textHakkimda);

        hakkimizda.setText(hakkımda.getHakkimda());

        return satir;

    }

}
