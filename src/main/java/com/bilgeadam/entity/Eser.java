package com.bilgeadam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Eser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String eserAdi;
    private int yapimYili;

    @ManyToOne(cascade = CascadeType.ALL)
    private Sanatci sanatci;

    @ManyToOne(cascade = CascadeType.ALL)
    private Sergi sergi;

    public Eser(int yapimYili) {
        this.yapimYili = yapimYili;
    }

    public Eser(String eserAdi, int yapimYili, Sanatci sanatci) {
        this.eserAdi = eserAdi;
        this.yapimYili = yapimYili;
        this.sanatci = sanatci;
    }
}

