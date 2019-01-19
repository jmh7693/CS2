/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, Constant.java
 */
package dendron.tree;
import dendron.machine.Machine;
import java.util.*;

/**
 * An expression node representing a constant, i.e., literal value
 */
public class Constant extends Object implements ExpressionNode{

    private int value;

    /**
     * stores the integer value in this new Constant
     * @param value the integer it will hold
     */
    public Constant(int value){
        this.value = value;
    }

    /**
     * prints the Constant's value on standard output
     */
    public void infixDisplay(){
        System.out.print(this.value);
    }

    /**
     * evaluates the constant
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the Constant's value
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        return this.value;
    }

    /**
     * emits an instruction to push the value onto the stack
     * @return a list containing that one instruction
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        instructions.add(new Machine.PushConst(this.value));
        return instructions;
    }
}
