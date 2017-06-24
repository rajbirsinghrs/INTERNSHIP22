/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cegepgim;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import java.sql.Timestamp;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.sql.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utilities.NewClass;

/**
 * REST Web Service
 *
 * @author Owner-PC
 */
@Path("generic")
public class Internship {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Internship() {
    }
    JSONObject verma = new JSONObject();
    String status;

    /**
     * Retrieves representation of an instance of cegepgim.GenericResource
     *
     * @param id
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Path("loginstudent&{studentid}&{dob}&{password}")
    @GET
    @Produces("application/json")
    public String loginstud(@PathParam("studentid") String id, @PathParam("dob") String date, @PathParam("password") String passwd) throws SQLException, IOException {
        try {
            NewClass o = new NewClass();
            o.getConnection();
            String sql = "select *from PERSONS where username='"+id+"'and DOB=TO_DATE('"+date+"','YYYY/MM/DD ') and PASSWORD='"+passwd+"' and  PERSON_ROLE='Student'";
            ResultSet rs = o.rss(sql);
            if (rs.next()) {
                status = "ok";
                verma.accumulate("Status", status);
                verma.accumulate("message", "welcome");

            } else {
                status = "Wrong";
                verma.accumulate("Status", status);
                verma.accumulate("error", "wrong");
            }
        } catch (SQLException e) {
            status = "error";
            verma.accumulate("Status", status);
            verma.accumulate("error", e.getLocalizedMessage());
        }
        return verma.toString();
    }
@Path("Signup&{F_name}&{L_name}&{username}&{address}&{email}&{contact}&{password}")
    @GET
    @Produces("application/json")
    public String Signup(@PathParam("F_name") String fnme,@PathParam("L_name") String lnme,@PathParam("username") String usernme, @PathParam("address") String adress, @PathParam("email") String eml, @PathParam("contact") String con, @PathParam("password") String pass) throws SQLException, IOException {
        try {
            NewClass o = new NewClass();
            o.getConnection();
            String insert = "INSERT INTO FINALP1.PERSONS (ID, PERSON_ROLE, F_NAME, L_NAME, PHONE_NO, EMAIL, ADDRESS, USERNAME, PASSWORD) VALUES (seq_person.nextval, 'Employer', '"+fnme+"', '"+lnme+"', '"+con+"', '"+eml+"', '"+adress+"','"+usernme+"','"+pass+"')"; 
            ResultSet rs2=o.rss(insert);
            rs2.close();
            String sql = "select *from PERSONS where username='"+usernme+"'and PASSWORD='"+pass+"' and  PERSON_ROLE='Employer'";
            ResultSet rs = o.rss(sql);
            if (rs.next()) {
                status = "ok";
                verma.accumulate("Status", status);
                verma.accumulate("message", "succesfully signed up");

            } else {
                status = "Wrong";
                verma.accumulate("Status", status);
                verma.accumulate("error", "wrong");
            }
        } catch (SQLException e) {
            status = "error";
            verma.accumulate("Status", status);
            verma.accumulate("error", e.getLocalizedMessage());
        }
        return verma.toString();
    }
    
    @Path("loginemployer&{username}&{password}")
    @GET
    @Produces("application/json")
    public String loginemp(@PathParam("username") String uname, @PathParam("password") String passw) throws SQLException, IOException {
        try {
            NewClass o = new NewClass();
            o.getConnection();
            String sql = "select *from PERSONS where username='"+uname+"'and password='"+passw+"' and  PERSON_ROLE='Employer'";
            ResultSet rs = o.rss(sql);
            if (rs.next()) {
                status = "ok";
                verma.accumulate("Status", status);
                verma.accumulate("message", "welcome as employer");

            } else {
                status = "Wrong";
                verma.accumulate("Status", status);
                verma.accumulate("error", "wrong");
            }
        } catch (SQLException e) {
            status = "error";
            verma.accumulate("Status", status);
            verma.accumulate("error", e.getLocalizedMessage());
        }
        return verma.toString();
    } 
    
    @Path("locations&{name}")
    @GET
    @Produces("application/json")
    public String LOCATION(@PathParam("name") String nme) throws SQLException, IOException {
        try {
            NewClass o = new NewClass();
            o.getConnection();
            
            String sql = "SELECT * from LOCATION where LOCATION_NAME ='"+nme+"'";
            ResultSet rs = o.rss(sql);
            if (rs.next()) {
                status = "ok";
                verma.accumulate("Status", status);
                String snum=rs.getString("STREET_NUMBER");
                verma.accumulate("STREET_NUMBER", snum);
                String sname=rs.getString("STREET_NAME");
                verma.accumulate("STREET_NAME", sname);
                String ct=rs.getString("CITY");
                verma.accumulate("CITY", ct);
                String pcode=rs.getString("POSTALCODE");
                verma.accumulate("POSTALCODE", pcode);
                String pr=rs.getString("PROVINCE");
                verma.accumulate("PROVINCE", pr);
                String cn=rs.getString("COUNTRY");
                verma.accumulate("COUNTRY", cn);
                String d=rs.getString("ID");
                verma.accumulate("ID", d);
                
                

            } else {
                status = "Wrong";
                verma.accumulate("Status", status);
                verma.accumulate("error", "wrong");
            }
        } catch (SQLException e) {
            status = "error";
            verma.accumulate("Status", status);
            verma.accumulate("error", e.getLocalizedMessage());
        }
        return verma.toString();
    }
    
       @Path("forgetpassword&{email}")
    @GET
    @Produces("application/json")
    public String FORGETPWORD(@PathParam("email") String em) throws SQLException, IOException {
        try {
            NewClass o = new NewClass();
            o.getConnection();
            String sql = "select *from PERSONS where email='"+em+"'";
            ResultSet rs = o.rss(sql);
            if (rs.next()) {
                status = "ok";
                verma.accumulate("Status", status);
                verma.accumulate("message", "successfully sent to your email");

            } else {
                status = "Wrong";
                verma.accumulate("Status", status);
                verma.accumulate("error", "wrong");
            }
        } catch (SQLException e) {
            status = "error";
            verma.accumulate("Status", status);
            verma.accumulate("error", e.getLocalizedMessage());
        }
        return verma.toString();
    }
}


