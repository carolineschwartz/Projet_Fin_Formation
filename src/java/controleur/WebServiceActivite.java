/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modele.Activite;
import modele.Utilisateur;

/**
 *
 * @author schwartz
 */
@Stateless
@Path("webServiceActivite")
public class WebServiceActivite extends AbstractFacade<Activite> {

    @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    public WebServiceActivite() {
        super(Activite.class);

    }

    @GET
    @Path("findActivite/{id : (\\w+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activite> findActivite(@PathParam("id") String id) {
 
        if (id.isEmpty()) {
           return super.findAll();
        }

        String request = "SELECT a FROM Activite a WHERE a.utilisateur.id = :id";
   
        long longId= Long.parseLong(id);
        Query query = em.createQuery(request);
        query.setParameter("id", longId);

       return query.getResultList(); 
       

       
       }

      
    
  
        
    
    
    
    @POST
    @Path("create")
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Activite create(Activite entity) {
        return super.create(entity);
    }

    

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Activite entity) {
//        super.edit(entity);
//    }
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Activite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Activite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
