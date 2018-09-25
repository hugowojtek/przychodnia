package pl.sda.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "lekarze")
public class Lekarze {

    @Column(name = "ID_lekarz")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "ID_specjalizacja")
    private Specjalizacje specjalizacja;

    @Column(name="Nr_uprawnien")
    private String nrUprawnien;

    @Column(name="Nr_gabinetu")
    private String nrGabinetu;

    @Column(name="Nr_telefonu")
    private String nrTelefonu;

    private String email;

    @Column(name="Cena_wizyty")
    private String cenaWizyty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Specjalizacje getSpecjalizacja(){
        return this.specjalizacja;
    }

    public void setSpecjalizacja(Specjalizacje specjalizacja){
        this.specjalizacja = specjalizacja;
    }

    public String getNrUprawnien() {
        return nrUprawnien;
    }

    public void setNrUprawnien(String nrUprawnien) {
        this.nrUprawnien = nrUprawnien;
    }

    public String getNrGabinetu() {
        return nrGabinetu;
    }

    public void setNrGabinetu(String nrGabinetu) {
        this.nrGabinetu = nrGabinetu;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCenaWizyty() {
        return cenaWizyty;
    }

    public void setCenaWizyty(String cenaWizyty) {
        this.cenaWizyty = cenaWizyty;
    }

    @Override
    public String toString() {
        return "Lekarze{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", specjalizacja=" + specjalizacja +
                ", nrUprawnien='" + nrUprawnien + '\'' +
                ", nrGabinetu='" + nrGabinetu + '\'' +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                ", email='" + email + '\'' +
                ", cenaWizyty='" + cenaWizyty + '\'' +
                '}';
    }
}
