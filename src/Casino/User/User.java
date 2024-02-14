package Casino.User;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class User {
    protected int counter = 0;

    public static Map<String, User> usernameUser = new HashMap<>();

    private String username;

    protected byte[] password;
    protected byte[] salt;
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
        this.username = username;
        usernameUser.put(username,this);
        balance = 0;
        this.salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(this.salt);

        this.password = generateHash(password,this.salt);

    }

    public String getUsername(){
        return username;
    }

    public double getBalance(){
        return balance;
    }

    protected byte[] getPassword(){
        return password;
    }

    public void casinoDeposit(double n){
        balance += n;
    }

    public void withdraw(double n){
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
    public void deposit(double n, User user){
        //check if the account is logged in
        if(!user.status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        balance += n;
    }
    public void deposit(double n){
        //check if the account is logged in
        if(!status){
            System.out.println("Please authenticate first in your account.");
            return;
        }
        balance += n;
    }

    public void transfer(double n, String username){
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

    protected byte[] generateHash(String password,byte[] salt){
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
