package com.example.splitwise.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name="expensess")

public class Expense extends BaseModel {
   private String description;
   private Long amount;
   @ManyToOne
   private User createdByUser;
    @Enumerated(EnumType.ORDINAL)
   private ExpenseType expenseType;
   @ManyToOne
   private Group group;//  if it  not group then group is null 

    @OneToMany(fetch = FetchType.LAZY ,
    mappedBy = "expense",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
   private List<UserExpense> userExpenses;
   

   /* 
    map<user,amount>hadToPay
    map<user,amount> paidBy
    */


    /* 
     * Er diagram 
     *   1              1
     * Expense ------> createdByUser
     *    M              1
     * 
     *   1             1
     * Expense------>group
     *    M             1
     * 
     *    1              M
     * Expense------->Userexpense
     *    1               1
     * 
     */
}
