package com.soudry.expense_reimbursement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soudry.expense_reimbursement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
