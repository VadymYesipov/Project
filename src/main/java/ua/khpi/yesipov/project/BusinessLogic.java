package ua.khpi.yesipov.project;

import ua.khpi.yesipov.project.factories.MySqlDAOFactory;
import ua.khpi.yesipov.project.persistence.dao.CarDAO;
import ua.khpi.yesipov.project.persistence.dao.OrderDAO;
import ua.khpi.yesipov.project.persistence.dao.PersonDAO;
import ua.khpi.yesipov.project.persistence.dao.RoleDAO;
import ua.khpi.yesipov.project.persistence.domain.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusinessLogic {

    private MySqlDAOFactory mySqlDAOFactory;

    private CarDAO carDAO;

    private RoleDAO roleDAO;

    private OrderDAO orderDAO;

    private PersonDAO personDAO;

    private static Car[] cars;
    private static Car[] orderedCars;

    private static Order[] orders;

    private static Role[] roles;

    public BusinessLogic() {
        cars = new Car[14];
        orderedCars = new Car[10];
        orders = new Order[10];
        roles = new Role[10];
        carDAO = mySqlDAOFactory.getCarDAO();
        roleDAO = mySqlDAOFactory.getRoleDAO();
        orderDAO = mySqlDAOFactory.getOrderDAO();
        personDAO = mySqlDAOFactory.getPersonDAO();
        getAllCars();
        getAllRoles();
    }

    private void getAllRoles() {
        ResultSet resultSet = roleDAO.selectRoles();
        try {
            for (int i = 0; resultSet.next(); i++) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
                roles[i] = role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllCars() {
        ResultSet resultSet = carDAO.selectAllCars();
        try {
            for (int i = 0; resultSet.next(); i++) {
                Car car = new Car();
                car.setId(resultSet.getInt(1));

                Brand brand = new Brand();
                brand.setBrand(resultSet.getString(2));
                car.setBrand(brand);

                car.setName(resultSet.getString(3));

                Quality quality = new Quality();
                quality.setQuality(resultSet.getString(4));
                car.setQuality(quality);

                car.setHours(resultSet.getInt(5));
                car.setPrice(resultSet.getDouble(6));
                if (resultSet.getInt(7) == 0) {
                    car.setIsOrdered(false);
                } else {
                    car.setIsOrdered(true);
                }
                cars[i] = car;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCarTable() throws SQLException {
        ResultSet resultSet = carDAO.selectCars();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ": " +
            resultSet.getString(2) + " " + resultSet.getString(3) + ", quality " +
            resultSet.getString(4) + ", price: " + resultSet.getDouble(6) + "$");
        }
    }

    private static int id;

    public void makeOrder(Scanner scanner) {
        OrderDAO orderDAO = mySqlDAOFactory.getOrderDAO();
        Order order = new Order();

        order.setId(++id);

        int i = scanner.nextInt();
        orderedCars[i] = cars[i];
        order.setCar(orderedCars[i]);

        Person person = new Person();
        person.setId(++id);
        person.setBirthday(new Date(System.currentTimeMillis()));
        person.setRole(roles[1]);
        System.out.println("What's your first name?");
        person.setFirstName(scanner.next());
        System.out.println("What's your middle name?");
        person.setMiddleName(scanner.next());
        System.out.println("What's your last name?");
        person.setLastName(scanner.next());
        System.out.println("What's your login?");
        person.setLogin(scanner.next());
        System.out.println("What's your password?");
        person.setPassword(scanner.next());
        personDAO.insertPerson(person);
        order.setPerson(person);

        long since = System.currentTimeMillis();
        order.setSince(new Date(since));
        System.out.println("How long do you want to rent the car? (hours)");
        long till = since + 3600000 * scanner.nextInt();
        order.setTill(new Date(till));

        double price = ((till - since) / 3600000) * orderedCars[i].getPrice();
        order.setPrice(price);

        Driver driver = new Driver();
        driver.setId(1);
        order.setDriver(driver);

        orderDAO.insertOrder(order);
    }

    public void showOrders() throws SQLException {
        ResultSet resultSet = orderDAO.selectOrders();
        System.out.println("The orders:");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ": " +
            resultSet.getString(2) + ", user: " + resultSet.getString(3) + ", " + resultSet.getString(4) + ", " + resultSet.getString(5) + ", " +
            resultSet.getDate(6) + ", " + resultSet.getString(7) + ", " + resultSet.getString(8) + "; since " +
            resultSet.getDate(9) + " till " + resultSet.getDate(10) + "; price: " + resultSet.getDouble(11));
        }
    }
}
