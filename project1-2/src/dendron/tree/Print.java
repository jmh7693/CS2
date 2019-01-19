/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, Print.java
 */
package dendron.tree;
import dendron.machine.Machine;
import java.util.*;

/**
 * A node that represents the displaying of the value of an expression on the console
 */
public class Print extends Object implements ActionNode{

    private ExpressionNode printee;

    /**
     * sets up a Print node
     * @param printee the expression to be evaluated and printed
     */
    public Print(ExpressionNode printee){
        this.printee = printee;
    }

    /**
     * evaluates the RHS expression and assigns the result to the variable
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer> symTab){
        System.out.println("=== " + printee.evaluate(symTab));
    }

    /**
     * shows a statement on standard ouput as the word "Print"
     * followed by the infix form of the expression
     */
    public void infixDisplay(){
        System.out.println("Print ");
        printee.infixDisplay();
    }

    /**
     * This method returns the code emitted by the printee node that pushes the value of the printee
     * expression onto the stack, followed by a PRINT instruction
     * @return a list of instructions ending in the ones that compute the value to be printed, and print it.
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.addAll(printee.emit());
        instructions.add(new Machine.Print());
        return instructions;

    }
}
