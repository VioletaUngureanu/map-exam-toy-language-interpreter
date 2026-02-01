package model.statement;

import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.Type;

import java.util.SequencedMap;

public class DeclareProcedureStatement implements IStatement {

    String name;
    SequencedMap<String, Type> formalParameters;
    IStatement body;

    public DeclareProcedureStatement(String name, SequencedMap<String, Type> formalParameters, IStatement body) {
        this.name = name;
        this.formalParameters = formalParameters;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        // we can either make a new Table, ProcedureTable, to hold all the procedures
        // but because it is easier, we will just add it to the SymbolTable with a special value, ProcedureValue
        SymbolTable symbolTable = state.symbolTable();
        // before declaring the procedure, we should check if it is already defined

        //symbolTable.isDefined(name) check can be added here if needed

        // we ought to make a new class, ProcedureValue and procedureType, but for simplicity, we will just store the statement directly
        // symbolTable.declareVariable (name, ProcedureType);
        // symbolTable.setValue(name, new ProcedureValue(formalParameters, body));

        // procedureValue will have the formal parameters and the body

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        return null;
    }
}
