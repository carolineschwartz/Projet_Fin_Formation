/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Administrateur;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author z
 */
public class AdminServlet extends HttpServlet {

  @PersistenceContext(unitName = "WebServeurSportPU")
  private EntityManager em;

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet AdminServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }

  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    String string1 = request.getParameter("username");
    String string2 = request.getParameter("password");

    if (!string1.isEmpty() && !string2.isEmpty()) {

      string2 = DigestUtils.md5Hex(string2);
      System.out.println("username " + string1);
      System.out.println("password " + string2);

      CriteriaBuilder qb = em.getCriteriaBuilder();
      CriteriaQuery cq = qb.createQuery();
      Root<Administrateur> customer = cq.from(Administrateur.class);

      //Constructing list of parameters
      List<Predicate> predicates = new ArrayList<>();

      //Adding predicates in case of parameter not being null
      predicates.add(
          qb.equal(customer.get("username"), string1));

      predicates.add(
          qb.equal(customer.get("password"), string2));

      //query itself
      cq.select(customer)
          .where(predicates.toArray(new Predicate[]{}));
      //execute query and do something with result
      if (!em.createQuery(cq).getResultList().isEmpty()) {

        redirect(true, request, response, session);
      } 

    } 
      redirect(false, request, response, session);

    

  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void redirect(boolean _connected, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    try {
      session.setAttribute("connected", _connected);
      session.setAttribute("request", request);
      session.setAttribute("response", response);
      //session.setAttribute("list", em.createQuery(cq).getResultList());

      request.getRequestDispatcher("admin.jsp").forward(request, response);
    } catch (ServletException | IOException ex) {
      Logger.getLogger(WebServiceAdministrateur.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
