package Evaluator;

import operators.Operator;
import java.util.*;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;

    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/() ";

    public Evaluator()
    {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }
public void CloseparCheck()
{
    while(operatorStack.peek().priority()>=1)
   {
        Operator oldOpr = operatorStack.pop();
        Operand op2 = operandStack.pop();
        Operand op1 = operandStack.pop();
        operandStack.push( oldOpr.execute( op1, op2));
    }
    operatorStack.pop();
    
}



    public int eval( String expression ) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators

        // TODO Operator is abstract - this will need to be fixed:


        while ( this.tokenizer.hasMoreTokens() ) //first while
        {
            // filter out spaces
            if ( !( token = this.tokenizer.nextToken() ).equals( " " )) //first if
            {
                // check if token is an operand
                if ( Operand.check( token ))
                    {
                    operandStack.push( new Operand( token ));
                    }
                    else {                                          //first else
                            if ( ! Operator.check( token ))
                            {
                                System.out.println( "*****invalid token******" );
                                throw new RuntimeException("*****invalid token******");

                            }

                            if(token.equals(")"))
                            {
                                CloseparCheck();
                                continue;
                            }
                            if(token.equals("-") )
                            {

                            }
                    if(token.equals("(")){

                        operatorStack.push(Operator.getOperator("("));

                        continue;

                    }


                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example.
                    Operator newOperator =  Operator.getOperator( token );

                    if(operatorStack.isEmpty())
                    {
                        operatorStack.add(newOperator);
                        continue;
                    } //close if

                    if(!(operatorStack.isEmpty())){
                    if ( operatorStack.peek().priority() >= newOperator.priority() )
                    {
                        // note that when we eval the expression 1 - 2 we will
                        // push the 1 then the 2 and then do the subtraction operation
                        // This means that the first number to be popped is the
                        // second operand, not the first operand - see the following code
                        Operator oldOpr = operatorStack.pop();
                        Operand op2 = operandStack.pop();
                        Operand op1 = operandStack.pop();
                        operandStack.push( oldOpr.execute( op1, op2 ));
                    } //close while

                    operatorStack.push( newOperator );
                    } //close if (empty)


                } //close  first else
            } //close first if in while

        } //close first while

  switch (operatorStack.peek().priority()) {
            case 3: case 2: case 1: case 0:
                {
                    Operator oldOpr = operatorStack.pop();
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    operandStack.push( oldOpr.execute( op1, op2 ));
                    break;
                }


            default:
                break;
        }
   switch  (operandStack.size()){
            case 3: case 2:
                {
                    Operator oldOpr = operatorStack.pop();
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    operandStack.push( oldOpr.execute( op1, op2 ));
                    break;
                }
                
           
            default:

                break;
   }


//if(operandStack.pop().getValue() == Exception){
//}
        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until it only contains the init operator;
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop.

        return operandStack.pop().getValue();
    }
}