package com.soudry.expense_reimbursement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soudry.expense_reimbursement.entities.Ticket;
import com.soudry.expense_reimbursement.entities.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findAllBySubmittedBy(User user);
}