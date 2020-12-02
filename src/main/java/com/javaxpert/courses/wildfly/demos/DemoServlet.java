package com.javaxpert.courses.wildfly.demos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@WebServlet(name = "Demo",loadOnStartup = 1,urlPatterns = "/demo/*")
public class DemoServlet  extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Servlet demos is ready ");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Clustering session demo<h1>");
//       if(req.getSession()==null){
//           HttpSession session = req.getSession(true);
//           session.setAttribute(Constants.COUNTER_ATTRIBUTE_NAME,new AtomicInteger(0));
//           writer.println("<p>Created new session, no existing one found...<p>");
//       }
//       else{
//           final HttpSession session = req.getSession();
//           Stream<String> stream = Collections.list(session.getAttributeNames()).stream();
//           Optional<String> attribute =  stream.filter(name -> name.equals(Constants.COUNTER_ATTRIBUTE_NAME)).findFirst();
//           attribute.ifPresent(name ->{
//               AtomicInteger value = (AtomicInteger)session.getAttribute(Constants.COUNTER_ATTRIBUTE_NAME);
//               int current = value.incrementAndGet();
//               session.setAttribute(Constants.COUNTER_ATTRIBUTE_NAME,new AtomicInteger(current));
//               writer.println("<p>Ok,  fetched a counter from session, now current value is :"+ current+ "</p");
//           } );
//           //session.setAttribute();attribute.orElse(Constants.COUNTER_ATTRIBUTE_NAME);
//
//
//       }
        HttpSession session = req.getSession();
        //is the session a new one ?
        Object session_contents = session.getAttribute(Constants.COUNTER_ATTRIBUTE_NAME);
        if(session_contents == null){
            session.setAttribute(Constants.COUNTER_ATTRIBUTE_NAME,new AtomicInteger(0));
        }
        AtomicInteger value = (AtomicInteger)session.getAttribute(Constants.COUNTER_ATTRIBUTE_NAME);
        int current = value.incrementAndGet();
        session.setAttribute(Constants.COUNTER_ATTRIBUTE_NAME,new AtomicInteger(current));
        writer.println("<p>Ok,  fetched a counter from session, now current value is :"+ current+ "</p");
       writer.close();
    }
}
