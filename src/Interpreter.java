import controller.Controller;
import controller.IController;
import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.programState.*;
import model.statement.*;
import programExamples.a5Examples.ExamplesA5;
import programExamples.a6Examples.ExampleA6;
import programExamples.conditionalAssignmentExample.ConditionalAssignmentExample;
import programExamples.countDownLatchExample.LatchExample;
import repository.IRepository;
import repository.ListRepository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

import java.util.concurrent.CountDownLatch;

public class Interpreter {
    public static void main(String[] args) {

//        ExamplesA5 examples = new ExamplesA5();
//     //   ExampleA6 examples = new ExampleA6();
//        IStatement ex1 = examples.getExample1();
//        try {
//            ex1.typeCheck(new SymbolTable());
//        } catch (InvalidTypeException | VariableNotDefinedException e) {
//            System.out.println("Typecheck error: " + e.getMessage());
//            return;
//        }
        ConditionalAssignmentExample conditionalAssignmentStatement = new ConditionalAssignmentExample();
        IExecutionStack executionStack1 = new ExecutionStack();
        IStatement conditionalAssignmentStatementExample = conditionalAssignmentStatement.getExample();
        executionStack1.push(conditionalAssignmentStatementExample);
        ProgramState programState1 = new ProgramState(executionStack1, new SymbolTable(), new Out(), new MapFileTable(), new Heap(), new LatchTable());
        IRepository repository1 = new ListRepository("logCondAssignment.txt");
        repository1.addProgram(programState1);
        IController controller1 = new Controller(repository1);

        LatchExample latchExample = new LatchExample();
        IStatement latchStatement = latchExample.getExample();
        IExecutionStack executionStackLatch = new ExecutionStack();
        executionStackLatch.push(latchStatement);
        ProgramState programStateLatch = new ProgramState(executionStackLatch, new SymbolTable(), new Out(), new MapFileTable(), new Heap(), new LatchTable());
        IRepository repositoryLatch = new ListRepository("logLatchExample.txt");
        repositoryLatch.addProgram(programStateLatch);
        IController latchController = new Controller(repositoryLatch);



        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExampleCommand("1", conditionalAssignmentStatementExample.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", latchStatement.toString(), latchController));

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.show();

    }
}
