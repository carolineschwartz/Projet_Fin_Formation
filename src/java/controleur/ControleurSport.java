/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.Sport;

/**
 *
 * @author schwartz
 */
@Stateless
public class ControleurSport {
  
     @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Sport createSport(String nom){
        Sport sport = new Sport(nom);
        em.persist(sport);
        return sport;
        
    }
       
    public Sport findById(long id){
        return (Sport)em.find(Sport.class, id);
    }
    
    public List<Sport> findAll(){
       List<Sport> listeSport = new ArrayList();
       Query query =em.createQuery("SELECT p FROM Utilisateur p");
       listeSport=query.getResultList();
       return listeSport;
    }
    
}
