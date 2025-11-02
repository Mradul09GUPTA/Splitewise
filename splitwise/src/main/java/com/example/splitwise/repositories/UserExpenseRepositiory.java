package com.example.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
@Repository
public interface UserExpenseRepositiory extends JpaRepository<UserExpense,Long> {
       List<UserExpense>findByUser(User user);
}
