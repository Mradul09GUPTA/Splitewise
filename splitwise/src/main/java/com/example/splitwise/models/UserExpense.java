package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "userExpnses")

public class UserExpense extends BaseModel {
    @ManyToOne
    private User user;
    private Long amount;

      @ManyToOne
    private Expense expense;
    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;


    /*      1            1
     *  userExpense---->user
     *       M             1
     *       
     *         1             1
     *    userExpense----->expense
     *         M               1
     */
   
}
