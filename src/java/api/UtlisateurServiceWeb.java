///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package api;
//
//import controleur.ControleurUtilisateur;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import modele.Utilisateur;
//
///**
// *
// * @author schwartz
// */
//@Stateless
//@Path("utilisateurwebservice")
//public class UtlisateurServiceWeb {
//
//    @EJB
//    private ControleurUtilisateur controleurUtilisateur;
//    
//    @GET
//    @Produces("application/json")
//    public List<Utilisateur> findAllUtilisateur() {
//        return controleurUtilisateur.findAll();
//    }
//
//    @POST
//    // @Consumes("application/json")
//    @Consumes("application/x-www-form-urlencoded")
//    @Produces("application/json")
//    public Utilisateur createUtilisateur(@FormParam ("nom") String nom, @FormParam ("prenom") String prenom, 
//                        @FormParam ("mail") String mail, @FormParam ("username") String username, 
//                        @FormParam ("password") String password, @FormParam ("dateNaiss") String dateNaiss, 
//                        @FormParam ("taille") double taille, @FormParam ("poids") double poids) {
//        
//        return controleurUtilisateur.create(nom, prenom, mail, username, 
//                                            password, dateNaiss, taille, poids);
//    }
//
//
//   @PUT
////   @Consumes({"application/json","application/xml"})
////   @Produces({"application/json","application/xml"})
//   @Produces("application/json")
//    public Utilisateur udapteUtilisateur(@FormParam ("id") long id,@FormParam ("nom") String nom, @FormParam ("prenom") String prenom, 
//                        @FormParam ("mail") String mail, @FormParam ("username") String username, 
//                        @FormParam ("password") String password, @FormParam ("dateNaiss") String dateNaiss, 
//                        @FormParam ("taille") double taille, @FormParam ("poids") double poids) {
//        
//        Utilisateur utilisateurUdapte = new Utilisateur();
//        
//        utilisateurUdapte=controleurUtilisateur.udapte(id,nom, prenom, mail, username, 
//                                            password, dateNaiss, taille, poids);
//        return utilisateurUdapte;
//    }
//
//}
//
//
// 
