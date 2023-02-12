package com.bilgeadam.service;

import com.bilgeadam.entity.*;
import com.bilgeadam.repository.SergiDao;

import java.util.Arrays;
import java.util.List;

public class SergiService {
    static SergiDao sergiDao=new SergiDao();
    public static void main(String[] args) {
        //save();
        //getAllJPQL();
        //getAllNative();
        //getAllHQL();
        //getByNameNative("kork");
        //update(1);
        //delete(1);

    }
    public static void save(){
        Sanatci sanatci1=new Sanatci("Max Grego","1996");
        Sanatci sanatci2=new Sanatci("Kevin Malcuit","1995","2021");
        Eser eser1=new Eser("Nefes Heykeli",2022,sanatci1);
        Eser eser2=new Eser("Zuma Heykeli",2005,sanatci2);

        Ziyaretci ziyaretci1=new Ziyaretci("Emre",27,"5548665544");
        Ziyaretci ziyaretci2=new Ziyaretci("Zeki",20,"5543334455");

        Muze muze1=new Muze("Denizli Müzesi","Denizli","10.00");
        Sergi sergi1=new Sergi("Korkusuz","13.02.2023","20.02.2023","Korku",
                muze1, Arrays.asList(eser1,eser2),Arrays.asList(ziyaretci1,ziyaretci2));
        try{
            sergiDao.save(sergi1);
            System.out.println("SAVE Success");
        }catch (Exception e){
            System.out.println(e.getMessage()+"SergiService Save Don't Success");
        }
    }

    public static void getAllJPQL(){
        try{
            List<Sergi> sergiList =sergiDao.getAllJPQL();
            for (Sergi item: sergiList){
                System.out.println("Sergi ID: "+item.getId()+", Sergi Adı: "+item.getSergiAdi()
                        +", Sergi Başlangıç tarihi: "+item.getBaslangiçTarihi()
                        +", Sergi bitiş Tarihi: "+item.getBitisTarihi()
                        +"\n Sergi Konusu: "+item.getKonusu()+", Muze Adı: "+item.getMuze().getMuzeAdi()
                        +", Muze Şehri: "+item.getMuze().getSehir());
                for (Eser item2:item.getEserList()){
                    System.out.println("Eser Adı: "+item2.getEserAdi()+", Eser YapımYili "+item2.getYapimYili()
                            +", Sanatçı Adı: "+item2.getSanatci().getSanatciAdi()
                            +", Sanatcı Doğum Yılı "+item2.getSanatci().getDogumYili());
                }
                for (Ziyaretci item3:item.getZiyaretciList()){
                    System.out.println("Ziyaretci Adı: "+item3.getZiyaretciAdi()
                            +", Ziyateçi TelNo: "+item3.getTelNo()+", Ziyateçi Yaş; "+item3.getYas());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage()+"\nSergiService Don't success getAllJPQL");
        }
    }
    public static void getAllNative(){
        try{
            sergiDao.getAllNative();
        }catch (Exception e){
            System.out.println(e.getMessage()+"\nSergiService Don't success getAllNAtive");
        }
    }
    public static void getAllHQL(){
        try{
            sergiDao.getAllHQL();
        }catch (Exception e){
            System.out.println(e.getMessage()+"\nSergiService Don't success getAllhql");
        }
    }
    public static void getByNameNative(String word){
        try{
            sergiDao.getByNameNative(word);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void update(int id){
        try{
            System.out.println("update success sergiservice");
            sergiDao.update(id);
        }catch (Exception e){
            System.out.println(e.getMessage()+"sergiservice hata update");
        }
    }
    public static void delete(int id){
        try{
            System.out.println("delete success sergiservice");
            sergiDao.delete(id);
        }catch (Exception e){
            System.out.println(e.getMessage()+"sergiservice hata delete");
        }
    }


}
