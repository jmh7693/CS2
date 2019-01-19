/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, UnaryOperation.java
 */
package dendron.tree;
import dendron.Errors;
import dendron.machine.Machine;
import java.util.*;

/**
 * A calculation represented by a unary operator and its operand.
 */
public class UnaryOperation extends Object implements ExpressionNode{

    private final static String NEG = "_";
    private final static String SQRT = "#";
    private static java.util.Collection<String> OPERATORS = Arrays.asList(NEG, SQRT);

    private String operator;
    private ExpressionNode expr;

    /**
     * Creates a new UnaryOperation node.
     * @param operator the string representation of the operation
     * @param expr the operand
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

    /**
     * computes the result of evaluating the expression and applies the operator to it
     * @param symTab symbol table, if needed, to fetch variable values
     * @return int
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        int e = this.expr.evaluate(symTab);

        if(OPERATORS.contains(NEG)){
            return -1 * e;
        }else if(OPERATORS.contains(SQRT)){
            if(e >= 0) {
                return (int) java.lang.Math.sqrt(e);
            }else{
                Errors.report(Errors.Type.ILLEGAL_VALUE, "Cannot square root a negative value.");
            }
        }
        return 0;
    }

    /**
     * Prints, on standard output, the infixDisplay of the child nodes
     * preceded by the operator and without an intervening blank.
     */
    public void infixDisplay(){
        System.out.print(operator);
        expr.infixDisplay();
    }

    /**
     * Emits the Machine instructions necessary to perform the computation of this UnaryOperation.
     * The operator itself is realized by an instruction that pops a value off the stack, applies
     * the operator, and pushes the answer.
     * @return a list containing instructions for the expression and the instruction to perform the operation
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.addAll(expr.emit());

        if(OPERATORS.contains(NEG)){
            instructions.add(new Machine.Negate());
        }else if(OPERATORS.contains(SQRT)){
            instructions.add(new Machine.SquareRoot());
        }
        return instructions;
    }
}
