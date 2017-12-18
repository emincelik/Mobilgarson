package com.mecelik.harun.garsoon;

/**
 * Created by HARUN on 09.12.2017.
 */

public class SiparisMenu {
    private String  gonderen, yAdi,yFiyat,yDurum;

    public SiparisMenu(String  gonderen,String yAdi,String yFiyat, String yDurum){

        this.gonderen=gonderen;
        this.yAdi=yAdi;
        this.yFiyat=yFiyat;
        this.yDurum=yDurum;

    }

    public String getGonderen(){return gonderen;}
    public void setGonderen(String gonderen){
        this.gonderen=gonderen;
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

    public String getYDurum(){
        return yDurum;
    }
    public void setYDurum(String yDurum){
        this.yDurum=yDurum;
    }
}
