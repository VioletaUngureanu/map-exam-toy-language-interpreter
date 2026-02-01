package view.commands;

public class ExitCommand extends Command {
    public ExitCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public String execute() {

        System.exit(0);
        return "Exited the program.";
    }
}
