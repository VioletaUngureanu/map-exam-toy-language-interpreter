package programExamples.a3Examples;

import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.IntType;
import model.type.StringType;
import model.value.StringValue;

public class ExamplesA3 {
    private final IStatement example1;
    public ExamplesA3() {
        this.example1 = createExample1();
    }

    public IStatement getExample1() {
        return example1;
    }

    private IStatement createExample1() {
        /*
//    string varf;
//    varf="test.in";
//    openRFile(varf);
//    int varc;
//    readFile(varf,varc);print(varc);
//    readFile(varf,varc);print(varc)

//     */
        return new CompoundStatement(
                new VariableDeclarationStatement(new StringType(), "varf"),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(new IntType(), "varc"),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),

                                                                        new PrintStatement(new VariableExpression("varc"))

                                                        )
                                                )
                                        )))
                )
        );

    }
}
