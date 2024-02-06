package Casino.User;
import java.util.Arrays;
import java.util.Base64;

public class Authenticator {
    //saves the logged user
    public static User loggedUser = null;

    public static void authenticate(String username, String password){
        //if someone is logged then you can't log in
        if(loggedUser != null){
            System.out.println("You're already logged in.");
            return;
        }
            //will see if the username given exists
            if(User.usernameUser.containsKey(username)){
                //if the username exists then it will check for password
                User actual = User.usernameUser.get(username);
                if(Arrays.equals(actual.generateHash(password, actual.salt), actual.getPassword())){
                    loggedUser = actual;
                    actual.status = true;
                    return;
                }
                System.out.println("Incorrect password.");
                return;
            }

        System.out.println("Username doesn't exist.");
    }

    public static void logout(){
        try {
            loggedUser.status = false;
            loggedUser = null;
            System.out.println("Logged out.");
        } catch (NullPointerException e){
            System.out.println("No user is logged in.");
        }
    }
}
