<%@page import="edu.jsu.mcis.lab6.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DAOFactory daoFactory = null;
    ServletContext context = request.getServletContext();
    if (context.getAttribute("daoFactory") == null) {
        System.err.println("*** Creating new DAOFactory ...");
        daoFactory = new DAOFactory();
        context.setAttribute("daoFactory", daoFactory);
    }
    else {
        daoFactory = (DAOFactory) context.getAttribute("daoFactory");
    }
    
    TrainingSessionsDAO sessionDAO = daoFactory.getTrainingSessionsDAO();
        
%>
<!DOCTYPE html>
<html>
    
<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    
    <div>
        <form action="registrations" method="POST">
            <fieldset>
                <legend>Register For a Session</legend>

                <label for=firstname> First Name </label>
                <input type="text" id="firstname" name="firstname"><!-- comment -->

                <label for=lastname>Last Name</label>
                <input type="text" id="lastname" name="lastname"><!-- comment -->

                <label for=displayname>Display Name</label>
                <input type="text" id="displayname" name="displayname"><!-- comment -->

                        <p>Session ID 
                            <%= sessionDAO.getSessionListAsHTML() %>
                        </p>

                <input type="submit" value="submit">
            </fieldset>
        </form>



        



    </div>
    <div>
    <a href="index.html">Home</a>
    </div>
</body>

</html>