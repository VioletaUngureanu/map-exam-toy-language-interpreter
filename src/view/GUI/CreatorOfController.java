package view.GUI;

import controller.Controller;
import model.programState.*;
import model.statement.IStatement;
import repository.IRepository;
import repository.ListRepository;

public class CreatorOfController {

    public static Controller buildControllerForStatement(IStatement stmt) {

        IExecutionStack exeStack = new ExecutionStack();
        exeStack.push(stmt);

        SymbolTable symTable = new SymbolTable();
        IOut out = new Out();
        IFileTable fileTable = new MapFileTable();
        IHeap heap = new Heap();

        ProgramState prg = new ProgramState(exeStack, symTable, out, fileTable, heap);

        IRepository repo = new ListRepository("logGUI.txt");
        repo.addProgram(prg);

        return new Controller(repo);
    }
}
