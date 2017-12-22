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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modele.Sport;

/**
 *
 * @author schwartz
 */
@Stateless
@Path("webServiceSport")
public class WebServiceSport extends AbstractFacade<Sport> {

    @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    public WebServiceSport() {
        super(Sport.class);
    }

    
    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sport> findAll() {
        
       Query query =em.createQuery("SELECT t FROM Sport as t");
       if(query.getResultList().isEmpty()){
           return null;
       }
       return query.getResultList();    
    }
    
    
    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Sport create(Sport entity) {
        return super.create(entity);
    }
 
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Sport entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Sport find(@PathParam("id") Long id) {
//        return super.find(id);
//    }
//
//   
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Sport> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
