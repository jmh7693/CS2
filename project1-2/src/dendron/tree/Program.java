/**
 * Language: Java
 * Author: Jason Hicks
 * Project 1, Program.java
 */
package dendron.tree;
import dendron.machine.Machine;

import java.beans.Expression;
import java.util.*;

/**
 * An ActionNode used to represent a sequence of other ActionNodes.
 */
public class Program extends Object implements ActionNode{

    private LinkedList<ActionNode> actionList;

    /**
     * Initializes this instance as an empty sequence of ActionNode children.
     */
    public Program(){
        this.actionList = new LinkedList<>();
    }

    /**
     * adds a child of this Program node
     * @param newNode the node representing the action that will execute last
     */
    public void addAction(ActionNode newNode){
        actionList.add(newNode);
    }

    /**
     * executes each ActionNode in this object, from first-added to last-added
     * @param symTab the table of variable values
     */
    public void execute(java.util.Map<String,Integer> symTab){
        for(ActionNode node : actionList){
            node.execute(symTab);
        }
    }

    /**
     * Shows the infix displays of all children on standard output. The order is first-added to last-added.
     */
    public void infixDisplay(){
        for(ActionNode node : actionList){
            node.infixDisplay();
            System.out.println();
        }
    }

    /**
     * creates a list of instructions emitted by each child, from the first-added child to the last-added
     * @return the concatenated instructions lists from all children
     */
    public java.util.List<Machine.Instruction> emit(){
        List<Machine.Instruction> instructions = new ArrayList<>();
        for(ActionNode node : actionList){
            instructions.addAll(node.emit());
        }
        return instructions;
    }
}
