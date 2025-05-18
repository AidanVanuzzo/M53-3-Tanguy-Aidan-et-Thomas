package main;

public class CommandRegistry {

    CMap map;
    CMove move;

    CommandRegistry() {
        this.map = new CMap();
        this.move = new CMove();
    }

    public void commandExecute() {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Entrez une commande : ");
        String command = scanner.nextLine();

        switch (command) {
            case "Move":

                move.execute();

                break;

            case "Map":

                map.execute();

                break;

            default:

                System.out.println("commande invalide, essayez la commande 'Help'");

                break;
        }

        scanner.close();

    }
}
