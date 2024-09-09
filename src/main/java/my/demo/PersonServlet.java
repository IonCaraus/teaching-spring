package my.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonServlet extends HttpServlet {
    private List<Person> personList = new ArrayList<>();

    public void init() {
        // Pre-populate with some data
        personList.add(new Person("John", "Doe", "12345", "john.doe@example.com"));
        personList.add(new Person("Jane", "Doe", "67890", "jane.doe@example.com"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the list of persons as a request attribute
        request.setAttribute("persons", personList);
        response.setContentType("text/html");
        // Forward to JSP page for displaying the list
        RequestDispatcher dispatcher = request.getRequestDispatcher("personList.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String personNumber = request.getParameter("personNumber");
        String email = request.getParameter("email");

        // Create a new person and add it to the list
        Person newPerson = new Person(firstName, lastName, personNumber, email);
        personList.add(newPerson);

        // Redirect to the list page
        response.sendRedirect("persons");
    }
}
