import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double firstNumber, secondNumber, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display Panel
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.DARK_GRAY);

        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "0", "C", "=", "+"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]") || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            firstNumber = secondNumber = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/": result = (secondNumber != 0) ? firstNumber / secondNumber : 0; break;
            }
            display.setText(String.valueOf(result));
        } else {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
