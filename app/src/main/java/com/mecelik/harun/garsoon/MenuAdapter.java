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
 * Created by HARUN on 29.11.2017.
 */

public class MenuAdapter  extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<MenuModel> menuList;

    public MenuAdapter(Activity activity, ArrayList<MenuModel> menuList){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.menuList=menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position){
        return menuList.get(getCount()-position-1);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MenuModel menu=(MenuModel) getItem(position);
        View satir=layoutInflater.inflate(R.layout.menu_satir,null);
        TextView yAdi=(TextView) satir.findViewById(R.id.textYadi);
        TextView yFiyat=(TextView) satir.findViewById(R.id.textYfiyat);
        yAdi.setText(menu.getYAdi());
        yFiyat.setText(menu.getYFiyat());
        return satir;

    }

}
