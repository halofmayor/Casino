package Casino;


import java.util.ArrayList;
import java.util.List;

public class Authenticator {
    //saves the logged user
    public static User loggedUser = null;

    public static void authenticate(String username, String password){
        //if someone is logged then you can't log in
        if(loggedUser != null){
            System.out.println("You're already logged in.");
            return;
        }
        //instances a new user object to search in the Users list
        User user = new User();
        for(int i = 0; i < user.getUsers().size(); i++){
            //will see if the actual User have the given username
            User actual = user.getUsers().get(i);
            if(username.equals(actual.getUsername())){
                //if the username exists then it will check for password
                if(password.equals(actual.getPassword())){
                    //the loggedUser will be the Actual User that was being used to check the credentials
                    loggedUser = actual;
                    System.out.println(loggedUser.status);
                    //and the status of the user will be true, so we can do operations
                    loggedUser.status = true;
                    return;
                }
                System.out.println("Incorrect password.");
                return;
            }
        }
        System.out.println("Username doesn't exist.");
    }

    public static void logout(){
        loggedUser.status = false;
        loggedUser = null;
        System.out.println("Logged out.");
    }
}
