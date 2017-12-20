/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author schwartz
 */
@Entity
@XmlRootElement
public class Activite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double duree;
    private int nombre;
     private String date;
    private Double caloriePerdues;

    @OneToOne
    private Sport sport;

    @OneToOne
    private Utilisateur utilisateur;

//    @Temporal(TemporalType.DATE    @Column(name = "DATE_FIELD")
//    private java.util.Date dateField;
    
    //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    // System.out.println("Date : " + date);
        
    // Constructeurs
    public Activite() {
    }

    public Activite(Long id, double duree, int nombre, Double caloriePerdues, 
            Sport sport, Utilisateur utilisateur) {
        this.id = id;
        this.duree = duree;
        this.nombre = nombre;
        this.caloriePerdues = caloriePerdues;
        this.sport = sport;
        this.utilisateur = utilisateur;
    }

    

    public Activite(double duree, String date, Sport sport,
            Utilisateur utilisateur, Double caloriePerdues) {
        this.duree = duree;
        this.date = date;
        this.sport = sport;
        this.utilisateur = utilisateur;
        this.caloriePerdues = caloriePerdues;
    }

    public Activite(double duree, int nombre, String date, Sport sport,
            Utilisateur utilisateur, Double caloriePerdues) {
        this.duree = duree;
        this.nombre = nombre;
        this.date = date;
        this.sport = sport;
        this.utilisateur = utilisateur;
        this.caloriePerdues = caloriePerdues;
    }

    // getter et setter
//    public java.util.Date getDateField() {
//        return dateField;
//    }
//
//    public void setDateField(java.util.Date dateField) {
//        this.dateField = dateField;
//    }
    
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public Double getCaloriePerdues() {
        return caloriePerdues;
    }

    public void setCaloriePerdues(Double caloriePerdues) {
        this.caloriePerdues = caloriePerdues;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Sport[ id=" + id + " ]";
    }

}
