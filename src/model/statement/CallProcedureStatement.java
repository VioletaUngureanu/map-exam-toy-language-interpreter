package model.statement;

import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.value.IValue;

import java.util.List;

public class CallProcedureStatement implements IStatement{

    String name;
    List<IValue> actualParameters;

    @Override
    public ProgramState execute(ProgramState state) {

        /**
         * IValue procedureValue = state.symbolTable().getVariableValue(name);
         * if (!(procedureValue instanceof ProcedureValue (punem fieldurile din ProcedureValue))) throw exception
         *
         * tre sa verficam ca avem acelasi nr de parametri
         *
         * trebuie sa construim parametrii
         * construim un program state artificial
         * facem un nou symbol table pentru procedura
         * int i = 0;
         * formalParameters.emtrySet().for  -> se face ceva automat
         * dupa ce a facut noul program state, executam pana execution stack e empty
         *
         * probleme la implementarea initialului program state pentru procedura: nu putem face fork in procedura si
         * totul e atomic (Se executa intr-un singur pas)
         *
         * pentru a putea face fork in procedura, putem sa ne inspiram din cum se pun procedurile/functiile pe stiva
         * deci nu e bine sa ne apucam sa facem un nou program state
         *  in loc sa creem un nou stack, pur si simplu punem body-ul metodei pe execution stack
         *  dar tot ne-ar trebui un nou symbol table
         *  cel mai natural ar fi sa avem un stack de symbol table-uri
         */


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
