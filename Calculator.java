import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField input1, input2, result;
    private JButton addBtn, subBtn, mulBtn, divBtn, clearBtn;

    public Calculator() {
        setTitle("BASIC ARTHEMATIC OPERATIONS");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create components
        JLabel label1 = new JLabel("First Number:");
        JLabel label2 = new JLabel("Second Number:");
        JLabel label3 = new JLabel("Result:");

        input1 = new JTextField(10);
        input2 = new JTextField(10);
        result = new JTextField(15);
        result.setEditable(false);

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        clearBtn = new JButton("Clear");

        // Add action listeners
        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        divBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        // Layout setup
        setLayout(new FlowLayout());
       
        add(label1);
        
        add(input1);

        add(label2);
                add(input2);

                add(label3);
                add(result);

        
        add(addBtn);
                add(subBtn);
        
        add(mulBtn);
               add(divBtn);
        
        add(clearBtn);
    }

    public void actionPerformed(ActionEvent e) {
        String num1Text = input1.getText();
        String num2Text = input2.getText();
        double num1, num2, res = 0;
        String op = e.getActionCommand();

        try {
            num1 = Double.parseDouble(num1Text);
            num2 = Double.parseDouble(num2Text);

            switch (op) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        result.setText("Cannot divide by zero");
                        return;
                    }
                    res = num1 / num2;
                    break;
                case "Clear":
                    input1.setText("");
                    input2.setText("");
                    result.setText("");
                    return;
            }
            result.setText(String.valueOf(res));
        } catch (NumberFormatException ex) {
            result.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
