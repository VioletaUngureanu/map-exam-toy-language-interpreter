import controller.Controller;
import controller.IController;
import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.programState.*;
import model.statement.*;
import programExamples.a5Examples.ExamplesA5;
import programExamples.a6Examples.ExampleA6;
import repository.IRepository;
import repository.ListRepository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

public class Interpreter {
    public static void main(String[] args) {

        ExamplesA5 examples = new ExamplesA5();
     //   ExampleA6 examples = new ExampleA6();
        IStatement ex1 = examples.getExample1();
        try {
            ex1.typeCheck(new SymbolTable());
        } catch (InvalidTypeException | VariableNotDefinedException e) {
            System.out.println("Typecheck error: " + e.getMessage());
            return;
        }
        IExecutionStack executionStack1 = new ExecutionStack();
        executionStack1.push(ex1);
        ProgramState programState1 = new ProgramState(executionStack1, new SymbolTable(), new Out(), new MapFileTable(), new Heap());
        IRepository repository1 = new ListRepository("logThreads.txt");
        repository1.addProgram(programState1);
        IController controller1 = new Controller(repository1);
        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.show();

    }
}
