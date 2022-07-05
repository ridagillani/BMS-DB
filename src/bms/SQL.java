package bms;


import oracle.sql.TIMESTAMP;
import java.sql.*;

public class SQL {
    public static boolean employee_login (String username, String password) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from \"HR\".\"EMPLOYEE\" where name='"+ username +"'");

            rs.next();

            String usernameDB = rs.getString(2);
            String pass = rs.getString(6);
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
                        UserDetails.setPassword(password);
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

            ResultSet acc_add = stmt.executeQuery("INSERT INTO \"HR\".\"ACCOUNT\" (id, balance, type, \"number\", user_id) VALUES ('" + acc_id + "', '" + 10000 + "', '" + acc_type + "', '" + acc_id + "', '" + user_id + "')");
            System.out.println("account added");

            con.close();

            UserDetails.setUserID(user_id);
            UserDetails.setUserName(name);
            UserDetails.setAccountNumber(acc_id);
            UserDetails.setBalance(0);
            UserDetails.setCnic(cnic);
            UserDetails.setAccountType(acc_type);
            UserDetails.setPassword(password);

            return true;
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateEmployee (int id, String name, String password, String cnic, String salary) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();

            ResultSet employee_update = stmt.executeQuery("UPDATE \"HR\".\"EMPLOYEE\" SET name = '" + name + "', cnic = '" + cnic + "', salary = '" + salary + "', password = '" + password + "' WHERE id = " + id);
            System.out.println("Employee updated");


            con.close();

            return true;
        } catch (Exception e) {
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

            Statement stmt1=con.createStatement();
            ResultSet rowsRs = stmt1.executeQuery("select count(*) from \"HR\".\"EMPLOYEE\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);

            stmt1.close();

            data = new String[rows][6];

            Statement stmt2=con.createStatement();
            ResultSet employees = stmt2.executeQuery("select * from \"HR\".\"EMPLOYEE\"");

            int count = 0;

            while (employees.next()) {
                String[] employee = new String[6];
                int employee_id = employees.getInt(1);
                String name = employees.getString(2);
                String cnic = employees.getString(3);
                String salary = employees.getString(4);
                int branch_id = employees.getInt(5);
                String password = employees.getString(6);

                Statement stmt3=con.createStatement();
                ResultSet employeeD = stmt3.executeQuery("select * from \"HR\".\"BRANCH\" where id = '" + branch_id + "'");
                employeeD.next();
                String branch = employeeD.getString(2);

                employee[0] = Integer.toString(employee_id);
                employee[1] = name;
                employee[2] = password;
                employee[3] = cnic;
                employee[4] = salary;
                employee[5] = branch;

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

    public static boolean removeEmployee (int id) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet delete = stmt.executeQuery("DELETE FROM \"HR\".\"EMPLOYEE\" WHERE id = " + id);

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public static String[][] getUserList () {
        String[][] data = {};
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt1=con.createStatement();
            ResultSet rowsRs = stmt1.executeQuery("select count(*) from \"HR\".\"USER\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);

            stmt1.close();

            data = new String[rows][6];

            Statement stmt2=con.createStatement();
            ResultSet users = stmt2.executeQuery("select * from \"HR\".\"USER\"");

            int count = 0;

            while (users.next()) {
                String[] user = new String[6];
                int user_id = users.getInt(1);
                String name = users.getString(2);
                String cnic = users.getString(3);
                String dob = users.getString(4);
                String father_name = users.getString(5);
                String password = users.getString(6);

                Statement stmt3=con.createStatement();
                ResultSet userAccount = stmt3.executeQuery("select * from \"HR\".\"ACCOUNT\" where user_id = '" + user_id + "'");
                userAccount.next();
                String acc_type = userAccount.getString(3);

                user[0] = Integer.toString(user_id);
                user[1] = name;
                user[2] = father_name;
                user[3] = dob;
                user[4] = cnic;
                user[5] = acc_type;

                data[count] = user;
                count++;
            }

            con.close();


            return data;
        } catch (Exception e) {
            System.out.println(e);
            return data;
        }
    }

    public static boolean removeUser (int id) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet delete = stmt.executeQuery("DELETE FROM \"HR\".\"USER\" WHERE id = " + id);

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateUser (int id, String name, String fatherName, String cnic, String dob, String accountType) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();

            ResultSet user_update = stmt.executeQuery("UPDATE \"HR\".\"USER\" SET name = '" + name + "', cnic = '" + cnic + "', \"father's_name\" = '" + fatherName + "', dob = '" + dob + "' WHERE id = " + id);
            System.out.println("User updated");

            ResultSet account_update = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET type = '" + accountType + "' WHERE user_id = " + id);
            System.out.println("Account updated");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeUserPass (int id, String oldPass, String newPass) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet user_data = stmt.executeQuery("select * from \"HR\".\"USER\" where id = '" + id + "'");


            boolean next = user_data.next();
            if (next) {

                String pass = user_data.getString(6);

                Statement stmt2 = con.createStatement();

                if (pass.equals(oldPass)) {

                    ResultSet user_update = stmt2.executeQuery("UPDATE \"HR\".\"USER\" SET password = '" + newPass + "' WHERE id = " + id);
                    System.out.println("User updated");
                    return true;
                }
            }

            con.close();

            return false;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addBeneficiary (int user_id, String name, int account_number) {
        try {
            int beneficiary_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet beneficiaryID = stmt.executeQuery("select * from \"HR\".\"BENEFICIARIES\" where id = (select max(id) from \"HR\".\"BENEFICIARIES\")");

            boolean next = beneficiaryID.next();
            if (next) {
                beneficiary_id = beneficiaryID.getInt(1) + 1;
            }

            Statement stmt2 = con.createStatement();
            ResultSet check = stmt2.executeQuery("select * from \"HR\".\"USER\" where id = " + account_number);

            if (!check.next()) {
                return false;
            }

            ResultSet beneficiary_add = stmt.executeQuery("INSERT INTO \"HR\".\"BENEFICIARIES\" (id, name, account_no, account_id) VALUES ('" + beneficiary_id + "', '" + name + "', '" + account_number + "', '" + user_id + "')");
            System.out.println("Beneficiary added");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static String[][] getBeneficiaryList (int user_id) {
        String[][] data = {};
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt1=con.createStatement();
            ResultSet rowsRs = stmt1.executeQuery("select count(*) from \"HR\".\"BENEFICIARIES\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);

            stmt1.close();

            data = new String[rows][4];

            Statement stmt2=con.createStatement();
            ResultSet users = stmt2.executeQuery("select * from \"HR\".\"BENEFICIARIES\" where account_id = " + user_id);

            int count = 0;

            while (users.next()) {
                String[] beneficiary = new String[4];
                int beneficiary_id = users.getInt(1);
                String name = users.getString(2);
                String account_no = users.getString(3);
                String account_id = users.getString(4);



                beneficiary[0] = Integer.toString(beneficiary_id);
                beneficiary[1] = name;
                beneficiary[2] = account_no;
                beneficiary[3] = account_id;

                data[count] = beneficiary;
                count++;
            }

            con.close();


            return data;
        } catch (Exception e) {
            System.out.println(e);
            return data;
        }
    }

    public static boolean updateBeneficiary (int id, String name, int account_number) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();

            ResultSet user_update = stmt.executeQuery("UPDATE \"HR\".\"BENEFICIARIES\" SET name = '" + name + "', account_no = '" + account_number + "' WHERE id = " + id);
            System.out.println("Beneficiary updated");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeBeneficiary (int id) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet delete = stmt.executeQuery("DELETE FROM \"HR\".\"BENEFICIARIES\" WHERE id = " + id);

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean transfer(int account_number, int amount, int beneficiary_account) {
        try {
            int transaction_id = 1;
            String timestamp = Long.toString(System.currentTimeMillis());

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet transID = stmt.executeQuery("select * from \"HR\".\"TRANSACTIONS\" where id = (select max(id) from \"HR\".\"TRANSACTIONS\")");

            boolean next = transID.next();
            if (next) {
                transaction_id = transID.getInt(1) + 1;
            }

            ResultSet senderAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + account_number + ") - " + amount + " where id = " + account_number);
            ResultSet receiverAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + beneficiary_account + ") + " + amount + " where id = " + beneficiary_account);

            ResultSet transaction_add = stmt.executeQuery("INSERT INTO \"HR\".\"TRANSACTIONS\" (id, amount, timestamp, account_id, transactions_type) VALUES ('" + transaction_id + "', '" + amount + "', '" + timestamp + "', '" + account_number +  "', '" + "TRANSFER_MONEY" + "')");
            ResultSet tran_add = stmt.executeQuery("INSERT INTO \"HR\".\"TRANSFER_MONEY\" (id, receiver) VALUES ('" + transaction_id + "', '" + beneficiary_account + "')");

            System.out.println("transaction added");

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean billPayment(int account_number, int amount, String billOf) {
        try {
            int transaction_id = 1;
            String timestamp = Long.toString(System.currentTimeMillis());

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet transID = stmt.executeQuery("select * from \"HR\".\"TRANSACTIONS\" where id = (select max(id) from \"HR\".\"TRANSACTIONS\")");

            boolean next = transID.next();
            if (next) {
                transaction_id = transID.getInt(1) + 1;
            }

            int bill_number = 1;

            Statement stmt2=con.createStatement();
            ResultSet billNo = stmt2.executeQuery("select * from \"HR\".\"BILL_PAYMENT\" where bill_number = (select max(bill_number) from \"HR\".\"BILL_PAYMENT\")");

            if (billNo.next()) {
                bill_number = billNo.getInt(2) + 1;
            }

            ResultSet senderAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + account_number + ") - " + amount + " where id = " + account_number);

            ResultSet transaction_add = stmt.executeQuery("INSERT INTO \"HR\".\"TRANSACTIONS\" (id, amount, timestamp, account_id, transactions_type) VALUES ('" + transaction_id + "', '" + amount + "', '" + timestamp + "', '" + account_number +  "', '" + "BILL_PAYMENT" + "')");
            ResultSet tran_add = stmt.executeQuery("INSERT INTO \"HR\".\"BILL_PAYMENT\" (id, type, bill_number) VALUES ('" + transaction_id + "', '" + billOf + "', '" + bill_number + "')");

            System.out.println("transaction added");

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean mobile_load(int account_number, int amount, String network, int number) {
        try {
            int transaction_id = 1;
            String timestamp = Long.toString(System.currentTimeMillis());

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet transID = stmt.executeQuery("select * from \"HR\".\"TRANSACTIONS\" where id = (select max(id) from \"HR\".\"TRANSACTIONS\")");

            boolean next = transID.next();
            if (next) {
                transaction_id = transID.getInt(1) + 1;
            }


            ResultSet senderAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + account_number + ") - " + amount + " where id = " + account_number);

            ResultSet transaction_add = stmt.executeQuery("INSERT INTO \"HR\".\"TRANSACTIONS\" (id, amount, timestamp, account_id, transactions_type) VALUES ('" + transaction_id + "', '" + amount + "', '" + timestamp + "', '" + account_number +  "', '" + "MOBILE_LOAD" + "')");
            ResultSet load_add = stmt.executeQuery("INSERT INTO \"HR\".\"MOBILE_LOAD\" (id, \"number\", network) VALUES ('" + transaction_id + "', '" + number + "', '" + network + "')");

            System.out.println("transaction added");

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean zakaat(int account_number, int amount, String charity) {
        try {
            int transaction_id = 1;
            String timestamp = Long.toString(System.currentTimeMillis());

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet transID = stmt.executeQuery("select * from \"HR\".\"TRANSACTIONS\" where id = (select max(id) from \"HR\".\"TRANSACTIONS\")");

            boolean next = transID.next();
            if (next) {
                transaction_id = transID.getInt(1) + 1;
            }


            ResultSet senderAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + account_number + ") - " + amount + " where id = " + account_number);

            ResultSet transaction_add = stmt.executeQuery("INSERT INTO \"HR\".\"TRANSACTIONS\" (id, amount, timestamp, account_id, transactions_type) VALUES ('" + transaction_id + "', '" + amount + "', '" + timestamp + "', '" + account_number +  "', '" + "ZAKAAT" + "')");
            ResultSet load_add = stmt.executeQuery("INSERT INTO \"HR\".\"ZAKAAT\" (id, charity) VALUES ('" + transaction_id + "', '" + charity + "')");

            System.out.println("transaction added");

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    static void updateUserDetails () {
        try{
            int user_id = 1;
            int acc_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            //user add
            Statement stmt=con.createStatement();
            ResultSet account = stmt.executeQuery("select * from \"HR\".\"ACCOUNT\" where id = " + UserDetails.getAccountNumber());

            account.next();
            Float balance = account.getFloat(2);

            con.close();

            UserDetails.setBalance(balance);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static boolean applyLoan (int user_id, int amount) {
        try {
            int loan_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();
            ResultSet loanID = stmt.executeQuery("select * from \"HR\".\"LOAN\" where id = (select max(id) from \"HR\".\"LOAN\")");

            boolean next = loanID.next();
            if (next) {
                loan_id = loanID.getInt(1) + 1;
            }

            ResultSet loan_add = stmt.executeQuery("INSERT INTO \"HR\".\"LOAN\" (id, amount, approved) VALUES ('" + loan_id + "', '" + amount + "', '" + "NOT APPROVED" + "')");
            ResultSet loan_apply = stmt.executeQuery("INSERT INTO \"HR\".\"APPLY\" (user_id, loan_id) VALUES ('" + user_id + "', '" + loan_id + "')");
            System.out.println("Loan Applied");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public static String[][] getLoanList () {
        String[][] data = {};
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt1=con.createStatement();
            ResultSet rowsRs = stmt1.executeQuery("select count(*) from \"HR\".\"APPLY\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);

            stmt1.close();

            data = new String[rows][5];

            Statement stmt2=con.createStatement();
            ResultSet users = stmt2.executeQuery("SELECT hr.apply.user_id, hr.apply.loan_id, \"HR\".\"USER\".name, hr.loan.amount, hr.loan.approved FROM HR.APPLY JOIN \"HR\".\"USER\" ON \"HR\".\"USER\".id = hr.apply.user_id JOIN HR.LOAN ON hr.loan.id = hr.apply.loan_id");
            int count = 0;

            while (users.next()) {
                String[] loan = new String[5];
                int user_id = users.getInt(1);
                int loan_id = users.getInt(2);
                String name = users.getString(3);
                int amount = users.getInt(4);
                String approved = users.getString(5);


                loan[0] = Integer.toString(loan_id);
                loan[1] = name;
                loan[2] = Integer.toString(user_id);
                loan[3] = Integer.toString(amount);
                loan[4] = approved;

                data[count] = loan;
                count++;
            }

            con.close();


            return data;
        } catch (Exception e) {
            System.out.println(e);
            return data;
        }
    }

    public static boolean updateLoan (int loan_id, int account_number, String status, int amount) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt=con.createStatement();

            if (amount != 0) {
                ResultSet senderAm = stmt.executeQuery("UPDATE \"HR\".\"ACCOUNT\" SET balance = (select balance from \"HR\".\"ACCOUNT\" where id = " + account_number + ") + " + amount + " where id = " + account_number);
            }

            ResultSet user_update = stmt.executeQuery("UPDATE \"HR\".\"LOAN\" SET approved = '" + status + "' WHERE id = " + loan_id);
            System.out.println("Beneficiary updated");


            con.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static String[][] getTransactions (int user_id) {
        String[][] data = {};
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

            Statement stmt1=con.createStatement();
            ResultSet rowsRs = stmt1.executeQuery("select count(*) from \"HR\".\"TRANSACTIONS\"");
            rowsRs.next();

            int rows = rowsRs.getInt(1);

            stmt1.close();

            data = new String[rows][3];

            Statement stmt2=con.createStatement();
            ResultSet transactions = stmt2.executeQuery("select * from \"HR\".\"TRANSACTIONS\" where account_id = " + user_id);

            int count = 0;

            while (transactions.next()) {
                String[] transaction = new String[3];
                int tra_id = transactions.getInt(1);
                int amount = transactions.getInt(2);
                String timestamp = transactions.getString(3);
                String type = transactions.getString(5);

                String name = "";

                Statement stmt3 = con.createStatement();

                switch (type){
                    case "BILL_PAYMENT":
                        ResultSet tr1 = stmt3.executeQuery("select * from \"HR\".\"BILL_PAYMENT\" where id = " + tra_id);
                        tr1.next();
                        name = tr1.getString(3);
                        break;

                    case "MOBILE_LOAD":
                        ResultSet tr2 = stmt3.executeQuery("select * from \"HR\".\"MOBILE_LOAD\" where id = " + tra_id);
                        tr2.next();
                        name = tr2.getString(3);
                        break;

                    case "TRANSFER_MONEY":
                        ResultSet tr3 = stmt3.executeQuery("select name from \"HR\".\"USER\" where id = (select receiver from \"HR\".\"TRANSFER_MONEY\" where id = " + tra_id + ")");
                        tr3.next();
                        name = tr3.getString(1);
                        break;

                    case "ZAKAAT":
                        ResultSet tr4 = stmt3.executeQuery("select * from \"HR\".\"ZAKAAT\" where id = " + tra_id);
                        tr4.next();
                        name = tr4.getString(2);
                        break;

                }


                transaction[0] = name;
                transaction[1] = Integer.toString(amount);
                transaction[2] = timestamp;

                data[count] = transaction;
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
