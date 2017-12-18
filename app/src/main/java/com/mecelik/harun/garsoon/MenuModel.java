package com.mecelik.harun.garsoon;

/**
 * Created by HARUN on 29.11.2017.
 */

public class MenuModel {

    private String yAdi,yFiyat;

    public MenuModel(String yAdi,String yFiyat){

        this.yAdi=yAdi;
        this.yFiyat=yFiyat;

    }

    public String getYAdi(){
        return yAdi;
    }
    public void setYAdi(String yAdi){
        this.yAdi=yAdi;
    }

    public String getYFiyat(){
        return yFiyat;
    }
    public void setYFiyat(String yFiyat){
        this.yFiyat=yFiyat;
    }
}
