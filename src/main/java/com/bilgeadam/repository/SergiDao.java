package com.bilgeadam.repository;

import com.bilgeadam.entity.Eser;
import com.bilgeadam.entity.Sergi;
import com.bilgeadam.entity.Ziyaretci;
import com.bilgeadam.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

public class SergiDao {
    Session session= HibernateUtil.getSessionFactory().openSession();

    public void save(Sergi sergi){
        try{

            session.beginTransaction();
            session.save(sergi);
            session.getTransaction().commit();
            session.close();
            System.out.println("SAVE Success");
        }catch (Exception e){
            System.out.println(e.getMessage()+"Save Don't Success");
        }
    }

    public List<Sergi> getAllJPQL(){
        session.beginTransaction();
        List<Sergi> sergiList=session.createQuery("select s from Sergi s").list();
        session.getTransaction().commit();
        return sergiList;
    }
    public void getAllNative(){
        String query="select s.id,s.sergiAdi,s.konusu,m.muzeAdi,m.sehir,e.eserAdi,sn.sanatciAdi,\n" +
                "sn.dogumYili,z.ziyaretciAdi,z.yas from sergi as s\n" +
                "inner join muze as m on m.id=s.muze_id\n" +
                "inner join sergi_eser as se on se.Sergi_id=s.id\n" +
                "inner join eser as e on e.id=se.eserList_id\n" +
                "inner join sergi_ziyaretci as sz on s.id=sz.sergiList_id\n" +
                "inner join ziyaretci as z on z.id=sz.ziyaretciList_id\n" +
                "inner join sanatci as sn on sn.id=e.sanatci_id";
        EntityManager entityManager=HibernateUtil.getSessionFactory().createEntityManager();
        List<Object[]> sergiList=entityManager.createNativeQuery(query).getResultList();
        for (Object[] item:sergiList){
            System.out.println(
                    "ID: " + item[0] + " --"
                            + " Sergi Adı: " + item[1] + " --"
                            + " Sergi konusu: " + item[2] +" --"
                            + " Muze Adı: " + item[3] + " --"
                            + " Muze Şehiri: " + item[4] + " --"
                            + " Eser Adı: " + item[5]
                            + " Sanatçı Adı: " + item[6]
                            + " Sanatçı Doğum Yılı: " + item[7]
                            + " Ziyaretçi Adı: " + item[8]
                            + " Ziyaretçi Yas: " + item[9]
            );
        }

    }
    public void getAllHQL(){
        try{
            String queryHQL="from Sergi";
            Query query=session.createQuery(queryHQL);
            List<Sergi> sergiList=query.getResultList();
            for (Iterator iterator= sergiList.iterator(); iterator.hasNext();){
                Sergi item= (Sergi) iterator.next();
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
            System.out.println(e.getMessage()+"sergiDao getAllHQL HATA");
        }
    }
    public void getByNameNative(String word){
        String query="select s.id,s.sergiAdi,s.konusu,m.muzeAdi,m.sehir,e.eserAdi,sn.sanatciAdi,\n" +
                "sn.dogumYili,z.ziyaretciAdi,z.yas from sergi as s\n" +
                "inner join muze as m on m.id=s.muze_id\n" +
                "inner join sergi_eser as se on se.Sergi_id=s.id\n" +
                "inner join eser as e on e.id=se.eserList_id\n" +
                "inner join sergi_ziyaretci as sz on s.id=sz.sergiList_id\n" +
                "inner join ziyaretci as z on z.id=sz.ziyaretciList_id\n" +
                "inner join sanatci as sn on sn.id=e.sanatci_id where s.sergiAdi like ?";
        EntityManager entityManager=HibernateUtil.getSessionFactory().createEntityManager();
        Query query1=entityManager.createNativeQuery(query);
        query1.setParameter(1,"%"+word+"%");
        List<Object[]> sergiList=query1.getResultList();
        for (Object[] item:sergiList){
            System.out.println(
                    "ID: " + item[0] + " --"
                            + " Sergi Adı: " + item[1] + " --"
                            + " Sergi konusu: " + item[2] +" --"
                            + " Muze Adı: " + item[3] + " --"
                            + " Muze Şehiri: " + item[4] + " --"
                            + " Eser Adı: " + item[5]
                            + " Sanatçı Adı: " + item[6]
                            + " Sanatçı Doğum Yılı: " + item[7]
                            + " Ziyaretçi Adı: " + item[8]
                            + " Ziyaretçi Yas: " + item[9]
            );
        }
    }

    public void update(int id){
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Sergi sergi=session.load(Sergi.class,id);
            //sergi.setBaslangiçTarihi("10.02.2024");
            //sergi.setBitisTarihi("30.02.2024");
            Eser eser=session.load(Eser.class,id);
            eser.setYapimYili(1996);
            session.update(eser);
            session.getTransaction().commit();
            session.close();
            System.out.println("update success");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(int id){
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Sergi sergi=session.load(Sergi.class,id);
            session.delete(sergi);
            session.getTransaction().commit();
            session.close();
            System.out.println("delete success");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
