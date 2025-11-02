package com.example.splitwise.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.splitwise.exceptions.UserException;
import com.example.splitwise.exceptions.UserExpenseException;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.Transcation;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.UserExpenseRepositiory;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.settleupStragey.SettleupHeapStragey;

@Service
public class settleupServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserExpenseRepositiory userExpenseRepositiory;
    @Autowired
    ExpenseRepository expenseRepository;

    public List<Transcation> settleupUser(long userid) throws UserException, UserExpenseException {
        User user = userRepository.findById(userid).orElseThrow(() -> new UserException("Invaild User"));

        List<UserExpense> userExpenses = userExpenseRepositiory.findByUser(user);

        if (userExpenses.isEmpty()) {
            throw new UserExpenseException("User has no Expense");
        }

        Set<Expense> expenses = null;

        for (UserExpense userExpense : userExpenses) {
            expenses.add(userExpense.getExpense());
        }

        List<Expense> expenseList = expenses.stream().toList();

        SettleupHeapStragey settleupHeapStragey = new SettleupHeapStragey();
        return settleupHeapStragey.settleup(expenseList) ;

    }
    
    public List<Transcation> settleupGroup(long groupid) throws UserException, UserExpenseException {
        
        
        
        List<Expense> expenseList = expenseRepository.findByGroupId(groupid);

        SettleupHeapStragey settleupHeapStragey = new SettleupHeapStragey();
        return settleupHeapStragey.settleup(expenseList) ;

    }
    

}
