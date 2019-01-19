package dendron.tree;

import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author Jason Hicks
 */
public class ParseTree extends Object{

    private Program prog;
    private Map<String, Integer> symTab;

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
        this.prog = new Program();
        symTab = new HashMap<>();

        while(!program.isEmpty()){
            this.prog.addAction(parseAction(program));
        }
    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
        if(program.isEmpty()){
            return null;

        }else{
            if(program.get(0) == ":="){
                return new Print(parseExpr(program));
            }else{
                return new Assignment(program.remove(0), parseExpr(program));
            }
        }

    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        if(!program.isEmpty()){
            String val1 = program.get(0);
            String val2 = program.remove(0);
            if(val1.equals("+")){
                return new BinaryOperation(val2, parseExpr(program), parseExpr(program));
            }else if(val1.equals("-")){
                return new BinaryOperation(val2, parseExpr(program), parseExpr(program));
            }else if(val1.equals("*")){
                return new BinaryOperation(val2, parseExpr(program), parseExpr(program));
            }else if(val1.equals("/")){
                return new BinaryOperation(val2, parseExpr(program), parseExpr(program));
            }else if(val1.equals("_")){
                return new UnaryOperation(val2, parseExpr(program));
            }else if(val1.equals("#")){
                return new UnaryOperation(val2, parseExpr(program));
            }else if(val1.matches("^[a-zA-Z].*")){
                return new Variable(val2);
            }else if(val1.matches("[-+]?\\d+")){
                return new Constant(Integer.parseInt(val2));
            }
        }else{
            return null;
        }
        return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        prog.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
        prog.execute(symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return prog.emit();
    }

}
