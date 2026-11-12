package gui;

import model.Transaction;
import model.Account;
import service.BankService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WithdrawFrame extends JFrame {

        private final Account account;
        private final Runnable balanceUpdater;
        private final BankService bankService;

        public WithdrawFrame(
        Account account,
        BankService bankService,
        Runnable balanceUpdater) {

                this.account = account;
this.bankService = bankService;
this.balanceUpdater = balanceUpdater;

                setTitle("Withdraw Money");
                setSize(480, 400);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);

                // Main background
                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBackground(new Color(240, 244, 248));

                // White card
                JPanel cardPanel = new JPanel();
                cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
                cardPanel.setBackground(Color.WHITE);
                cardPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

                // Bank name
                JLabel bankName = new JLabel("ROYAL TRUST BANK");
                bankName.setFont(new Font("Arial", Font.BOLD, 24));
                bankName.setForeground(new Color(25, 55, 95));
                bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Title
                JLabel title = new JLabel("WITHDRAW MONEY");
                title.setFont(new Font("Arial", Font.BOLD, 20));
                title.setForeground(new Color(60, 70, 80));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Subtitle
                JLabel subtitle = new JLabel("Withdraw money from your account");
                subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
                subtitle.setForeground(Color.GRAY);
                subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Current balance
                JLabel currentBalance = new JLabel(
                                "Current Balance: ₹"
                                                + String.format("%.2f", account.getBalance()));

                currentBalance.setFont(
                                new Font("Arial", Font.BOLD, 16));

                currentBalance.setForeground(
                                new Color(180, 90, 50));

                currentBalance.setAlignmentX(
                                Component.CENTER_ALIGNMENT);

                // Amount label
                JLabel amountLabel = new JLabel("Enter Amount");

                amountLabel.setFont(
                                new Font("Arial", Font.BOLD, 14));

                amountLabel.setAlignmentX(
                                Component.CENTER_ALIGNMENT);

                // Amount field
                JTextField amountField = new JTextField();

                amountField.setMaximumSize(
                                new Dimension(260, 38));

                amountField.setPreferredSize(
                                new Dimension(260, 38));

                amountField.setFont(
                                new Font("Arial", Font.PLAIN, 16));

                // Buttons
                JButton withdrawButton = new JButton("WITHDRAW");
                JButton cancelButton = new JButton("CANCEL");

                styleButton(
                                withdrawButton,
                                new Color(180, 90, 50),
                                Color.WHITE);

                styleButton(
                                cancelButton,
                                new Color(220, 225, 230),
                                Color.BLACK);

                JPanel buttonPanel = new JPanel(
                                new FlowLayout(
                                                FlowLayout.CENTER,
                                                12,
                                                0));

                buttonPanel.setBackground(Color.WHITE);

                buttonPanel.add(withdrawButton);
                buttonPanel.add(cancelButton);

                // Add components
                cardPanel.add(bankName);
                cardPanel.add(Box.createVerticalStrut(5));

                cardPanel.add(title);
                cardPanel.add(Box.createVerticalStrut(5));

                cardPanel.add(subtitle);
                cardPanel.add(Box.createVerticalStrut(25));

                cardPanel.add(currentBalance);
                cardPanel.add(Box.createVerticalStrut(25));

                cardPanel.add(amountLabel);
                cardPanel.add(Box.createVerticalStrut(7));

                cardPanel.add(amountField);
                cardPanel.add(Box.createVerticalStrut(25));

                cardPanel.add(buttonPanel);

                mainPanel.add(cardPanel);
                add(mainPanel);

                // Withdraw action
                withdrawButton.addActionListener(e -> {

                        String amountText = amountField.getText().trim();

                        if (amountText.isEmpty()) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Please enter an amount.",
                                                "Input Required",
                                                JOptionPane.WARNING_MESSAGE);

                                return;
                        }

                        double amount;

                        try {

                                amount = Double.parseDouble(amountText);

                        } catch (NumberFormatException ex) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Please enter a valid amount.",
                                                "Invalid Amount",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        if (amount <= 0) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Amount must be greater than ₹0.",
                                                "Invalid Amount",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // Check sufficient balance
                        if (amount > account.getBalance()) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Insufficient balance!\n\n"
                                                                + "Available Balance: ₹"
                                                                + String.format(
                                                                                "%.2f",
                                                                                account.getBalance()),
                                                "Withdrawal Failed",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // Withdraw money
                        boolean success = account.withdraw(amount);

                        if (success) {
                                // Record the transaction
                                account.addTransaction(
                                                new Transaction(
                                                                "Withdrawal",
                                                                amount,
                                                                account.getBalance(),
                                                                "Cash withdrawal"));
                                boolean saved = bankService.saveData();

                                // Update dashboard balance
                                balanceUpdater.run();

                                JOptionPane.showMessageDialog(
                                                this,
                                                "₹" + String.format("%.2f", amount)
                                                                + " withdrawn successfully!\n\n"
                                                                + "Remaining Balance: ₹"
                                                                + String.format(
                                                                                "%.2f",
                                                                                account.getBalance()),
                                                "Withdrawal Successful",
                                                JOptionPane.INFORMATION_MESSAGE);

                                dispose();
                        }
                });

                // Cancel
                cancelButton.addActionListener(
                                e -> dispose());

                setVisible(true);
        }

        private void styleButton(
                        JButton button,
                        Color background,
                        Color foreground) {

                button.setPreferredSize(
                                new Dimension(125, 40));

                button.setFont(
                                new Font("Arial", Font.BOLD, 13));

                button.setBackground(background);
                button.setForeground(foreground);

                button.setFocusPainted(false);
                button.setBorderPainted(false);
        }
}