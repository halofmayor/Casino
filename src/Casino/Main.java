package Casino;

public class Main {
    public static void main(String[] args) {
        User carlos = new User("Carlos", "Souza", "carlitos", "223345Lol");
        User jacinto = new User("Jacinto", "Leite", "jacintogameplays", "1212kkJ");

        Authenticator.authenticate("carlitos","223345Lol");
        System.out.println(carlos.status);
        carlos.deposit(20);
        System.out.println(carlos.getBalance());
        carlos.withdraw(20);
        System.out.println(carlos.getBalance());
        carlos.deposit(60);
        Authenticator.logout();
        carlos.transfer(20, "jacintogameplays");
        System.out.println(carlos.getBalance());

    }
}
