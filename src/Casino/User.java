package Casino;

import java.util.ArrayList;
import java.util.List;


public class User {
    private static List<User> users = new ArrayList<>();
    private static List<String> usernames = new ArrayList<>();
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private double balance;

    boolean status = false;

    public User(){
    }

    public User(String firstName, String lastName, String username, String password){
        for(String user : usernames){
            //this for loop checks in the list of usernames if there is already the username given
            if(username.equals(user)){
                System.out.println("Username already exists.");
                return;
            }
        }
        //if not then it will give values to the User
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        balance = 0;
        //add the usernames to the usernames list and the user to the users list
        usernames.add(username);
        users.add(this);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public double getBalance(){
        return balance;
    }

    public static List<User> getUsers(){
        return users;
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
        for(User actual : users){
            if(username.equals(actual.getUsername())){
                actual.deposit(n,this);
                withdraw(n);
                return;
            }
        }
        System.out.println("username not found");
    }
}
