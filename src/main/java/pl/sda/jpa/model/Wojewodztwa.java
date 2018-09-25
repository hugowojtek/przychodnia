package pl.sda.jpa.model;

import javax.persistence.*;

@Entity
public class Wojewodztwa {

    @Column(name = "ID_wojewodztwa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wojewodztwo")
    private String district;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getDistrict(){
        return this.district;
    }

    public void setDistrict(String district){
        this.district = district;
    }

    public String toString(){
        return "id:"+this.id+"wojewodztwo:"+this.district;
    }
}
