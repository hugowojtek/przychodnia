package pl.sda.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pacjenci implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_pacjent")
    private Long id;
    private String imie;
    private String nazwisko;
    private String pesel;
    @Column(name = "Data_urodzenia")
    private Date dataUrodzenia;
    private String plec;
    private String miasto;
    private String ulica;
    @Column(name = "nr_lokalu")
    private String nrLokalu;
    @OneToOne
    @JoinColumn(name = "ID_wojewodztwa")
    private Wojewodztwa wojewodztwo;

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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrLocalu() {
        return nrLokalu;
    }

    public void setNrLocalu(String nrLocalu) {
        this.nrLokalu = nrLocalu;
    }

    public Wojewodztwa getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwa wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @Override
    public String toString() {
        return "Pacjenci{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", plec='" + plec + '\'' +
                ", miasto='" + miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nrLokalu='" + nrLokalu + '\'' +
                ", wojewodztwo=" + wojewodztwo +
                '}';
    }
}
