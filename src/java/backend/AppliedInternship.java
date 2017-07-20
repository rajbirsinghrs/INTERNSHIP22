/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rajbir
 */
public class AppliedInternship extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String senderid=request.getParameter("senderid");
        JSONObject json;
        JSONObject jsonn     = new JSONObject();
        JSONArray  respo = new JSONArray();
        try
        {
            Connection con=dbconnectivity.DbConnectivity.getConnect();
           // "select * from COMPANYLOGINS where email='"+email+"'
            PreparedStatement ps=con.prepareStatement("select * from APPLYINTERNSHIP where senderid like'%"+senderid+"%'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
             //create Json Object
                json = new JSONObject();
                  // put some value pairs into the JSON object .
                  json.put("senderid", rs.getString("senderid"));
                  json.put("reciverid", rs.getString("reciverid"));
                  json.put("message", rs.getString("message"));
                  json.put("messagedate", rs.getString("applydate"));
                   respo.put(json);
                  }
            jsonn.put("responce",respo);
            out.print(jsonn.toString());
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
