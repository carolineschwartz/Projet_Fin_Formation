/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author z
 */
@Entity
@XmlRootElement
//@SecondaryTable(name="UTILISATEURENTITY")
public class UtilisateurEntity extends AdminEntity implements Serializable {


    private String dateNaiss;
    private double taille;
    private double poids;
    
    @ManyToMany
    private List<SportEntity> sports =new ArrayList();

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String nom, String prenom, String mail, String username, String password, String dateNaiss, double taille, double poids) {

        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
        
    }
    
    
    
    
    
    
    public Long getId() {
        return super.id;
    }

    public void setId(Long id) {
        super.id = id;
    }

    public String getNom() {
        return super.nom;
    }

    public void setNom(String nom) {
        super.nom = nom;
    }

    public String getPrenom() {
        return super.prenom;
    }

    public void setPrenom(String prenom) {
        super.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        super.mail = mail;
    }

    public String getUsername() {
        return super.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    @XmlTransient
    public List<SportEntity> getSports() {
        return sports;
    }

    public void setSports(List<SportEntity> sports) {
        this.sports = sports;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Long) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtilisateurEntity)) {
            return false;
        }
        UtilisateurEntity other = (UtilisateurEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.UtilisateurEntity[ id=" + id + " ]";
    }
    
}
