package com.michal;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryControllerDAOImpl implements QueryControllerDAO {
    QueryView View;

    @Override
    public Properties readProperties() {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        try {
            BufferedReader bf = Files.newBufferedReader(myPath,
                    StandardCharsets.UTF_8);

            props.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(QueryControllerDAOImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return props;
    }

    @Override
    public void retrieveData(String statement) {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(statement);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
    public void query1() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT first_name, last_name FROM mentors;";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count =1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Mentor");

                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(1)+" "+rs.getString( 2));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void query2() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT nick_name FROM mentors WHERE city='Miskolc';";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count = 1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Mentor");


                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(1));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void query3() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT CONCAT(first_name,' ', last_name) AS full_name, phone_number FROM applicants WHERE first_name='Carol';";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count = 1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Applicant");

                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(1));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void query4() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, phone_number FROM applicants WHERE email LIKE '%@adipiscingenimmi.edu%';";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count = 1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Applicant");

                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(1)+ ", "+ rs.getString(2));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void Batchupdates(){
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");


        try (Connection con = DriverManager.getConnection(url, user, password)) {

            try (Statement st = con.createStatement()) {

                con.setAutoCommit(false);

                st.addBatch("DELETE FROM applicants WHERE application_code='54823';");
                st.addBatch("INSERT INTO applicants values(DEFAULT, 'Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823);");
                st.addBatch("UPDATE applicants SET phone_number ='003670/223-7459' WHERE id=7;");
                st.addBatch("DELETE FROM applicants WHERE email LIKE '%@mauriseu.net';");


                int counts[] = st.executeBatch();

                con.commit();

                System.out.println("Committed " + counts.length + " updates");

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(
                                QueryControllerDAOImpl.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(
                        QueryControllerDAOImpl.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void query5() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT * FROM applicants WHERE application_code='54823';";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count =1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Applicant");

                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(2)+" "+rs.getString( 3));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void query6() {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        String query = "SELECT CONCAT(first_name,' ', last_name) AS full_name, phone_number FROM applicants WHERE phone_number='003670/223-7459';";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            boolean isResult = pst.execute();
            int count = 1;

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    View.printColumnName("Applicant");

                    while (rs.next()) {

                        System.out.print(count);
                        System.out.print(": ");
                        System.out.println(rs.getString(1)+ ", "+ rs.getString(2));
                        count++;

                    }

                    isResult = pst.getMoreResults();
                }
            } while (isResult);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    QueryControllerDAOImpl.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}