package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author porte
 */
public class ShoppingListServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        
        //        log out
        String action = (String)request.getParameter("action");
        if (action != null && action.equals("logout")) {
            
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
//        if already logged in
        if (session.getAttribute("username") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String action = (String)request.getParameter("action");
        
        HttpSession session = request.getSession();
        
//        register a user
        if (action.equals("register")) {
            
            String username = request.getParameter("username");
            
            // if wrong input
            if (username.equals("") || username == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else {
                
                session.setAttribute("username", username);
                session.setAttribute("itemList", new ArrayList<String>());
            
                
                
            }
        } else if (action.equals("add")) {
            
            ArrayList<String> newList = (ArrayList<String>)session.getAttribute("itemList");
            String addItem = (String)request.getParameter("addItem");
            
            if (addItem != null && !addItem.equals("")) {
                newList.add(addItem);
            }
            
            session.setAttribute("itemList", newList);
            
        } else if (action.equals("delete")) {
            
            ArrayList<String> newList = (ArrayList<String>)session.getAttribute("itemList");
            String deleteItem = (String)request.getParameter("deleteItem");
            
            newList.remove(deleteItem);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        
    }

}