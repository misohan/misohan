package com.michal;

import java.util.Scanner;

public class QueryView extends QueryControllerDAOImpl {
    Scanner scanner = new Scanner(System.in);
    QueryControllerDAOImpl newQuery = new QueryControllerDAOImpl();

    public static void printColumnName(String columnName){
        int stringLength = columnName.length();
        String symbolLenght = "";

        for (int i = 0; i < stringLength ; i++) {
            symbolLenght += "=";

        }
        System.out.println(columnName);
        System.out.println(symbolLenght);
    }


    public void HRQueries(){

        boolean quit = false;
        startHRQueryApplication();
        printActions();
        while (!quit) {
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;

                case 1:
                    newQuery.query1();
                    break;

                case 2:
                    newQuery.query2();
                    break;

                case 3:
                    newQuery.query3();
                    break;

                case 4:
                    newQuery.query4();
                    break;

                case 5:
                    newQuery.query5();
                    break;
                case 6:
                    newQuery.query6();
                    break;

                case 7:
                    newQuery.query5();
                    break;


                case 8:
                    printActions();
                    break;
            }

        }

    }
    private static void printActions(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to shutdown\n" +
                "1  - Display first name and last name of mentors.\n" +
                "2  - Display nicknames of mentors from Miskolc.\n" +
                "3  - Display full name and phone number of applicant Carol\n" +
                "4  - Display full name and phone of applicant from Adipiscingenimmi university.\n" +
                "5  - Display new applicant.\n" +
                "6  - Display Jemina update.\n" +
                "7  - Display update after deleted applicants.\n" +
                "8  - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }
    private static void startHRQueryApplication(){
        System.out.println("Welcome in HR Query application");
    }

}
