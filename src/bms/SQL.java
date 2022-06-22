package bms;
import java.sql.*;

public class SQL {

    public static boolean signup(String name, String cnic, int dob, String father, int branchID){
        try{
            int user_id = 1;

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");


            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("INSERT INTO table_name (user_id, name, cnic, dob, father_name, BRANCH_BRANCH_ID) VALUES (" + user_id + "," + name + "," + cnic+ "," + dob + "," +father + "," + branchID+ "," + ");");

//            while(rs.next())
//                System.out.println(rs.getInt(1));


            con.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
