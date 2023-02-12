package com.bilgeadam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sergi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sergiAdi;
    private String baslangiçTarihi;
    private String bitisTarihi;
    private String konusu;

    @ManyToOne(cascade = CascadeType.ALL)
    private Muze muze;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Eser> eserList;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ziyaretci> ziyaretciList;

    public Sergi(Integer id) {
        this.id = id;
    }

    public Sergi(String baslangiçTarihi) {
        this.baslangiçTarihi = baslangiçTarihi;
    }

    public Sergi(String sergiAdi, String baslangiçTarihi, String bitisTarihi,
                 String konusu, Muze muze, List<Eser> eserList, List<Ziyaretci> ziyaretciList) {
        this.sergiAdi = sergiAdi;
        this.baslangiçTarihi = baslangiçTarihi;
        this.bitisTarihi = bitisTarihi;
        this.konusu = konusu;
        this.muze = muze;
        this.eserList = eserList;
        this.ziyaretciList = ziyaretciList;
    }
}
