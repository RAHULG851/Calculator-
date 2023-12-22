import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField input;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] operatorButtons = new JButton[4];
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton decimalButton, equalButton, deleteButton, clearButton;
    private String inputValue = "";
    private String operatorValue = "";
    private double result = 0;

    public Calculator() {
        setLayout(new BorderLayout());

        input = new JTextField();
        input.setEditable(false);
        input.setFont(new Font("Arial", Font.BOLD, 14));
        input.setPreferredSize(new Dimension(450, 50));
        input.setHorizontalAlignment(SwingConstants.RIGHT);
        add(input, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }

        addButton = new JButton("+");
        addButton.addActionListener(this);
        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(this);
        divideButton = new JButton("/");
        divideButton.addActionListener(this);

        operatorButtons[0] = addButton;
        operatorButtons[1] = subtractButton;
        operatorButtons[2] = multiplyButton;
        operatorButtons[3] = divideButton;

        decimalButton = new JButton(".");
        decimalButton.addActionListener(this);
        equalButton = new JButton("=");
        equalButton.addActionListener(this);
        deleteButton = new JButton("DEL");
        deleteButton.addActionListener(this);
        clearButton = new JButton("CLEAR");
        clearButton.addActionListener(this);

        panel.add(decimalButton);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(equalButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();

            if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
                if (!operatorValue.isEmpty()) {
                    calculate();
                }
                operatorValue = buttonText;
                inputValue += buttonText + " ";
            } else if (buttonText.equals("=")) {
                calculate();
            } else if (buttonText.equals("DEL")) {
                if (!inputValue.isEmpty()) {
                    inputValue = inputValue.substring(0, inputValue.length() - 1);
                }
            } else if (buttonText.equals("CLEAR")) {
                inputValue = "";
                operatorValue = "";
                result = 0;
            } else {
                inputValue += buttonText;
            }

            input.setText(inputValue);
        }
    }

    private void calculate() {
        if (!operatorValue.isEmpty()) {
            double operand1 = Double.parseDouble(inputValue.substring(0, inputValue.indexOf(operatorValue)));
            double operand2 = Double.parseDouble(inputValue.substring(inputValue.indexOf(operatorValue) + 2));

            switch (operatorValue) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
            }

            inputValue = inputValue.substring(0, inputValue.indexOf(operatorValue)) + " " + operatorValue + " " + result;
            operatorValue = "";
        }
    }
}