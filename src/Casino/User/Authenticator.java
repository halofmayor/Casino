package Casino.User;
import Casino.Roulette;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Authenticator {
    //saves the logged user
    private static User isBlock;
    //
    //deveria fazer uma lista ligada para guardar ambos os usuarios guardados e logados.
    //para isso tmbem teria de alterar o metodo logout!
    //
    public static User loggedUser;

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
                if(actual.equals(isBlock)){
                    System.out.println("Try again later.");
                    return;
                }
                if(Arrays.equals(actual.generateHash(password, actual.salt), actual.getPassword())){
                    loggedUser = actual;
                    actual.status = true;
                    return;
                }
                actual.counter ++;
                System.out.println("Incorrect password.");
                System.out.println("Only have " + (3 - actual.counter) + " tries remaining.");
                if(actual.counter >= 3){
                    isBlock = actual;
                    timeout();
                }
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

    //gives the user a timeout if he fails the password many times
    private static void timeout(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            public void run() {
                isBlock.counter = 0;
                isBlock = null;
            }
        };
        executor.schedule(task, 60, TimeUnit.SECONDS);
    }
}

