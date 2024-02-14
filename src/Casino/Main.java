package Casino;

import Casino.User.Authenticator;
import Casino.User.User;

public class Main {
    public static void main(String[] args) {

        Roulette roullete = new Roulette();


        User carlos = new User("Carlos", "Souza", "carlitos", "aaaa");
        User jacinto = new User("Jacinto", "Leite", "jacintogameplays", "aaaa");


        Authenticator.authenticate("carlitos","aaaa");
        carlos.deposit(20);
        Roulette.createBet(Roulette.Colors.BLACK, 20);
        Authenticator.logout();
        Authenticator.authenticate("jacintogameplays", "aaaa");
        jacinto.deposit(20);
        Roulette.createBet(Roulette.Colors.GREEN,20);

    }
}
