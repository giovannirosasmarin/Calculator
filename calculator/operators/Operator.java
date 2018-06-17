package operators;

import java.util.*;
import Evaluator.*;

public abstract class Operator 
{
  // The Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Operators.
  // ALL subclasses of operator MUST be in their own file.
  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  // HashMap getOperator = new HashMap();
  // getOperator.put( "+", new AdditionOperator() );
  // getOperator.put( "-", new SubtractionOperator() );
  private static final HashMap<String, Operator> getOperator = new HashMap<>();
 public abstract int priority();
        /*      Priority for each operator:
        * 				"(" 0
        * 				")" 0
        * 				"+" 2
        * 				"-" 2
        * 				"*" 3
        * 				"/" 3
        */

   
    static
    {
      getOperator.put( "+", new AdditionOperator() );
      getOperator.put( "-", new SubtractionOperator() );
      getOperator.put( "*", new MultiplicationOperator() );
      getOperator.put( "/", new DivisionOperator() );
      getOperator.put( "^", new PowerOperator() );
      getOperator.put( "(", new OpenParOperator() );
      getOperator.put( ")", new CloseParOperator() );


    }

  public abstract Operand execute( Operand op1, Operand op2 );

  public static boolean check( String token )
    {
        boolean check = token.matches("[-+*/^()]");
        return check;
    }

    public static Operator getOperator(String token)
    {
        return getOperator.get(token);
    }
}
