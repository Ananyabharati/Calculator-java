import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JLabel titleLabel;
    private double num1, num2, result;
    private String currentOperation = "";
    private char operator;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.PINK);
        setLayout(null); // Set layout to null for absolute positioning

        titleLabel = new JLabel("Calculator", SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 350, 30);
        add(titleLabel);

        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 50));
        display.setBounds(25, 40, 300, 50);
        add(display);

        String[] buttonLabels = {
                "7", "8", "9",
                "/", "6", "5",
                "4", "*", "3",
                "2", "1", "+", "0",
                "-", ".", "=", "Back", "Clear"
        };
        int x = 25, y = 100;
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(70, 30));
            button.setBounds(x, y, 70, 30);
            add(button);
            x += 75;
            if (x > 300) {
                x = 25;
                y += 40;
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> {
                if (!currentOperation.equals("="))
                    display.setText(display.getText() + command);
                else {
                    display.setText(command);
                    currentOperation = "";
                }
                break;
            }
            case "+", "-", "*", "/" -> {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                currentOperation = command;
                display.setText(display.getText() + " " + command + " ");
                break;
            }
            case "=" -> {
                num2 = Double.parseDouble(display.getText().replaceAll(".*[+\\-*/]", ""));
                switch (operator) {
                    case '+' -> result = num1 + num2;
                    case '-' -> result = num1 - num2;
                    case '*' -> result = num1 * num2;
                    case '/' -> {
                        if (num2 != 0)
                            result = num1 / num2;
                        else
                            display.setText("Error: Division by zero");
                    }
                }
                display.setText(String.valueOf(result));
                currentOperation = "=";
                break;
            }
            case "Back" -> {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            }
            case "Clear" -> {
                display.setText("");
                currentOperation = "";
                break;
            }
            default -> display.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
