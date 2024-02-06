package Casino;

import Casino.User.Authenticator;
import Casino.User.User;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                Roulette.spin();
            }
        };

        // Schedule the task to run every 30 seconds (30,000 milliseconds)
        timer.scheduleAtFixedRate(task, 20000, 30000);


        User carlos = new User("Carlos", "Souza", "carlitos", "aaaa");
        User jacinto = new User("Jacinto", "Leite", "jacintogameplays", "aaaa");


        Authenticator.authenticate("carlitos","aaaa");
        carlos.deposit(20);
        Roulette.createBet(Roulette.Colors.BLACK, 20);
        Authenticator.logout();
        Authenticator.authenticate("jacintogameplays", "aaaa");
        jacinto.deposit(20);
        Roulette.createBet(Roulette.Colors.RED,20);

    }
}
