package bms;

public class UserDetails {
    static int userID = 0;
    static String userName = "";
    static float balance = 0;
    static String cnic = "";
    static int accountNumber = 0;
    static String accountType = "";


    public static String getUserName () {
        return userName;
    }
    public static void setUserName(String userName) {
        UserDetails.userName = userName;
    }

    public static float getBalance() {
        return balance;
    }

    public static void setBalance(float balance) {
        UserDetails.balance = balance;
    }

    public static String getCnic() {
        return cnic;
    }

    public static void setCnic(String cnic) {
        UserDetails.cnic = cnic;
    }

    public static int getAccountNumber() {
        return accountNumber;
    }

    public static void setAccountNumber(int accountNumber) {
        UserDetails.accountNumber = accountNumber;
    }

    public static String getAccountType() {
        return accountType;
    }

    public static void setAccountType(String accountType) {
        UserDetails.accountType = accountType;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        UserDetails.userID = userID;
    }
}
