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
import modele.Utilisateur;

/**
 *
 * @author schwartz
 */
@Stateless
public class ControleurUtilisateur {

    @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Utilisateur create(String nom, String prenom, String mail, 
                                       String username, String password, 
                                       String dateNaiss, double taille, double poids) {
        
        Utilisateur utilisateurNew = new Utilisateur(nom, prenom, mail, username, 
                                            password, dateNaiss, taille, poids);
        em.persist(utilisateurNew);
        return utilisateurNew;
    }

    
    public Utilisateur findById(long id) {      
       return (Utilisateur) em.find(Utilisateur.class, id);
    }
    
    // Requete JPQL
    public List<Utilisateur> findAll(){
       List<Utilisateur> listeUtilisateur = new ArrayList();
       Query query =em.createQuery("SELECT p FROM Utilisateur p");
       listeUtilisateur=query.getResultList();
       return listeUtilisateur;
    }
    
    public void delete(long id){
        em.remove(this.findById(id));
    }
    
    public Utilisateur udapte(long id, String nom, String prenom, String mail, String username, String passWord, 
                        String dateNaiss,  double taille, double poids){
        
        Utilisateur utilisateurUdapte = this.findById(id);
        
        utilisateurUdapte.setNom(nom);
        utilisateurUdapte.setPrenom(prenom);
        utilisateurUdapte.setMail(mail);
        utilisateurUdapte.setpassWord(passWord);
        utilisateurUdapte.setDateNaiss(dateNaiss);
        utilisateurUdapte.setTaille(taille);
        utilisateurUdapte.setPoids(poids);
        em.merge(utilisateurUdapte);
        return utilisateurUdapte;
    }
}


