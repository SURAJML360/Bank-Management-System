package gui;

import model.Account;
import service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final BankService bankService;

    public LoginFrame(BankService bankService) {

        this.bankService = bankService;

        setTitle("Bank Login");
        setSize(500, 400);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        mainPanel.setBackground(
                new Color(240, 244, 248));

        JPanel loginPanel = new JPanel();

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS));

        loginPanel.setBackground(Color.WHITE);

        loginPanel.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        30,
                        40));

        JLabel title = new JLabel("BANK LOGIN");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26));

        title.setForeground(
                new Color(30, 60, 100));

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Enter your account details");

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14));

        subtitle.setForeground(Color.GRAY);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel accountLabel = new JLabel("Account Number");

        accountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        accountLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JTextField accountField = new JTextField();

        accountField.setMaximumSize(
                new Dimension(280, 35));

        accountField.setPreferredSize(
                new Dimension(280, 35));

        accountField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15));

        JLabel pinLabel = new JLabel("PIN");

        pinLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        pinLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JPasswordField pinField = new JPasswordField();

        pinField.setMaximumSize(
                new Dimension(280, 35));

        pinField.setPreferredSize(
                new Dimension(280, 35));

        pinField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15));

        JButton loginButton = new JButton("LOGIN");

        JButton cancelButton = new JButton("CANCEL");

        styleButton(
                loginButton,
                new Color(30, 60, 100),
                Color.WHITE);

        styleButton(
                cancelButton,
                new Color(220, 225, 230),
                Color.BLACK);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        0));

        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        loginPanel.add(title);
        loginPanel.add(
                Box.createVerticalStrut(5));

        loginPanel.add(subtitle);

        loginPanel.add(
                Box.createVerticalStrut(25));

        loginPanel.add(accountLabel);

        loginPanel.add(
                Box.createVerticalStrut(5));

        loginPanel.add(accountField);

        loginPanel.add(
                Box.createVerticalStrut(15));

        loginPanel.add(pinLabel);

        loginPanel.add(
                Box.createVerticalStrut(5));

        loginPanel.add(pinField);

        loginPanel.add(
                Box.createVerticalStrut(25));

        loginPanel.add(buttonPanel);

        mainPanel.add(loginPanel);

        add(mainPanel);

        // LOGIN
        loginButton.addActionListener(e -> {

            String accountNumber = accountField.getText().trim();

            String pin = new String(
                    pinField.getPassword());

            if (accountNumber.isEmpty()
                    || pin.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Account Number and PIN.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            Account account = bankService.login(
                    accountNumber,
                    pin);

            if (account != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!\n\n"
                                + "Welcome, "
                                + account.getName(),
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

                new DashboardFrame(account, bankService);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Account Number or PIN.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // CANCEL
        cancelButton.addActionListener(
                e -> dispose());

        setVisible(true);
    }

    private void styleButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setPreferredSize(
                new Dimension(125, 38));

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        button.setBackground(background);
        button.setForeground(foreground);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}