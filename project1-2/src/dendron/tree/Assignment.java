/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, Assignment.java
 */
package dendron.tree;
import dendron.machine.Machine;
import java.util.*;

/**
 * An ActionNode that represents the assignment of the value of an expression to a variable.
 */
public class Assignment extends Object implements ActionNode{

    private String ident;
    private ExpressionNode rhs;

    /**
     * Set up an Assignment node.
     * @param ident the name of the variable that is getting a new value
     * @param rhs the expression on the "right-hand side" (RHS) of the assignment statement
     */
    public Assignment(String ident, ExpressionNode rhs){
        this.ident = ident;
        this.rhs = rhs;
    }

    /**
     * evaluates the RHS expression and assigns the result to the variable
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer> symTab){
        symTab.put(ident, rhs.evaluate(symTab));
    }

    /**
     * shows this assignment on standard output as a variable followed by an assignment arrow
     * (":=") followed by the infix form of the RHS expression
     */
    public void infixDisplay(){
        System.out.print(":= ");
        rhs.infixDisplay();
    }

    /**
     * This method returns a STORE instruction for the
     * variable in question preceded by the code emitted by the RHS node that eventually
     * pushes the value of the expression onto the stack
     * @return a list of instructions ending in one that stores the top value on the stack to this node's variable
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.addAll(rhs.emit());
        instructions.add(new Machine.Store(ident));
        return instructions;
    }
}
