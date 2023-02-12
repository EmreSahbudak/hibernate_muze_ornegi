package com.bilgeadam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ziyaretci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ziyaretciAdi;
    private int yas;
    private String telNo;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "ziyaretciList")
    private List<Sergi> sergiList;

    public Ziyaretci(String ziyaretciAdi, int yas, String telNo) {
        this.ziyaretciAdi = ziyaretciAdi;
        this.yas = yas;
        this.telNo = telNo;
    }
}
