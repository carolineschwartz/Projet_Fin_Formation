
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author schwartz
 */
@Entity
@XmlRootElement
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String iconeUrl;
    // Nb de calories perdues par heure pour le sport correspondant
    private int calorie;
    
    @OneToMany(mappedBy="sport")
    private List<Video> videos = new ArrayList<>();

//    @OneToOne(mappedBy="sport")
//    private Activite activite;
   
    
    //Constructeurs
    public Sport() {
    }

    public Sport(String nom, String iconeUrl) {
        this.nom = nom;
        this.iconeUrl = iconeUrl;
    }

    public Sport(String nom, String iconeUrl, int calorie) {
        this.nom = nom;
        this.iconeUrl = iconeUrl;
        this.calorie = calorie;
    }
    
    // getter et setter
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

    public String getIconeUrl() {
        return iconeUrl;
    }

    public void setIconeUrl(String iconeUrl) {
        this.iconeUrl = iconeUrl;
    }

//    public Activite getActivite() {
//        return activite;
//    }
//
//    public void setActivite(Activite activite) {
//        this.activite = activite;
//    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

//    public List<Video> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<Video> videos) {
//        this.videos = videos;
//    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    
}
