package com.udhaya.expenseTracker.Repository;


import com.udhaya.expenseTracker.Models.Expenses;
import com.udhaya.expenseTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expenses,Long> {
    List<Expenses> findByUser(User user);
    List<Expenses> findAllByOrderByCreatedAtDesc();
}
