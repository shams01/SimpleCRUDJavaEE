package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public UserController() {
        super();
        dao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userid = Integer.parseInt(request.getParameter("userid"));
            dao.deleteUser(userid);
            forward = "/listUser.jsp";
            request.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")){
            forward = "/user.jsp";
            int userid = Integer.parseInt(request.getParameter("userid"));
            User user = dao.getUserById(userid);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = "/listUser.jsp";
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = "/user.jsp";
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        String id = request.getParameter("userid");
        int userid = Integer.parseInt(id);
        user.setUserid(userid);
        dao.addUser(user);
//        if(userid == null || userid.isEmpty())
//        {
//            dao.addUser(user);
//        }
//        else
//        {
//            user.setUserid(Integer.parseInt(userid));
//            dao.updateUser(user);
//        }
        RequestDispatcher view = request.getRequestDispatcher("/listUser.jsp");
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
}
