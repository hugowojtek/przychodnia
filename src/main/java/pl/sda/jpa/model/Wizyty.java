package pl.sda.jpa.model;

import org.hibernate.type.DateType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Wizyty implements Serializable{
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
    LocalDateTime dataWizyty;

    @Column(name = "Data_umowienia")
    LocalDateTime dataUmowienia;

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

    public LocalDateTime getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(LocalDateTime dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public LocalDateTime getDataUmowienia() {
        return dataUmowienia;
    }

    public void setDataUmowienia(LocalDateTime dataUmowienia) {
        this.dataUmowienia = dataUmowienia;
    }

    public BigDecimal getAktualnaCenaWizyty() {
        return aktualnaCenaWizyty;
    }

    public void setAktualnaCenaWizyty(BigDecimal aktualnaCenaWizyty) {
        this.aktualnaCenaWizyty = aktualnaCenaWizyty;
    }
}
