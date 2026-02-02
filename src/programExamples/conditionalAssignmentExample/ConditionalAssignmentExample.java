package programExamples.conditionalAssignmentExample;

import model.statement.CompoundStatement;
import model.statement.IStatement;
import model.statement.VariableDeclarationStatement;

public class ConditionalAssignmentExample {
    private final IStatement example;
    public ConditionalAssignmentExample() {
        // Example code goes here
        // Ref int a; Ref int b; int v;
        //new(a,0); new(b,0);
        //wh(a,1); wh(b,2);
        //v=(rh(a)<rh(b))?100:200;
        //print(v);
        //v= ((rh(b)-2)>rh(a))?100:200;
        //print(v);

        this.example =
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                new model.type.ReferenceType(new model.type.IntType()),
                                "a"
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        new model.type.ReferenceType(new model.type.IntType()),
                                        "b"
                                ),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(
                                                new model.type.IntType(),
                                                "v"
                                        ),
                                        new CompoundStatement(
                                                new model.statement.HeapAllocationStatement(
                                                        "a",
                                                        new model.expression.ValueExpression(
                                                                new model.value.IntegerValue(0)
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new model.statement.HeapAllocationStatement(
                                                                "b",
                                                                new model.expression.ValueExpression(
                                                                        new model.value.IntegerValue(0)
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new model.statement.HeapWritingStatement(
                                                                        "a",
                                                                        new model.expression.ValueExpression(
                                                                                new model.value.IntegerValue(1)
                                                                        )
                                                                ),
                                                                new CompoundStatement(
                                                                        new model.statement.HeapWritingStatement(
                                                                                "b",
                                                                                new model.expression.ValueExpression(
                                                                                        new model.value.IntegerValue(2)
                                                                                )
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new model.statement.ConditionalAssignmentStatement(
                                                                                        "v",
                                                                                        new model.expression.RelationalExpression(
                                                                                                new model.expression.HeapReadingExpression(
                                                                                                        new model.expression.VariableExpression("a")
                                                                                                ),
                                                                                                "<",
                                                                                                new model.expression.HeapReadingExpression(
                                                                                                        new model.expression.VariableExpression("b")
                                                                                                )
                                                                                        ),
                                                                                        new model.expression.ValueExpression(
                                                                                                new model.value.IntegerValue(100)
                                                                                        ),
                                                                                        new model.expression.ValueExpression(
                                                                                                new model.value.IntegerValue(200)
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new model.statement.PrintStatement(
                                                                                                new model.expression.VariableExpression("v")
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new model.statement.ConditionalAssignmentStatement(
                                                                                                        "v",
                                                                                                        new model.expression.RelationalExpression(
                                                                                                                new model.expression.ArithmeticExpression(
                                                                                                                        new model.expression.HeapReadingExpression(
                                                                                                                                new model.expression.VariableExpression("b")
                                                                                                                        ),
                                                                                                                        new model.expression.ValueExpression(
                                                                                                                                new model.value.IntegerValue(2)
                                                                                                                        ),
                                                                                                                        '-'
                                                                                                                ),
                                                                                                                ">",
                                                                                                                new model.expression.HeapReadingExpression(
                                                                                                                        new model.expression.VariableExpression("a")
                                                                                                                )
                                                                                                        ),
                                                                                                        new model.expression.ValueExpression(
                                                                                                                new model.value.IntegerValue(100)
                                                                                                        ),
                                                                                                        new model.expression.ValueExpression(
                                                                                                                new model.value.IntegerValue(200)
                                                                                                        )
                                                                                                ),
                                                                                                new model.statement.PrintStatement(
                                                                                                        new model.expression.VariableExpression("v")
                                                                                                )
                                                                                        )                                                    )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                );
    }

    public IStatement getExample() {
        return example;
    }

}
