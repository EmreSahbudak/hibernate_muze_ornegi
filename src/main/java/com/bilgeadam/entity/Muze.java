package com.bilgeadam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Muze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String muzeAdi;
    private String sehir;
    private String acilisSaati;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "muze")
    private List<Sergi> sergiList;

    public Muze(String muzeAdi, String sehir, String acilisSaati) {
        this.muzeAdi = muzeAdi;
        this.sehir = sehir;
        this.acilisSaati = acilisSaati;
    }
}
