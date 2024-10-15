package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.soudry.expense_reimbursement.repository.TicketRepository;

public class TicketService {

    TicketRepository ticketRepo;

    @Autowired
    TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public TicketResponse submitTicket(TicketRequest ticket) {
        ticketRepo.save(null)
    }
    
}
