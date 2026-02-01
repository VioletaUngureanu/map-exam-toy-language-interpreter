package programExamples.a4Examples;

import model.expression.*;
import model.statement.*;
import model.type.IntType;
import model.type.ReferenceType;
import model.value.IntegerValue;

public class ExamplesA4 {
    private final IStatement exHeapAlloc;
    private final IStatement exRead;
    private final IStatement exWrite;
    private final IStatement exWhile;
    private final IStatement ex1GarbageCollector;
    private final IStatement ex2GarbageCollector;

    public IStatement getEx2GarbageCollector() {
        return ex2GarbageCollector;
    }

    public IStatement getEx1GarbageCollector() {
        return ex1GarbageCollector;
    }

    public IStatement getExWhile() {
        return exWhile;
    }

    public IStatement getExWrite() {
        return exWrite;
    }

    public IStatement getExRead() {
        return exRead;
    }

    public IStatement getExHeapAlloc() {
        return exHeapAlloc;
    }

    public ExamplesA4(){
        exHeapAlloc = createExHeap();
        exRead = createExRead();
        exWrite = createExWrite();
        exWhile = createExWhile();
        ex1GarbageCollector = createEx1GarbageCollector();
        ex2GarbageCollector = createEx2GarbageCollector();
    }

    private IStatement createEx2GarbageCollector() {
        // Ref int v; new(v, 20); new(v, 25); print(rH(v))
        return new CompoundStatement(
            new VariableDeclarationStatement(new ReferenceType(
                    new IntType()), "v"),
            new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(25))),
                            new PrintStatement(
                                    new HeapReadingExpression(new VariableExpression("v"))
                            )
                    )
            )
        );
    }

    private IStatement createEx1GarbageCollector() {
        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        return new CompoundStatement(
            new VariableDeclarationStatement(new ReferenceType(
                    new IntType()), "v"),
            new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new ReferenceType(
                                    new ReferenceType(new IntType())), "a"),
                            new CompoundStatement(
                                    new HeapAllocationStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(30))),
                                            new PrintStatement(
                                                    new HeapReadingExpression(
                                                            new HeapReadingExpression(new VariableExpression("a"))
                                                    )
                                            )
                                    )
                            )
                    )
            )
        );
    }

    private IStatement createExWhile() {
        //int v; v=4; (while(v>0) print(v); v=v-1;) print(v)
        return new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "v"),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(4))),
                    new CompoundStatement(
                            new WhileStatement(
                                    new RelationalExpression( new VariableExpression("v"),
                                             ">", new ValueExpression(new IntegerValue(0))),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new AssignmentStatement("v", new ArithmeticExpression(
                                                    new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), '-'))
                            )),
                            new PrintStatement(new VariableExpression("v")))));
    }

    private IStatement createExWrite() {
        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        return new CompoundStatement(
                new VariableDeclarationStatement(new ReferenceType(
                        new IntType()), "v"),
            new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new PrintStatement(
                                    new HeapReadingExpression(new VariableExpression("v"))
                            ),
                            new CompoundStatement(
                                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(30))),
                                    new PrintStatement(
                                            new ArithmeticExpression(
                                                    new HeapReadingExpression(new VariableExpression("v")),
                                                    new ValueExpression(new IntegerValue(5)),
                                                    '+'
                                            )
                                    )
                            )
                    )
            )
        );
    }

    private IStatement createExRead() {
         //  Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        return new CompoundStatement(
                new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"),
            new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new ReferenceType(
                                    new ReferenceType(new IntType())), "a"),
                            new CompoundStatement(
                                    new HeapAllocationStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new PrintStatement(
                                                    new HeapReadingExpression(new VariableExpression("v"))
                                            ),
                                            new PrintStatement(
                                                    new ArithmeticExpression(
                                                            new HeapReadingExpression(
                                                                    new HeapReadingExpression(new VariableExpression("a"))
                                                            ),
                                                            new ValueExpression(new IntegerValue(5)),
                                                            '+'
                                                    )
                                            )
                                    )
                            )
                    )
            )
        );

    }

    private IStatement createExHeap() {
        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        return  new CompoundStatement(
                        new VariableDeclarationStatement(new ReferenceType(new IntType()), "v"),
            new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new ReferenceType(
                                    new ReferenceType(new IntType())), "a"),
                            new CompoundStatement(
                                    new HeapAllocationStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new PrintStatement(new VariableExpression("a")

            ))))));
    }
}
