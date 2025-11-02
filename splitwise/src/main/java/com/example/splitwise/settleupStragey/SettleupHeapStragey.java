package com.example.splitwise.settleupStragey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.Transcation;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.models.UserExpenseType;

@Component
public class SettleupHeapStragey {
    public List<Transcation> settleup(List<Expense> expenses) {

        Map<User, Long> netBalance = new HashMap<>();
        // Step 1️⃣: Compute each user's net balance
        for (Expense expense : expenses) {

            for (UserExpense userExpense : expense.getUserExpenses()) {
                User user = userExpense.getUser();
                Long amount = userExpense.getAmount();

                if (userExpense.getUserExpenseType() == UserExpenseType.paidBy) {
                    netBalance.put(user, netBalance.getOrDefault(user, (long) 0) + amount);
                } else if (userExpense.getUserExpenseType() == UserExpenseType.hadToPay) {
                    netBalance.put(user, netBalance.getOrDefault(user, (long) 0) - amount);
                }
            }

        }

        PriorityQueue<Map.Entry<User, Long>> creditors = new PriorityQueue<>(
                (a, b) -> Long.compare(b.getValue(), a.getValue()));
        PriorityQueue<Map.Entry<User, Long>> debtors = new PriorityQueue<>(
                (a, b) -> Long.compare(a.getValue(), b.getValue()));

        for (var entry : netBalance.entrySet()) {
            if (entry.getValue() > 0) {
                creditors.add(entry);
            } else {
                debtors.add(entry);
            }
        }
        List<Transcation> transactions = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            // creditor contain user and amount
            var creditor = creditors.poll();
            var debtor = debtors.poll();

            Long settleupAmount = Math.min(creditor.getValue(), -debtor.getValue());
            Transcation transcation = new Transcation();
            transcation.setAmount(settleupAmount);
            transcation.setFromUser(debtor.getKey());
            transcation.setToUser(creditor.getKey());
            transactions.add(transcation);

            Long creditorAmount = creditor.getValue() - settleupAmount;
            Long debitAmount = debtor.getValue() + settleupAmount;

            if (creditorAmount > 0) {
                creditors.add(Map.entry(creditor.getKey(), creditorAmount));
            }
            if (debitAmount > 0) {
                debtors.add(Map.entry(debtor.getKey(), debitAmount));
            }

        }

        return transactions;
    }
}
