package House;

public class Accounts {


    private int accountID ;
    private String name;
    private String address;
    private String phone;
    private String userName;
    private String passWord;


    public Accounts(String name, String address, String phone, String userName, String passWord) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
        this.passWord = passWord;
    }



    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    



    public Accounts(int accountID,String name, String address, String phone, String userName, String passWord ) {
        this.accountID = accountID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
        this.passWord = passWord;
    }
}
