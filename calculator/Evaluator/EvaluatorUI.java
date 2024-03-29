package Evaluator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener 
{

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = 
    {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private static String CEE;
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) 
    {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI()
    {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
            for (int i = 0; i < EvaluatorUI.bText.length; i++)
                {
                     bt = new Button(bText[i]);
                     bt.setFont(new Font("Courier", Font.BOLD, 28));
                        buttons[i] = bt;
                }

        //add buttons to button panel
                for (int i = 0; i < EvaluatorUI.bText.length; i++) 
                {
                        buttonPanel.add(buttons[i]);
                }

        //set up buttons to listen for mouse input
                 for (int i = 0; i < EvaluatorUI.bText.length; i++) 
                {
                        buttons[i].addActionListener(this);
                }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void space()
    {
        if(txField.getText().contains(" ")){
            txField.setText("");
        }


    }


    public void actionPerformed(ActionEvent arg0) 
    {
  //System.out.println(arg0.getSource());
//   this.txField.setText(this.txField.getText() + arg0.getActionCommand());          //idea given in class by Professor

        // You need to fill in this fuction
        for (int i = 0; i <=13 ; i++)                           //Reduced if statements from buttons[0] to buttons [13]
        {

            if(arg0.getSource() == buttons[i]) {

                space();
                txField.setText(txField.getText() + arg0.getActionCommand());
            }

        }
        for (int i = 15; i <=17 ; i++)                          //Reduced if statements from buttons[15] to buttons [17]
        {
            if(arg0.getSource() == buttons[i]) {

                space();
                txField.setText(txField.getText() + arg0.getActionCommand());
            }
        }

        if(arg0.getSource() == buttons[14]){                    //Had to leave the if statement for "=" out side because it was giving me an error on the loop and the evaluator
            Evaluator calc= new Evaluator();

            //calls the eval class passing the user input argument
            txField.setText(Integer.toString(calc.eval(txField.getText())));

        }if(arg0.getSource() == buttons[18]){                   //if statement for "C" it will clear any thing

        //C  clear the text field
        txField.setText("");

    }if(arg0.getSource() == buttons[19]){                        //if statement for "CE" it will clear any thing. I tried making a function to return the last textfield but could get there
                                                                    //Found an example to get textField before
        //CE
        if(txField.getText().length() <= 1)  {

            CEE = "";

            txField.setText("");

        } else     {

            CEE = txField.getText().substring(0, txField.getText().length() - 1);

            txField.setText(CEE);

        }

    }

    }
}



