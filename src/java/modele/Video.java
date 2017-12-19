/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author z
 */
@Entity
@XmlRootElement
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String nom;
    
    @ManyToOne
    private Sport sport;
    
   // @OneToOne (targetEntity = modele.SportEntity.class, mappedBy = "id", cascade = CascadeType.ALL)
   // private int sportId;

    public Video() {
    }

    public Video(String url) {
        this.url = url;
    }

    public Video(String url, int sportId) {
        this.url = url;
        this.sport = sport;
    }

    public String getNom() {
      return nom;
    }

    public void setNom(String nom) {
      this.nom = nom;
    }

    public Sport getSport() {
      return sport;
    }

    public void setSport(Sport sport) {
      this.sport = sport;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Sport getSportId() {
        return sport;
    }

    public void setSportId(Sport sport) {
        this.sport = sport;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Video[ id=" + id + " ]";
    }
    
}