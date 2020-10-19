package ru.mirea.task10;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorByTechnicalTask extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField1, textField2, textField3;
    JButton badd, bsub, bdiv, bmul;

    private static double a = 0, b = 0, result = 0;
    private static int operator = 0;

    public CalculatorByTechnicalTask() {
        frame = new JFrame("Calculator");
        frame.setSize(223, 323);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();

        badd = new JButton("+");
        bsub = new JButton("-");
        bdiv = new JButton("/");
        bmul = new JButton("*");

        textField1.setBounds(30, 40, 280, 30);
        textField2.setBounds(30, 170, 280, 30);
        textField3.setBounds(30, 270, 280, 60);

        badd.setBounds(40, 100, 50, 40);
        bsub.setBounds(110, 100, 50, 40);
        bdiv.setBounds(180, 100, 50, 40);
        bmul.setBounds(250, 100, 50, 40);

        frame.add(textField1);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(bdiv);
        frame.add(bmul);
        frame.add(bsub);
        frame.add(badd);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        badd.addActionListener(this);
        bdiv.addActionListener(this);
        bmul.addActionListener(this);
        bsub.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == badd) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a + b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bsub) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a - b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bmul) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a * b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bdiv) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a / b;
            textField3.setText("" + result);
        }
    }
}