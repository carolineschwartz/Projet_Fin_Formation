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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author schwartz
 */
@Entity
@XmlRootElement
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String passWord;  
    private String dateNaiss;
    private double taille;
    private double poids;
    
    @ManyToMany
    private List<Sport> sports =new ArrayList();
    
    
    // Constructeurs
    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String prenom, String mail, String username, String passWord, String dateNaiss, double taille, double poids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.username = username;
        this.passWord = passWord;
        this.dateNaiss = dateNaiss;
        this.taille = taille;
        this.poids = poids;
    }
    
    public Utilisateur(String nom, String prenom, String mail, String username, String passWord, 
                        String dateNaiss,  double taille, double poids) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.passWord = passWord;
        this.mail = mail;
        this.dateNaiss = dateNaiss;
        this.poids = poids;
        this.taille = taille;
    }

    public Utilisateur(String mail, String passWord) {
        this.mail = mail;
        this.passWord = passWord;
    }

    
    // getter, setter
    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
    
    public Long getId() {
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getpassWord() {
        return passWord;
    }

    public void setpassWord(String passWord) {
        this.passWord = passWord;
    }
    

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }
    
    // Calcul IMC
    public double calculImc(double taille, double poids){
        return(poids/(sqr(taille)));
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Utilisateur[ id=" + id + " ]";
    }

    private double sqr(double taille) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
