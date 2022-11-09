package edu.jsu.mcis.lab6;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.*;
import edu.jsu.mcis.lab6.dao.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.find(sessionid, attendeeid));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.create(sessionid, attendeeid));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        // get parameters from the request
        BufferedReader br = null;
        response.setContentType("application/json;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            HashMap<String, String> parameters = new HashMap<>();
            String[] pairs = p.trim().split("&");

            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }

            System.err.println(parameters);
            // get id parameter from request
            int attendeeid_old = Integer.parseInt(parameters.get("attendeeid_old"));
            int sessionid_old = Integer.parseInt(parameters.get("sessionid_old"));
            int attendeeid_updated = Integer.parseInt(parameters.get("attendeeid_updated"));
            int sessionid_updated = Integer.parseInt(parameters.get("sessionid_updated"));
            // rest of servlet code goes here

            DAOFactory daoFactory = null;

            ServletContext context = request.getServletContext();

            if (context.getAttribute("daoFactory") == null) {
                System.err.println("*** Creating new DAOFactory ...");
                daoFactory = new DAOFactory();
                context.setAttribute("daoFactory", daoFactory);
            } else {
                daoFactory = (DAOFactory) context.getAttribute("daoFactory");
            }

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.update(sessionid_old, attendeeid_old, sessionid_updated, attendeeid_updated));

        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        // get parameters from the request
        BufferedReader br = null;
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            HashMap<String, String> parameters = new HashMap<>();
            String[] pairs = p.trim().split("&");

            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }

            // get id parameter from request
            int attendeeid = Integer.parseInt(parameters.get("attendeeid"));
            int sessionid = Integer.parseInt(parameters.get("sessionid"));
            // rest of servlet code goes here

            DAOFactory daoFactory = null;

            ServletContext context = request.getServletContext();

            if (context.getAttribute("daoFactory") == null) {
                System.err.println("*** Creating new DAOFactory ...");
                daoFactory = new DAOFactory();
                context.setAttribute("daoFactory", daoFactory);
            } else {
                daoFactory = (DAOFactory) context.getAttribute("daoFactory");
            }

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.delete(attendeeid, sessionid));

        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Registration Servlet";
    }

}