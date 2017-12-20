/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modele.Utilisateur;

/**
 *
 * @author schwartz
 */
@Stateless
@Path("webServiceUtilisateurPassWord")
public class WebServiceUtilisateurPassWord extends AbstractFacade<Utilisateur> {

    /**
     * This is a sample web service operation
     */
    @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    public WebServiceUtilisateurPassWord() {
        super(Utilisateur.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur findByEmail(Utilisateur entity) {
        Utilisateur resultEntity = null;

        String request = "SELECT e.id FROM Utilisateur as e WHERE e.email  = ?1";
        Query query = em.createQuery(request);
        query.setParameter(1, entity.getEmail());

        if (query.getResultList().isEmpty()) {
            return resultEntity;
        } else if (super.find(query.getResultList()).getPassWord().equals(entity.getPassWord())) {
            return super.find(query.getResultList());
        } else {
            return resultEntity;

        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
