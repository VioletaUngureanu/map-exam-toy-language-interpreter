package programExamples.a6Examples;

import model.expression.HeapReadingExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.StringType;
import model.value.IntegerValue;

public class ExampleA6 {
    private final IStatement example;
    public ExampleA6() {
        // string v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        this.example = new CompoundStatement(
                new VariableDeclarationStatement(new StringType(), "v"),
                new CompoundStatement(
                        new VariableDeclarationStatement( new ReferenceType(new IntType() ), "a"),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntegerValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new HeapWritingStatement("a", new ValueExpression(new IntegerValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new ValueExpression(new IntegerValue(32))),
                                                                                new PrintStatement(
                                                                                        new HeapReadingExpression(new VariableExpression("a"))
                                                                                ))))
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(
                                                                new HeapReadingExpression(new VariableExpression("a"))
                                                        )
                                                )


                                        )))));
    }
    public IStatement getExample1() {
        return example;
    }
}
