package Casino;


import java.util.HashMap;
import java.util.Map;

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
                if(actual.getPassword().equals(password)){
                    //the loggedUser will be the Actual User that was being used to check the credentials
                    loggedUser = actual;
                    //and the status of the user will be true, so we can do operations
                    loggedUser.status = true;
                    return;
                }
                System.out.println("Incorrect password.");
                return;
            }

        System.out.println("Username doesn't exist.");
    }

    public static void logout(){
        loggedUser.status = false;
        loggedUser = null;
        System.out.println("Logged out.");
    }
}
