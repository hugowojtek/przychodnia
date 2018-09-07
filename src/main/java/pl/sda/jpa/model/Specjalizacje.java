package pl.sda.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="specjalizacje")
public class Specjalizacje {
    @Id
    @Column(name = "ID_specjalizacja")
    private Long id;
    @Column(name="Specjalizacja", nullable = false)
    private String specialization;
}
