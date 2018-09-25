package pl.sda.jpa.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Wizyty {
    @Column(name = "ID_wizyty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ld;

//    @Column(name = "Lekarz")
    @OneToOne
    @JoinColumn(name = "ID_lekarz")

    private Lekarze lekarz;

//    @Column(name = "Pacjent")
    @OneToOne
    @JoinColumn(name = "ID_pacjent")
    private Pacjenci pacjent;

    @Column(name = "Data_wizyty")
    Date dataWizyty;

    @Column(name = "Data_umowienia")
    Date dataUmowienia;

    @Column(name = "Aktualna_cena_wizyty")
    private BigDecimal aktualnaCenaWizyty;

    public Long getLd() {
        return ld;
    }

    public void setLd(Long ld) {
        this.ld = ld;
    }

    public Lekarze getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarze lekarz) {
        this.lekarz = lekarz;
    }

    public Pacjenci getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjenci pacjent) {
        this.pacjent = pacjent;
    }

    public Date getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(Date dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public Date getDataUmowienia() {
        return dataUmowienia;
    }

    public void setDataUmowienia(Date dataUmowienia) {
        this.dataUmowienia = dataUmowienia;
    }

    public BigDecimal getAktualnaCenaWizyty() {
        return aktualnaCenaWizyty;
    }

    public void setAktualnaCenaWizyty(BigDecimal aktualnaCenaWizyty) {
        this.aktualnaCenaWizyty = aktualnaCenaWizyty;
    }
}
