package main;

import interfaces.ICommand;
import java.util.Map;

public class CHelp implements ICommand {

    private String description;
    private Map<String, ICommand> commands;

    public CHelp(Map<String, ICommand> commands) {
        this.description = "Displays the list of available commands.";
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Controls available:");
        for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
            String commandName = entry.getKey().toLowerCase();
            boolean isHelpCommand = commandName.equals("help");

            if (!isHelpCommand) {
                System.out.println(" - " + commandName + " : " + entry.getValue().getDescription());
            }
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
