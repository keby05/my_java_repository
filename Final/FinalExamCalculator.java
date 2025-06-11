import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class FinalExamCalculator {
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                new Calculator();
                        }
                });
        }
}

class Calculator {
        JFrame frame;
        JTextField displayField;
        JTextArea historyArea;
        JScrollPane historyPane;
        ArrayList<String> history = new ArrayList<String>();

        String current = "";
        String operator = "";
        double firstNumber = 0;
        boolean startNew = true;

        public Calculator() {
                frame = new JFrame("Calculator");
                frame.setSize(400, 650);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                displayField = new JTextField("0");
                displayField.setEditable(false);
                displayField.setFont(new Font("Arial", Font.BOLD, 40));
                displayField.setHorizontalAlignment(JTextField.RIGHT);

                JPanel keyPanel = new JPanel();
                keyPanel.setLayout(new GridLayout(6, 4, 5, 5));

                JButton btnAC = new JButton("AC");
                JButton btnC = new JButton("C");
                JButton btnCE = new JButton("CE");
                JButton btnDiv = new JButton("/");
                JButton btn7 = new JButton("7");
                JButton btn8 = new JButton("8");
                JButton btn9 = new JButton("9");
                JButton btnMul = new JButton("*");
                JButton btn4 = new JButton("4");
                JButton btn5 = new JButton("5");
                JButton btn6 = new JButton("6");
                JButton btnMinus = new JButton("-");
                JButton btn1 = new JButton("1");
                JButton btn2 = new JButton("2");
                JButton btn3 = new JButton("3");
                JButton btnPlus = new JButton("+");
                JButton btn0 = new JButton("0");
                JButton btnDot = new JButton(".");
                JButton btnSign = new JButton("+/-");
                JButton btnEqual = new JButton("=");
                JButton btnMod = new JButton("%");
                JButton btnPow = new JButton("^");
                JButton btnSqrt = new JButton("√");

                keyPanel.add(btnAC);
                keyPanel.add(btnC);
                keyPanel.add(btnCE);
                keyPanel.add(btnDiv);
                keyPanel.add(btn7);
                keyPanel.add(btn8);
                keyPanel.add(btn9);
                keyPanel.add(btnMul);
                keyPanel.add(btn4);
                keyPanel.add(btn5);
                keyPanel.add(btn6);
                keyPanel.add(btnMinus);
                keyPanel.add(btn1);
                keyPanel.add(btn2);
                keyPanel.add(btn3);
                keyPanel.add(btnPlus);
                keyPanel.add(btn0);
                keyPanel.add(btnDot);
                keyPanel.add(btnSign);
                keyPanel.add(btnEqual);
                keyPanel.add(btnMod);
                keyPanel.add(btnPow);
                keyPanel.add(btnSqrt);

                JButton[] allButtons = {
                        btnAC, btnC, btnCE, btnDiv,
                        btn7, btn8, btn9, btnMul,
                        btn4, btn5, btn6, btnMinus,
                        btn1, btn2, btn3, btnPlus,
                        btn0, btnDot, btnSign, btnEqual,
                        btnMod, btnPow, btnSqrt
                };

                for (int i = 0; i < allButtons.length; i++) {
                        JButton btn = allButtons[i];
                        btn.setFont(new Font("Arial", Font.BOLD, 24));
                        btn.setBackground(Color.WHITE);
                        btn.setOpaque(true);
                        btn.setBorderPainted(true);
                        btn.setFocusPainted(false);
                        btn.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                        handleInput(btn.getText());
                                }
                        });
                }

                JButton historyButton = new JButton("History");
                historyButton.setFont(new Font("Arial", Font.PLAIN, 18));
                historyButton.setBackground(Color.LIGHT_GRAY);
                historyButton.setFocusPainted(false);
                historyButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                toggleHistory();
                        }
                });

                historyArea = new JTextArea(8, 20);
                historyArea.setEditable(false);
                historyArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

                historyPane = new JScrollPane(historyArea);
                historyPane.setVisible(false);

                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new BorderLayout());
                bottomPanel.add(historyButton, BorderLayout.NORTH);
                bottomPanel.add(historyPane, BorderLayout.CENTER);

                frame.add(displayField, BorderLayout.NORTH);
                frame.add(keyPanel, BorderLayout.CENTER);
                frame.add(bottomPanel, BorderLayout.SOUTH);

                frame.setVisible(true);
        }

        void toggleHistory() {
                if (historyPane.isVisible()) {
                        historyPane.setVisible(false);
                } else {
                        historyPane.setVisible(true);
                }
                frame.revalidate();
        }

        void handleInput(String input) {
                if (input.equals("AC")) {
                        current = "";
                        operator = "";
                        firstNumber = 0;
                        startNew = true;
                        displayField.setText("0");
                } else if (input.equals("C")) {
                        current = "";
                        displayField.setText("0");
                } else if (input.equals("CE")) {
                        if (current.length() > 0) {
                                current = current.substring(0, current.length() - 1);
                        }
                        if (current.equals("")) {
                                displayField.setText("0");
                        } else {
                                displayField.setText(current);
                        }
                } else if (input.equals("+/-")) {
                        if (!current.equals("")) {
                                if (current.startsWith("-")) {
                                        current = current.substring(1);
                                } else {
                                        current = "-" + current;
                                }
                                displayField.setText(current);
                        }
                } else if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") || input.equals("^") || input.equals("%")) {
                        if (!current.equals("")) {
                                firstNumber = Double.parseDouble(current);
                                operator = input;
                                startNew = true;
                        }
                } else if (input.equals("=")) {
                        if (!current.equals("") && !operator.equals("")) {
                                double secondNumber = Double.parseDouble(current);
                                double result = doCalculation(firstNumber, secondNumber, operator);

                                String equation = formatNumber(firstNumber) + " " + operator + " " +
                                                                  formatNumber(secondNumber) + " = " + formatNumber(result);
                                history.add(equation);
                                updateHistory();

                                displayField.setText(formatNumber(result));
                                current = String.valueOf(result);
                                operator = "";
                                startNew = true;
                        }
                } else if (input.equals("√")) {
                        if (!current.equals("")) {
                                double num = Double.parseDouble(current);
                                if (num < 0) {
                                        displayField.setText("Error");
                                        current = "";
                                } else {
                                        double result = Math.sqrt(num);
                                        String equation = "√" + formatNumber(num) + " = " + formatNumber(result);
                                        history.add(equation);
                                        updateHistory();
                                        displayField.setText(formatNumber(result));
                                        current = String.valueOf(result);
                                        startNew = true;
                                }
                        }
                } else if (input.equals(".")) {
                        if (startNew) {
                                current = "0.";
                                startNew = false;
                        } else if (!current.contains(".")) {
                                current += ".";
                        }
                        displayField.setText(current);
                } else {
                        if (startNew) {
                                current = input;
                                startNew = false;
                        } else {
                                current += input;
                        }
                        displayField.setText(current);
                }
        }

        double doCalculation(double num1, double num2, String op) {
                if (op.equals("+")) {
                        return num1 + num2;
                } else if (op.equals("-")) {
                        return num1 - num2;
                } else if (op.equals("*")) {
                        return num1 * num2;
                } else if (op.equals("/")) {
                        if (num2 == 0) return 0;
                        return num1 / num2;
                } else if (op.equals("^")) {
                        return Math.pow(num1, num2);
                } else if (op.equals("%")) {
                        if (num2 == 0) return 0;
                        return num1 % num2;
                }
                return 0;
        }

        String formatNumber(double value) {
                if (value == (long) value) {
                        return String.format("%d", (long) value);
                } else {
                        return String.format("%s", value);
                }
        }

        void updateHistory() {
                String historyText = "";
                for (int i = 0; i < history.size(); i++) {
                        historyText += history.get(i) + "\n";
                }
                historyArea.setText(historyText);
        }
}