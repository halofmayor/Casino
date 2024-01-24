package Casino;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private static List<User> users = new ArrayList<User>();
    private static List<String> usernames = new ArrayList<String>();
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UUID userID;
    private double balance;

    boolean status = false;

    public User(){
    }

    public User(String firstName, String lastName, String username, String password){
        for(int i = 0; i < usernames.size(); i++){
            //this for loop checks in the list of usernames if there is already the username given
            if(username.equals(usernames.get(i))){
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
        userID = UUID.randomUUID();
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

    public List<User> getUsers(){
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
        for(int i = 0; i < users.size(); i++){
            User actual = users.get(i);
            if(username.equals(actual.getUsername())){
                actual.deposit(n,this);
                withdraw(n);
                return;
            }
        }
        System.out.println("username not found");
    }
}
