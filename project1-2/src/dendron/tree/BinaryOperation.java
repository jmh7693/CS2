/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, BinaryOperation.java
 */
package dendron.tree;
import dendron.Errors;
import dendron.machine.Machine;
import java.util.*;

/**
 * A calculation represented by a binary operator and its two operands.
 */
public class BinaryOperation extends Object implements ExpressionNode{

    private final static String ADD = "+";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String SUB = "-";
    private static java.util.Collection<String> OPERATORS = Arrays.asList(ADD, SUB, MUL, DIV);

    private String operator;
    private ExpressionNode leftChild;
    private ExpressionNode rightChild;

    /**
     * Creates a new BinaryOperation node.
     * @param operator the string rep. of the operation
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * computes the result of evaluating the expression and applies the operator to it
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        int lc = this.leftChild.evaluate(symTab);
        int rc = this.rightChild.evaluate(symTab);

        if(OPERATORS.contains(ADD)) {
            return lc + rc;
        }else if(OPERATORS.contains(SUB)){
            return lc - rc;
        }else if(OPERATORS.contains(MUL)){
            return lc * rc;
        }else if(OPERATORS.contains(DIV)) {
            if(rc != 0) {
                return lc / rc;
            }else{
                Errors.report(Errors.Type.DIVIDE_BY_ZERO, "Cannot divide by zero.");
            }
        }else{
            return 0;
        }
        return 0;
    }

    /**
     * Prints, on standard output, the infixDisplay of the two child nodes separated by the operator and
     * surrounded by parentheses. Blanks are inserted throughout.
     */
    public void infixDisplay(){
        System.out.print("( ");
        leftChild.infixDisplay();
        System.out.print(" " + operator + " ");
        rightChild.infixDisplay();
        System.out.print(" )");
    }

    /**
     * Emits the Machine instructions necessary to perform the computation of this BinaryOperation.
     * The operator itself is realized by an instruction that pops two values off the stack, applies
     * the operator, and pushes the answer
     * @return a list containing instructions for the left operand, instructions for the
     * right operand, and the instruction to perform the operation
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.addAll(leftChild.emit());
        instructions.addAll(rightChild.emit());

        if(OPERATORS.contains(ADD)){
            instructions.add(new  Machine.Add());
        }else if(OPERATORS.contains(SUB)){
            instructions.add(new Machine.Subtract());
        }else if(OPERATORS.contains(MUL)){
            instructions.add(new Machine.Multiply());
        }else if(OPERATORS.contains(DIV)){
            instructions.add(new Machine.Divide());
        }
        return instructions;
    }
}
