package interfaces;

//Interface qui oblige toutes les commandes à implémenter la méthode execute()
//Force également à récupérer la description de la commande via getDescription()
public interface ICommand {
    void execute();
    String getDescription();
}