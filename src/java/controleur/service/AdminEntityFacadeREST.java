/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import modele.AdminEntity;
import org.apache.commons.codec.digest.*;

/**
 *
 * @author z
 */
@Stateless
@Path("modele.adminentity")
public class AdminEntityFacadeREST extends AbstractFacade<AdminEntity> {

  
  
    @PersistenceContext(unitName = "WebServeurSportPU")
    private EntityManager em;

    
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;


    
    public AdminEntityFacadeREST() {
        super(AdminEntity.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public AdminEntity create(AdminEntity entity) {
        super.create(entity);
        return entity;
    }
    
//    @POST @Consumes("application/json")
//    @Path("/create")
//    public void create(final MyJaxBean input) {
//    System.out.println("param1 = " + input.param1);
//    System.out.println("param2 = " + input.param2);
//}

    
    
    @POST
    @Path("checker")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public void find(AdminEntity ae) throws ServletException, IOException{
        CriteriaBuilder qb = em.getCriteriaBuilder(); // on crree le constructeur des critères
        CriteriaQuery cq = qb.createQuery(); // on crée une requette 
        Root<AdminEntity> adminRoot = cq.from(AdminEntity.class); // on recupere tous les admins
        List<Predicate> predicates = new ArrayList<>(); // on crée la liste de conditions

        if ((ae.getUsername() != null)&&(ae.getPassword() != null)) {  //si le nom d'utilisateur  et mot de pas ne sont pas vide on les ajoute dans les conditions 
        predicates.add(
                qb.equal(adminRoot.get("username"),ae.getUsername()));
        predicates.add(
                qb.equal(adminRoot.get("password"), ae.getPassword()));
    }
   
    
    cq.select(adminRoot).where(predicates.toArray(new Predicate[]{})); // on recupere que les resultats correspondants aux condittions 
  
      
     
      if (!em.createQuery(cq).getResultList().toString().isEmpty()) {
           
        HttpSession session = request.getSession(true);
        session.setAttribute("connected", true);
        session.setAttribute("request", request);
        session.setAttribute("response", response);
        request.getRequestDispatcher("/webserveursport/admin.jsp").forward(request,response);

      } else {
        request.getRequestDispatcher("/webserveursport/admin.jsp").forward(request,response);
      }

          
    }

   
    @POST
    @Path("check")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_PLAIN})
    public void find(@FormParam("username") String string1,@FormParam("password") String string2)  {


      
      string2 = DigestUtils.md5Hex(string2);
      System.out.println("username " + string1);
      System.out.println("password " + string2);
    
      CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery cq = qb.createQuery();
    Root<AdminEntity> customer = cq.from(AdminEntity.class);

    //Constructing list of parameters
    List<Predicate> predicates = new ArrayList<Predicate>();

    //Adding predicates in case of parameter not being null
    if (string1 != null) {
        predicates.add(
                qb.equal(customer.get("username"), string1));
    }
    if (string2 != null) {
        predicates.add(
                qb.equal(customer.get("password"), string2));
    }
    //query itself
    cq.select(customer)
            .where(predicates.toArray(new Predicate[]{}));
    //execute query and do something with result
    if(!em.createQuery(cq).getResultList().isEmpty()) 
    {
      

          try {
          HttpSession session = request.getSession(true);
          session.setAttribute("connected", true);
          session.setAttribute("request", request);
          session.setAttribute("response", response);

            request.getRequestDispatcher("admin.jsp").forward(request,response);
          } catch (ServletException ex) {
            Logger.getLogger(AdminEntityFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
            Logger.getLogger(AdminEntityFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    else{
        try {
          request.getRequestDispatcher("admin.jsp").forward(request,response);
        } catch (ServletException ex) {
          Logger.getLogger(AdminEntityFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(AdminEntityFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//    cq.select(cq.from(AdminEntity.class)).where(new Predicate[]{(customer.get("someAttribute"), param1)});
        
    }
    
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, AdminEntity entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AdminEntity find(@PathParam("id") Long id) {
        return super.find(id);
    }

 
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AdminEntity> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AdminEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
