package servlets;

import ua.khpi.yesipov.project.persistence.MySqlDAOFactory;
import ua.khpi.yesipov.project.persistence.dao.PersonDAO;
import ua.khpi.yesipov.project.persistence.dao.RoleDAO;
import ua.khpi.yesipov.project.persistence.domain.Person;
import ua.khpi.yesipov.project.persistence.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends HttpServlet {

    private MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
    private RoleDAO roleDAO = mySqlDAOFactory.getRoleDAO();
    private PersonDAO personDAO = mySqlDAOFactory.getPersonDAO();
    private final List<Role> roles = roleDAO.selectRoles();
    private List<Person> personList = new ArrayList<Person>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Starting SignUp");
        HttpSession session = req.getSession();

        Person person = new Person();
        person.setId(personDAO.selectCount() + 1);
        person.setRole(roles.get(1));
        person.setLogin(req.getParameter("login"));
        person.setPassword(req.getParameter("password"));
        person.setFirstName(req.getParameter("firstName"));
        person.setMiddleName(req.getParameter("middleName"));
        person.setLastName(req.getParameter("lastName"));
        person.setBirthday(Date.valueOf(req.getParameter("birthday")));
        person.setIsBlocked(0);

        Person copy = personDAO.findPerson(req.getParameter("login"),
                req.getParameter("password"));
        if (copy.getFirstName() != null) {
            System.out.println("-------------------error---------------------------");
            resp.sendRedirect("pages/signUpError.jsp");
        } else {
            personDAO.insertPerson(person);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }
}
