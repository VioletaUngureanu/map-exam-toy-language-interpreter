package view.commands;

import controller.IController;

public class RunExampleCommand extends Command {

    private final IController controller;

    public RunExampleCommand(String key, String description, IController controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public String execute() {
        try{
            controller.allStepsExecution();
            for (String step : controller.getExecutionLog()) {
                System.out.println(step);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
