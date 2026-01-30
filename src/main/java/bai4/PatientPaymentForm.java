package bai4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientPaymentForm extends JFrame {

    private JRadioButton rdoMale, rdoFemale, rdoChild;
    private JTextField txtAge, txtPayment;

    public PatientPaymentForm() {
        setTitle("Calculate the Payment for the Patient");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Radio buttons
        rdoMale = new JRadioButton("Male");
        rdoFemale = new JRadioButton("Female");
        rdoChild = new JRadioButton("Child (0 - 17 years)");

        rdoMale.setBounds(30, 30, 100, 25);
        rdoFemale.setBounds(140, 30, 100, 25);
        rdoChild.setBounds(250, 30, 150, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(rdoMale);
        group.add(rdoFemale);
        group.add(rdoChild);

        add(rdoMale);
        add(rdoFemale);
        add(rdoChild);

        // Age
        JLabel lblAge = new JLabel("Age (Years):");
        lblAge.setBounds(30, 70, 100, 25);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(140, 70, 100, 25);
        add(txtAge);

        // Button
        JButton btnCalc = new JButton("Calculate");
        btnCalc.setBounds(260, 70, 120, 30);
        add(btnCalc);

         // Payment
        JLabel lblPayment = new JLabel("Payment is:");
        lblPayment.setBounds(30, 120, 100, 25);
        add(lblPayment);

        txtPayment = new JTextField();
        txtPayment.setBounds(140, 120, 100, 25);
        txtPayment.setEditable(false);
        add(txtPayment);

        JLabel lblEuro = new JLabel("euro €");
        lblEuro.setBounds(250, 120, 60, 25);
        add(lblEuro);


        // Action
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePayment();
            }
        });
    }

    private void calculatePayment() {
        try {
            int age = Integer.parseInt(txtAge.getText());

            if (age < 0 || age > 145) {
                showError("Age is not valid");
                return;
            }

//            int payment;

//            if (rdoChild.isSelected()) {
//                if (age > 17) {
//                    showError("Child age must be from 0 to 17");
//                    return;
//                }
//                payment = 50;
//            }
//            else if (rdoMale.isSelected()) {
//                if (age < 18) {
//                    showError("Male age must be >= 18");
//                    return;
//                }
//                if (age <= 35) payment = 100;
//                else if (age <= 50) payment = 120;
//                else payment = 140;
//            }
//            else if (rdoFemale.isSelected()) {
//                if (age < 18) {
//                    showError("Female age must be >= 18");
//                    return;
//                }
//                if (age <= 35) payment = 80;
//                else if (age <= 50) payment = 110;
//                else payment = 140;
//            }
//            else {
//                showError("Please select gender or child");
//                return;
//            }
//
//            txtPayment.setText(String.valueOf(payment));
            String type;

            if (rdoChild.isSelected()) {
                type = "CHILD";
            } else if (rdoMale.isSelected()) {
                type = "MALE";
            } else if (rdoFemale.isSelected()) {
                type = "FEMALE";
            } else {
                JOptionPane.showMessageDialog(this, "Please select patient type");
                return;
            }

            int payment = PaymentCalculator.calculate(type, age);
            txtPayment.setText(String.valueOf(payment));


        } catch (NumberFormatException ex) {
            showError("Please enter a valid number");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new PatientPaymentForm().setVisible(true);
    }
}
