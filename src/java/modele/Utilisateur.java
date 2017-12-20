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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author schwartz
 */
@Entity
@XmlRootElement
public class Utilisateur extends Administrateur implements Serializable  {
    
    private String dateNaiss;
    private double taille;
    private double poids;
    
    @OneToMany(mappedBy="utilisateur")
    private List<Activite> activites =new ArrayList();
    
    @ManyToMany
    private List<Utilisateur> listAmi =new ArrayList();
    
  
    // Constructeurs
    public Utilisateur() {
    }

    public Utilisateur(String email, String passWord) {
        super(email, passWord);
    }
    
    
    public Utilisateur(String nom, String prenom, String email, String username, String passWord,
                 String dateNaiss, double taille, double poids ) {
        super(nom, prenom, email, username, passWord);
        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
    }

    // getter, setter

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
    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

  @XmlTransient
    public List<Utilisateur> getListAmi() {
        return listAmi;
    }

    public void setListAmi(List<Utilisateur> listAmi) {
        this.listAmi = listAmi;
    }


    
    // Calcul IMC
    public double calculImc(double taille, double poids){
        return(poids/(Math.pow(taille,2)));
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((super.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "dateNaiss=" + dateNaiss + ", taille=" + taille + ", poids=" + poids +
                ", activites=" + activites + ", listAmi=" + listAmi + '}';
    }

    
}
