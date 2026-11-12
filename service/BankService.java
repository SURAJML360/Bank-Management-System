package service;

import model.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class BankService {

    // File used to store account data
    private static final String DATA_FILE = "data/accounts.dat";

    // Stores all accounts
    private final ArrayList<Account> accounts = new ArrayList<>();

    // Starting account number
    private int nextAccountNumber = 1001;

    // Constructor
    public BankService() {

        // Load previously saved accounts
        loadData();

        // Generate the next unused account number
        updateNextAccountNumber();
    }

    // Create a new account
    public Account createAccount(
            String name,
            int age,
            String phone,
            String address,
            String accountType,
            String pin) {

        String accountNumber =
                String.valueOf(nextAccountNumber++);

        Account account = new Account(
                accountNumber,
                name,
                age,
                phone,
                address,
                accountType,
                pin,
                0.0
        );

        accounts.add(account);

        return account;
    }

    // Find account by account number
    public Account findAccount(String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    // Verify login
    public Account login(String accountNumber, String pin) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)
                    && account.getPin().equals(pin)) {

                return account;
            }
        }

        return null;
    }

    // Return all accounts
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Save accounts and transaction history
    public boolean saveData() {

        File file = new File(DATA_FILE);
        File parentFolder = file.getParentFile();

        try {

            // Create the data folder if necessary
            if (parentFolder != null
                    && !parentFolder.exists()
                    && !parentFolder.mkdirs()
                    && !parentFolder.isDirectory()) {

                System.err.println(
                        "Could not create the data folder."
                );

                return false;
            }

            // Write all account objects to the file
            try (ObjectOutputStream output =
                         new ObjectOutputStream(
                                 new FileOutputStream(file))) {

                output.writeObject(accounts);
            }

            return true;

        } catch (IOException e) {

            System.err.println(
                    "Could not save bank data: " + e.getMessage()
            );

            return false;
        }
    }

    // Load saved accounts
    private void loadData() {

        File file = new File(DATA_FILE);

        // Nothing to load on the first run
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            Object savedData = input.readObject();

            if (savedData instanceof ArrayList<?>) {

                ArrayList<?> savedAccounts =
                        (ArrayList<?>) savedData;

                for (Object item : savedAccounts) {

                    if (item instanceof Account) {
                        accounts.add((Account) item);
                    }
                }
            }

            System.out.println(
                    "Bank data loaded successfully."
            );

        } catch (IOException | ClassNotFoundException e) {

            System.err.println(
                    "Could not load bank data: "
                            + e.getMessage()
            );
        }
    }

    // Find the next available account number
    private void updateNextAccountNumber() {

        int maximumNumber = 1000;

        for (Account account : accounts) {

            try {

                int currentNumber = Integer.parseInt(
                        account.getAccountNumber()
                );

                if (currentNumber > maximumNumber) {
                    maximumNumber = currentNumber;
                }

            } catch (NumberFormatException e) {

                // Ignore account numbers that are not numeric
            }
        }

        nextAccountNumber = maximumNumber + 1;
    }
}