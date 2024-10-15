package com.soudry.expense_reimbursement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soudry.expense_reimbursement.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    
}