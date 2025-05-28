import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    String[] buttonValues = {
                "AC", "+/-", "%", "e",
                "asin", "acos", "atan", "π",
                "sin", "cos", "tan", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "sqrt", "=",

    };

    String[] topThree = {"AC","+/-","%"};
    String[] rightSide = {"e","π","/","x","-","+","="};

    String[] twoNumOperations = {"%","/", "x", "-", "+"};
    String[] oneNumOperations= {"asin","acos","atan","sin","cos","tan","sqrt","e","π", "+/-"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    Calculator() {

        //set frame values
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //setting display values
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        //add display to screen
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        //add buttons to screen
        buttonsPanel.setLayout(new GridLayout(7, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        //give buttons values
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);

            if(Arrays.asList(topThree).contains(buttonValue)) {
                button.setBackground(customOrange);
            }else if(Arrays.asList(rightSide).contains(buttonValue)) {
                button.setBackground(customOrange);
            }else{
                button.setBackground(customDarkGray);
            }
            button.setForeground(Color.WHITE);

            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if(operator == null){
                        if (Arrays.asList(twoNumOperations).contains(buttonValue)) {
                            operator = buttonValue;
                            A = displayLabel.getText();
                            displayLabel.setText("0");
                        } else if(Arrays.asList(oneNumOperations).contains(buttonValue)) {
                            operator = buttonValue;
                            A = displayLabel.getText();
                            displayLabel.setText(removeZeroDecimal(calculate(operator,A,"0")));
                            A = displayLabel.getText();
                            operator = null;
                        }
                    }
                    if(buttonValue.equals(".")){
                        if(!displayLabel.getText().contains(buttonValue)){
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                    } else if("0123456789".contains(buttonValue)){
                        if(displayLabel.getText().equals("0")){
                            displayLabel.setText(buttonValue);
                        }
                        else{
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                    }
                    if(operator != null && buttonValue.equals("=")) {
                        B = displayLabel.getText();
                        double answer = calculate(operator, A, B);
                        displayLabel.setText(removeZeroDecimal(answer));
                        A = displayLabel.getText();
                        B = null;
                        operator = null;
                    } else if(buttonValue.equals("AC")){
                        clearAll();
                        displayLabel.setText("0");
                    }
                }
            });
            frame.setVisible(true);

        }
    }

    public Double calculate(String operator, String A, String B) {
        switch (operator) {
            case "+":
                return Double.parseDouble(A) + Double.parseDouble(B);
            case "-":
                return Double.parseDouble(A) - Double.parseDouble(B);
            case "x":
                return Double.parseDouble(A) * Double.parseDouble(B);
            case "/":
                return Double.parseDouble(A) / Double.parseDouble(B);
            case "sqrt":
                return Math.sqrt(Double.parseDouble(A));
            case "sin":
                return Math.sin(Double.parseDouble(A));
            case "cos":
                return Math.cos(Double.parseDouble(A));
            case "tan":
                return Math.tan(Double.parseDouble(A));
            case "asin":
                return Math.asin(Double.parseDouble(A));
            case "acos":
                return Math.acos(Double.parseDouble(A));
            case "atan":
                return Math.atan(Double.parseDouble(A));
            case "e":
                return Double.parseDouble(A) * Math.E;
            case "π":
                return Double.parseDouble(A) * Math.PI;
            case "%":
                return Double.parseDouble(A) % Double.parseDouble(B);
            default:
                operator = null;
                return Double.parseDouble(A) * -1;
        }
    }

    public void clearAll() {
        A = "0";
        operator = null;
        B = null;
        }

        String removeZeroDecimal(double numDisplay) {
            if (numDisplay % 1 ==0) {
                return Integer.toString((int) numDisplay);
            }
            return Double.toString(numDisplay);
        }
    }
