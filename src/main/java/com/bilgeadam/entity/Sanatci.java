package com.bilgeadam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sanatci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sanatciAdi;
    private String dogumYili;
    private String olumYili;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sanatci")
    private List<Eser> eserList;

    public Sanatci(String sanatciAdi, String dogumYili) {
        this.sanatciAdi = sanatciAdi;
        this.dogumYili = dogumYili;
    }

    public Sanatci(String sanatciAdi, String dogumYili, String olumYili) {
        this.sanatciAdi = sanatciAdi;
        this.dogumYili = dogumYili;
        this.olumYili = olumYili;
    }
}
