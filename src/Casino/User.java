package Casino;


import java.util.HashMap;
import java.util.Map;


public class User {

    public static Map<String, User> usernameUser = new HashMap<>();

    private String password;

    private String firstName;
    private String lastName;
    private double balance;

    boolean status = false;


    public User(String firstName, String lastName, String username, String password){
        if(usernameUser.containsKey(username)){
            System.out.println("This username already exists.");
        }
        //if not then it will give values to the User
        this.firstName = firstName;
        this.lastName = lastName;
        usernameUser.put(username,this);
        balance = 0;
        this.password = password;
    }

    public double getBalance(){
        return balance;
    }

    public String getPassword(){
        return password;
    }


    public void withdraw(int n){
        //check if the account is logged in
        if(!status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        if(balance - n < 0){
            System.out.println("Impossible to efectuate this operation. Insufficient balance.");
            return;
        }
        balance -= n;
    }
    public void deposit(int n, User user){
        //check if the account is logged in
        if(!user.status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        balance += n;
    }
    public void deposit(int n){
        //check if the account is logged in
        if(!status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        balance += n;
    }

    public void transfer(int n, String username){
        //check if the account is logged in
        if(!status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        //this for loop will search for the username in the usernames list and will find the respective user with that username
            if(usernameUser.containsKey(username)){
                usernameUser.get(username).deposit(n,this);
                withdraw(n);
                return;
            }
        System.out.println("username not found");
    }
}
