package programExamples.countDownLatchExample;

import model.expression.ArithmeticExpression;
import model.expression.HeapReadingExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.IntType;
import model.type.ReferenceType;
import model.value.IntegerValue;

import java.util.concurrent.CountDownLatch;

public class LatchExample {
    private final IStatement example;
    public LatchExample()
    {


        //fork(wh(v1,rh(v1)*10);print(rh(v1));countDown(cnt);
        // fork(wh(v2,rh(v2)*10);print(rh(v2));countDown(cnt);
        // fork(wh(v3,rh(v3)*10);print(rh(v3));countDown(cnt))
        // )
        // );
        IStatement fork = new ForkStatement(
                new CompoundStatement(
                        new HeapWritingStatement("v1", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v1")),
                                new ValueExpression(new IntegerValue(10)), '*')),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
                                new CompoundStatement(
                                        new CountDownStatement("cnt"),
                                        new ForkStatement(
                                                new CompoundStatement(
                                                        new HeapWritingStatement("v2", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v2")),
                                                                new ValueExpression(new IntegerValue(10)), '*')),
                                                        new CompoundStatement(
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
                                                                new CompoundStatement(
                                                                        new CountDownStatement("cnt"),
                                                                        new ForkStatement(
                                                                                new CompoundStatement(
                                                                                        new HeapWritingStatement("v3",
                                                                                                new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v3")),
                                                                                                        new ValueExpression(new IntegerValue(10)), '*')),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v3") )),
                                                                                                new CountDownStatement("cnt")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        //Ref int v1; Ref int v2; Ref int v3; int cnt;
        //new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,rH(v2));

        //fork(wh(v1,rh(v1)*10);print(rh(v1));countDown(cnt);
        // fork(wh(v2,rh(v2)*10);print(rh(v2));countDown(cnt);
        // fork(wh(v3,rh(v3)*10);print(rh(v3));countDown(cnt))
        // )
        // );
        //await(cnt);
        //print(100);
        //countDown(cnt);
        //print(100)
        this.example =
                new CompoundStatement(
                        new VariableDeclarationStatement(new ReferenceType(new IntType()), "v1"),
                        new CompoundStatement(
                                new VariableDeclarationStatement(new ReferenceType(new IntType()), "v2"),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(new ReferenceType(new IntType()), "v3"),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement(new IntType(), "cnt"),
                                                new CompoundStatement(
                                                        new HeapAllocationStatement("v1", new ValueExpression(new IntegerValue(2))),
                                                        new CompoundStatement(
                                                                new HeapAllocationStatement("v2", new ValueExpression(new IntegerValue(3))),
                                                                new CompoundStatement(
                                                                        new HeapAllocationStatement("v3", new ValueExpression(new IntegerValue(4))),
                                                                        new CompoundStatement(
                                                                                new NewLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2"))),
                                                                                new CompoundStatement(
                                                                                        fork,
                                                                                        new CompoundStatement(
                                                                                                new AwaitStatement("cnt"),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new ValueExpression(new IntegerValue(100))),
                                                                                                        new CompoundStatement(
                                                                                                                new CountDownStatement("cnt"),
                                                                                                                new PrintStatement(new ValueExpression(new IntegerValue(100)))
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                )

                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );
    }

    public IStatement getExample(){return this.example;}
}
