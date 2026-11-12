package gui;

import model.Transaction;
import model.Account;
import service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TransferFrame extends JFrame {

    private final Account account;
    private final BankService bankService;
    private final Runnable balanceUpdater;

    public TransferFrame(
            Account account,
            BankService bankService,
            Runnable balanceUpdater) {

        this.account = account;
        this.bankService = bankService;
        this.balanceUpdater = balanceUpdater;

        // Frame settings
        setTitle("Transfer Money");
        setSize(520, 500);
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

        // ================= HEADER =================

        JLabel bankName = new JLabel("ROYAL TRUST BANK");

        bankName.setFont(new Font("Arial", Font.BOLD, 25));
        bankName.setForeground(new Color(25, 55, 95));
        bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("TRANSFER MONEY");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(60, 70, 80));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Send money securely to another account");

        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= SENDER INFO =================

        JLabel senderLabel = new JLabel(
                "Sending from: " + account.getName());

        senderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        senderLabel.setForeground(new Color(50, 60, 70));
        senderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel balanceLabel = new JLabel(
                "Available Balance: ₹"
                        + String.format("%.2f", account.getBalance()));

        balanceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        balanceLabel.setForeground(new Color(50, 110, 90));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= RECEIVER FIELD =================

        JLabel receiverLabel = new JLabel(
                "Receiver Account Number");

        receiverLabel.setFont(new Font("Arial", Font.BOLD, 14));
        receiverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField receiverField = new JTextField();

        receiverField.setMaximumSize(new Dimension(280, 38));
        receiverField.setPreferredSize(new Dimension(280, 38));
        receiverField.setFont(new Font("Arial", Font.PLAIN, 15));

        // ================= AMOUNT FIELD =================

        JLabel amountLabel = new JLabel("Transfer Amount (₹)");

        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField amountField = new JTextField();

        amountField.setMaximumSize(new Dimension(280, 38));
        amountField.setPreferredSize(new Dimension(280, 38));
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));

        // ================= BUTTONS =================

        JButton transferButton = new JButton("TRANSFER FUNDS");
        JButton cancelButton = new JButton("CANCEL");

        styleButton(
                transferButton,
                new Color(25, 55, 95),
                Color.WHITE);

        styleButton(
                cancelButton,
                new Color(220, 225, 230),
                Color.BLACK);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 12, 0));

        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(transferButton);
        buttonPanel.add(cancelButton);

        // ================= ADD COMPONENTS =================

        cardPanel.add(bankName);
        cardPanel.add(Box.createVerticalStrut(5));

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(5));

        cardPanel.add(subtitle);
        cardPanel.add(Box.createVerticalStrut(22));

        cardPanel.add(senderLabel);
        cardPanel.add(Box.createVerticalStrut(5));

        cardPanel.add(balanceLabel);
        cardPanel.add(Box.createVerticalStrut(22));

        cardPanel.add(receiverLabel);
        cardPanel.add(Box.createVerticalStrut(6));

        cardPanel.add(receiverField);
        cardPanel.add(Box.createVerticalStrut(15));

        cardPanel.add(amountLabel);
        cardPanel.add(Box.createVerticalStrut(6));

        cardPanel.add(amountField);
        cardPanel.add(Box.createVerticalStrut(25));

        cardPanel.add(buttonPanel);

        mainPanel.add(cardPanel);
        add(mainPanel);

        // ================= TRANSFER ACTION =================

        transferButton.addActionListener(e -> {

            String receiverNumber = receiverField.getText().trim();

            String amountText = amountField.getText().trim();

            // Check empty fields
            if (receiverNumber.isEmpty() || amountText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the receiver account number "
                                + "and transfer amount.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // Find receiver account
            Account receiver = bankService.findAccount(receiverNumber);

            if (receiver == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "The receiver account does not exist.",
                        "Account Not Found",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Prevent transferring to yourself
            if (receiver.getAccountNumber()
                    .equals(account.getAccountNumber())) {

                JOptionPane.showMessageDialog(
                        this,
                        "You cannot transfer money to your own account.",
                        "Invalid Transfer",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // Convert amount into a number
            double amount;

            try {

                amount = Double.parseDouble(amountText);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid transfer amount.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Validate amount
            if (!Double.isFinite(amount) || amount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer amount must be greater than ₹0.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Check available balance
            if (amount > account.getBalance()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient balance!\n\n"
                                + "Available Balance: ₹"
                                + String.format(
                                        "%.2f",
                                        account.getBalance()),
                        "Transfer Failed",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Confirm transfer
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Please confirm your transfer.\n\n"
                            + "Receiver: " + receiver.getName()
                            + "\nAccount Number: "
                            + receiver.getAccountNumber()
                            + "\nTransfer Amount: ₹"
                            + String.format("%.2f", amount)
                            + "\n\nDo you want to continue?",
                    "Confirm Transfer",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            // Withdraw from sender
            boolean success = account.withdraw(amount);

            if (!success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer failed. Please check your balance.",
                        "Transfer Failed",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Deposit into receiver
            receiver.deposit(amount);
            // Record the sender's transaction
            account.addTransaction(
                    new Transaction(
                            "Transfer Sent",
                            amount,
                            account.getBalance(),
                            "To " + receiver.getName()
                                    + " (A/C " + receiver.getAccountNumber() + ")"));

            // Record the receiver's transaction
            receiver.addTransaction(
                    new Transaction(
                            "Transfer Received",
                            amount,
                            receiver.getBalance(),
                            "From " + account.getName()
                                    + " (A/C " + account.getAccountNumber() + ")"));
            boolean saved = bankService.saveData();

            // Update sender's dashboard
            balanceUpdater.run();

            // Show success message
            JOptionPane.showMessageDialog(
                    this,
                    "Transfer completed successfully!\n\n"
                            + "Receiver: " + receiver.getName()
                            + "\nTransferred Amount: ₹"
                            + String.format("%.2f", amount)
                            + "\nYour Remaining Balance: ₹"
                            + String.format(
                                    "%.2f",
                                    account.getBalance()),
                    "Transfer Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
        });

        // Cancel action
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Button styling
    private void styleButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setPreferredSize(new Dimension(165, 42));

        button.setFont(new Font("Arial", Font.BOLD, 13));

        button.setBackground(background);
        button.setForeground(foreground);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
    }
}