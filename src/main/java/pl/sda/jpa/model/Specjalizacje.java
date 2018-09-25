package pl.sda.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="specjalizacje")
@NamedQuery(name="Spec.findAll", query = "SELECT s FROM Specjalizacje s")
public class Specjalizacje implements Serializable{
    @Column(name = "ID_specjalizacja")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="Specjalizacja", nullable = false)
    private String specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String toString(){
        return "id:"+id+"-specjalizacja:"+specialization;
    }


}
