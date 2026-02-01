package programExamples.a2Examples;

import model.expression.ArithmeticExpression;
import model.expression.IExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.IntType;
import model.value.IntegerValue;

public class ExamplesA2 {
    private final IStatement example1;
    private final IStatement example2;
    private final IStatement example3;
    public ExamplesA2() {
        this.example1 = createExample1();
        this.example2 = createExample2();
        this.example3 = createExample3();

    }

    private IStatement createExample3() {
        return new CompoundStatement(
            new VariableDeclarationStatement( new IntType(), "a"),
            new CompoundStatement(
                new VariableDeclarationStatement( new IntType(), "v"),
                new CompoundStatement(
                    new AssignmentStatement("a", new ValueExpression( new IntegerValue(1))),
                    new CompoundStatement(
                        new IfStatement(
                            new VariableExpression("a"),
                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))),
                        new PrintStatement(new VariableExpression("v"))
                    )

                )
            )
        );

    }

    private IStatement createExample2() {
        IExpression arth1 = new ArithmeticExpression(new ValueExpression(new IntegerValue(3)),
           new ValueExpression(new IntegerValue(5)), '*');
        IExpression arth2 = new ArithmeticExpression(new ValueExpression(new IntegerValue(2)), arth1, '+');
        return new CompoundStatement(
            new VariableDeclarationStatement( new IntType(), "a"),
            new CompoundStatement(new VariableDeclarationStatement( new IntType(), "b"),
                new CompoundStatement(new AssignmentStatement("a", arth2),
                    new CompoundStatement(
                        new AssignmentStatement("b", new ArithmeticExpression(
                            new VariableExpression("a"), new ValueExpression(new IntegerValue(1)), '+')),
                        new PrintStatement(new VariableExpression("b"))                                    ))
            ));

    }

    private IStatement createExample1() {
        return new CompoundStatement(new VariableDeclarationStatement( new IntType(), "v"),
           new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                  new PrintStatement(new VariableExpression("v"))));
    }

}
