/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, Variable.java
 */
package dendron.tree;
import dendron.machine.Machine;
import java.util.*;

/**
 * The ExpressionNode for a simple variable
 */
public class Variable extends Object implements ExpressionNode{

    private String name;

    /**
     * Sets the name of this new Variable.
     * @param name the name of this variable
     */
    public Variable(String name){
        this.name = name;
    }

    /**
     * Prints on standard output the Variable's name
     */
    public void infixDisplay(){
        System.out.print(this.name);
    }

    /**
     * Emits a LOAD instruction that pushes the Variable's value onto the stack
     * @return a list containing a single LOAD instruction
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.add(new Machine.Load(this.name));
        return instructions;
    }

    /**
     * evaluates a variable by fetching its value
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the variable's current value in the symbol table
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        return symTab.get(name);
    }
}
