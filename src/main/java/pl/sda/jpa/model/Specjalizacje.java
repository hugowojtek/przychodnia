package pl.sda.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="specjalizacje")
@NamedQuery(name="Spec.findAll", query = "SELECT s FROM Specjalizacje s")
public class Specjalizacje {
    @Id
    @Column(name = "ID_specjalizacja")
    private Long id;
    @Column(name="Specjalizacja", nullable = false)
    private String specialization;
}
