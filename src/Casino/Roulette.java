package Casino;

import Casino.User.Authenticator;
import Casino.User.User;

import java.awt.*;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static Casino.User.Authenticator.loggedUser;


public class Roulette {
    private static User bettingUser;

    enum Colors{
        RED,
        BLACK,
        GREEN;

        private static Random random = new Random();
        private static Colors randomColor(){
            int randomValue = random.nextInt(19); // Total 19 slots (18 red, 18 black, 2 green)
            if (randomValue == 18) { // 1 out of 19 slots for green
                return GREEN;
            } else if (randomValue % 2 == 0) { // 18 out of 19 slots for red or black
                return RED;
            } else {
                return BLACK;
            }
        }
    }



    private static Map<Colors, Object[]> userBets = new HashMap<>();

    public static void createBet(Colors color, double betAmount){
        bettingUser = loggedUser;
        Object[] betDetails = {bettingUser, betAmount};
        userBets.put(color, betDetails);
        System.out.println(loggedUser.getUsername() + " just bet " + betAmount + " in " + color);
        loggedUser.withdraw(betAmount);
    }


    public static void spin(){
            Colors color = Colors.randomColor();
            System.out.println(color);
            for(Map.Entry<Colors, Object[]> value : userBets.entrySet()){
                if(!(color == value.getKey())){
                    continue;
                }
                Object[] betDetails = value.getValue();
                User user = (User) betDetails[0];
                double win = ((double) betDetails[1]) * 2;
                if(color.equals(Colors.GREEN)){
                    win *= 7;
                }
                user.casinoDeposit(win);
                System.out.println(user.getUsername() + " just won " + win);
            }
            userBets.clear();

    }
}
