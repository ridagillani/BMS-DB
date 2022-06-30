package bms;
import java.sql.*;

public class SQL {
    public static boolean branch_login (String username, String password) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from \"HR\".\"BRANCH\" where name='"+ username +"'");

            rs.next();

            String usernameDB = rs.getString(2);
            String pass = rs.getString(4);
            con.close();

            if (usernameDB.equals(username) && password.equals(pass)) {
                return true;
            }
            return false;

        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean user_login(String user, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet user_data = stmt.executeQuery("select * from \"HR\".\"USER\" where name = '" + user + "'");


            boolean next = user_data.next();
            if (next) {

                String pass = user_data.getString(6);
                int user_id = user_data.getInt(1);
                String userName = user_data.getString(2);
                String cnic = user_data.getString(3);

                if (password.equals(pass)) {
                    UserDetails.setUserID(user_id);
                    UserDetails.setUserName(userName);
                    UserDetails.setCnic(cnic);


                    ResultSet acc_data = stmt.executeQuery("select * from \"HR\".\"ACCOUNT\" where user_id = '" + user_id + "'");

                    boolean nextA = acc_data.next();
                    if (nextA) {
                        int acc_id = acc_data.getInt(1);
                        float balance = acc_data.getFloat(2);
                        String type = acc_data.getString(3);

                        UserDetails.setAccountType(type);
                        UserDetails.setAccountNumber(acc_id);
                        UserDetails.setBalance(balance);
                        con.close();
                    }

                    return true;
                }

                return false;
            }

            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean signup(String name, String cnic, String dob, String father, String password, String acc_type){
        try{
            int user_id = 1;
            int acc_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            //user add
            Statement stmt=con.createStatement();
            ResultSet userID = stmt.executeQuery("select * from \"HR\".\"USER\" where id = (select max(id) from \"HR\".\"USER\")");

            boolean next = userID.next();
            if (next) {
                user_id = userID.getInt(1) + 1;
            }

            ResultSet user_add = stmt.executeQuery("INSERT INTO \"HR\".\"USER\" (id, name, cnic, dob, \"father's_name\", password) VALUES ('" + user_id + "', '" + name + "', '" + cnic+ "', '" + dob + "', '" + father + "', '" + password + "')");
            System.out.println("user added");

            //account add
            ResultSet accID = stmt.executeQuery("select * from \"HR\".\"ACCOUNT\" where id = (select max(id) from \"HR\".\"ACCOUNT\")");

            boolean nexA = accID.next();
            if (nexA) {
                acc_id = accID.getInt(1) + 1;
            }

            ResultSet acc_add = stmt.executeQuery("INSERT INTO \"HR\".\"ACCOUNT\" (id, balance, type, \"number\", user_id) VALUES ('" + acc_id + "', '" + 0 + "', '" + acc_type + "', '" + acc_id + "', '" + user_id + "')");
            System.out.println("account added");

            con.close();

            UserDetails.setUserID(user_id);
            UserDetails.setUserName(name);
            UserDetails.setAccountNumber(acc_id);
            UserDetails.setBalance(0);
            UserDetails.setCnic(cnic);
            UserDetails.setAccountType(acc_type);

            return true;
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addEmployee (String name, String password, String cnic, String salary) {
        try {
            int employee_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet employeeID = stmt.executeQuery("select * from \"HR\".\"EMPLOYEE\" where id = (select max(id) from \"HR\".\"EMPLOYEE\")");

            boolean next = employeeID.next();
            if (next) {
                employee_id = employeeID.getInt(1) + 1;
            }

            ResultSet employee_add = stmt.executeQuery("INSERT INTO \"HR\".\"EMPLOYEE\" (id, name, cnic, salary, branch_id, password) VALUES ('" + employee_id + "', '" + name + "', '" + cnic+ "', '" + salary + "', '" + 1 + "', '" + password + "')");
            System.out.println("Employee added");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static String[][] getEmployeeList () {
        String[][] data = {};
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet rowsRs = stmt.executeQuery("select count(*) from \"HR\".\"EMPLOYEE\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);
            data = new String[rows][4];

            ResultSet employees = stmt.executeQuery("select * from \"HR\".\"EMPLOYEE\"");

            int count = 0;

            while (employees.next()) {
                String[] employee = new String[4];
                int employee_id = employees.getInt(1);
                String name = employees.getString(2);
                String cnic = employees.getString(3);
                String salary = employees.getString(4);
                int branch_id = employees.getInt(5);

                ResultSet employeeD = stmt.executeQuery("select * from \"HR\".\"BRANCH\" where id = '" + branch_id + "'");
                employeeD.next();
                String branch = employeeD.getString(2);

                employee[0] = Integer.toString(employee_id);
                employee[1] = name;
                employee[2] = salary;
                employee[3] = branch;

                data[count] = employee;
                count++;
            }

            con.close();

            return data;
        } catch (Exception e) {
            System.out.println(e);
            return data;
        }
    }


}
