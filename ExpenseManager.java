import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager extends JFrame {

    private JTextField expenseField, categoryField, budgetField;
    private JTextArea expenseLogArea;
    private Map<String, Double> budgets;

    public ExpenseManager() {
        super("Expense Manager");
        budgets = new HashMap<>();

        // GUI Components
        expenseField = new JTextField(10);
        categoryField = new JTextField(10);
        budgetField = new JTextField(10);
        expenseLogArea = new JTextArea(10, 30);

        JButton logButton = new JButton("Log Expense");
        JButton setBudgetButton = new JButton("Set Budget");
        JButton showGraphButton = new JButton("Show Expenses");

        // Layout
        setLayout(new FlowLayout());

        add(new JLabel("Expense:"));
        add(expenseField);
        add(new JLabel("Category:"));
        add(categoryField);
        //add(new JLabel("Budget:"));
        //add(budgetField);
        add(logButton);
        add(setBudgetButton);
        add(showGraphButton);
        add(new JScrollPane(expenseLogArea));

        // Event Listeners
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logExpense();
            }
        });

        setBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSetBudgetFrame();
            }
        });

        showGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExpenses();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    private void logExpense() {
        String expense = expenseField.getText();
        String category = categoryField.getText();

        // Assuming all entered expenses are numeric
        double amount = Double.parseDouble(expense);

        if (budgets.containsKey(category) && budgets.get(category) < amount) {
            JOptionPane.showMessageDialog(this, "Error: Expense exceeds the budget for " + category);
            return;
        }

        String logEntry = "Expense: $" + amount + " | Category: " + category + "\n";
        expenseLogArea.append(logEntry);
    }

    private void showExpenses() {
        // This is a simplified example; in a real application, you might use a charting library for better visualizations
        JOptionPane.showMessageDialog(this, "Showing expense graph - Not implemented in this example");
    }

    private void openSetBudgetFrame() {
         JFrame setBudgetFrame = new JFrame("Set Budget");
        setBudgetFrame.setLayout(new FlowLayout());

        JTextField categoryField = new JTextField(10);
        JTextField budgetField = new JTextField(10);
        JButton setButton = new JButton("Set Budget");

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBudget(categoryField.getText(), Double.parseDouble(budgetField.getText()));
                setBudgetFrame.dispose();
            }
        });

        setBudgetFrame.add(new JLabel("Category:"));
        setBudgetFrame.add(categoryField);
        setBudgetFrame.add(new JLabel("Budget:"));
        setBudgetFrame.add(budgetField);
        setBudgetFrame.add(setButton);

        setBudgetFrame.setSize(300, 150);
        setBudgetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBudgetFrame.setVisible(true);
    }

    private void setBudget(String category, double budget) {
        if (budgets.containsKey(category)) {
            int result = JOptionPane.showConfirmDialog(this, "Category already has a budget. Do you want to update it?",
                    "Budget Confirmation", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                budgets.put(category, budget);
                JOptionPane.showMessageDialog(this, "Budget updated for " + category);
            }
        } else {
            budgets.put(category, budget);
            JOptionPane.showMessageDialog(this, "Budget set for " + category);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseManager();
            }
        });
    }
}
