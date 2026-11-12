package gui;

import model.Account;
import model.Transaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TransactionFrame extends JFrame {

    private final Account account;

    private DefaultTableModel tableModel;
    private JLabel countLabel;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public TransactionFrame(Account account) {

        this.account = account;

        setTitle("Transaction History - Royal Trust Bank");
        setSize(900, 560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main background
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(new Color(240, 244, 248));

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout(10, 5));

        headerPanel.setBackground(new Color(25, 55, 95));

        headerPanel.setBorder(
                new EmptyBorder(18, 22, 18, 22)
        );

        JPanel headingPanel = new JPanel();

        headingPanel.setLayout(
                new BoxLayout(headingPanel, BoxLayout.Y_AXIS)
        );

        headingPanel.setBackground(new Color(25, 55, 95));

        JLabel bankName = new JLabel("ROYAL TRUST BANK");

        bankName.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        bankName.setForeground(Color.WHITE);

        JLabel title = new JLabel("TRANSACTION HISTORY");

        title.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        title.setForeground(new Color(220, 230, 240));

        headingPanel.add(bankName);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(title);

        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(
                new BoxLayout(accountPanel, BoxLayout.Y_AXIS)
        );

        accountPanel.setBackground(new Color(25, 55, 95));

        JLabel customerLabel = new JLabel(
                "Customer: " + account.getName()
        );

        customerLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        customerLabel.setForeground(Color.WHITE);

        JLabel accountNumberLabel = new JLabel(
                "Account No: " + account.getAccountNumber()
        );

        accountNumberLabel.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        accountNumberLabel.setForeground(
                new Color(220, 230, 240)
        );

        accountPanel.add(customerLabel);

        accountPanel.add(
                Box.createVerticalStrut(5)
        );

        accountPanel.add(accountNumberLabel);

        headerPanel.add(
                headingPanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                accountPanel,
                BorderLayout.EAST
        );

        // ================= TABLE CARD =================

        JPanel tableCard = new JPanel(new BorderLayout(0, 12));

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(
                new EmptyBorder(18, 18, 18, 18)
        );

        JLabel tableTitle = new JLabel(
                "Your Banking Activity"
        );

        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        tableTitle.setForeground(
                new Color(40, 55, 75)
        );

        JLabel tableSubtitle = new JLabel(
                "View your deposits, withdrawals and transfers."
        );

        tableSubtitle.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        tableSubtitle.setForeground(Color.GRAY);

        JPanel tableHeadingPanel = new JPanel();

        tableHeadingPanel.setLayout(
                new BoxLayout(tableHeadingPanel, BoxLayout.Y_AXIS)
        );

        tableHeadingPanel.setBackground(Color.WHITE);

        tableHeadingPanel.add(tableTitle);

        tableHeadingPanel.add(
                Box.createVerticalStrut(5)
        );

        tableHeadingPanel.add(tableSubtitle);

        // Table columns
        String[] columns = {
                "Date & Time",
                "Type",
                "Description",
                "Amount",
                "Balance After"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable transactionTable = new JTable(tableModel);

        transactionTable.setRowHeight(32);

        transactionTable.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        transactionTable.setForeground(
                new Color(45, 55, 70)
        );

        transactionTable.setBackground(Color.WHITE);

        transactionTable.setSelectionBackground(
                new Color(220, 232, 245)
        );

        transactionTable.setSelectionForeground(Color.BLACK);

        transactionTable.setGridColor(
                new Color(230, 235, 240)
        );

        transactionTable.setShowVerticalLines(false);

        transactionTable.setAutoCreateRowSorter(true);

        transactionTable.setFillsViewportHeight(true);

        transactionTable.setIntercellSpacing(
                new Dimension(0, 1)
        );

        JTableHeader tableHeader =
                transactionTable.getTableHeader();

        tableHeader.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        tableHeader.setBackground(
                new Color(25, 55, 95)
        );

        tableHeader.setForeground(Color.WHITE);

        tableHeader.setPreferredSize(
                new Dimension(100, 38)
        );

        tableHeader.setReorderingAllowed(false);

        // Column widths
        transactionTable.getColumnModel()
                .getColumn(0).setPreferredWidth(155);

        transactionTable.getColumnModel()
                .getColumn(1).setPreferredWidth(115);

        transactionTable.getColumnModel()
                .getColumn(2).setPreferredWidth(280);

        transactionTable.getColumnModel()
                .getColumn(3).setPreferredWidth(130);

        transactionTable.getColumnModel()
                .getColumn(4).setPreferredWidth(140);

        JScrollPane scrollPane =
                new JScrollPane(transactionTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 225, 230)
                )
        );

        // ================= FOOTER =================

        countLabel = new JLabel("Total Transactions: 0");

        countLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        countLabel.setForeground(
                new Color(70, 80, 95)
        );

        JButton refreshButton = new JButton("REFRESH");

        JButton closeButton = new JButton("CLOSE");

        styleButton(
                refreshButton,
                new Color(50, 110, 90),
                Color.WHITE
        );

        styleButton(
                closeButton,
                new Color(220, 225, 230),
                Color.BLACK
        );

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0)
        );

        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        JPanel footerPanel = new JPanel(new BorderLayout());

        footerPanel.setBackground(Color.WHITE);

        footerPanel.add(
                countLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        // Assemble table card
        tableCard.add(
                tableHeadingPanel,
                BorderLayout.NORTH
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        tableCard.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        // Assemble main screen
        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // Load transactions
        refreshTransactions();

        // Refresh button
        refreshButton.addActionListener(e -> refreshTransactions());

        // Close button
        closeButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Load account transactions into the JTable
    private void refreshTransactions() {

        tableModel.setRowCount(0);

        for (Transaction transaction : account.getTransactions()) {

            String amountText;

            // Withdrawals and sent transfers reduce the balance
            if (transaction.getType().equals("Withdrawal")
                    || transaction.getType().equals("Transfer Sent")) {

                amountText = "- ₹" + String.format(
                        "%.2f",
                        transaction.getAmount()
                );

            } else {

                amountText = "+ ₹" + String.format(
                        "%.2f",
                        transaction.getAmount()
                );
            }

            String balanceText = "₹" + String.format(
                    "%.2f",
                    transaction.getBalanceAfter()
            );

            tableModel.addRow(
                    new Object[]{
                            transaction.getDateTime()
                                    .format(dateFormatter),

                            transaction.getType(),

                            transaction.getDescription(),

                            amountText,

                            balanceText
                    }
            );
        }

        countLabel.setText(
                "Total Transactions: "
                        + account.getTransactions().size()
        );
    }

    // Button styling
    private void styleButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setPreferredSize(
                new Dimension(120, 38)
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        button.setBackground(background);

        button.setForeground(foreground);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}
