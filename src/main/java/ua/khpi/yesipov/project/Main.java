package ua.khpi.yesipov.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Which car do you want to rent?");
        BusinessLogic businessLogic = new BusinessLogic();
        businessLogic.getCarTable();
        Scanner scanner = new Scanner(System.in);
        businessLogic.makeOrder(scanner);
        businessLogic.showOrders();
    }
}
