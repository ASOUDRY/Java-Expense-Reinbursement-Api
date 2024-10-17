package com.soudry.expense_reimbursement.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Request.TicketRequest;
import com.soudry.expense_reimbursement.DTO.Response.TicketResponse;
import com.soudry.expense_reimbursement.repository.TicketRepository;
import com.soudry.expense_reimbursement.repository.UserRepository;
import com.soudry.expense_reimbursement.entities.Ticket;
import com.soudry.expense_reimbursement.entities.User;

@Service
public class TicketService {

    TicketRepository ticketRepo;
    UserRepository userRepo;

    @Autowired
    TicketService(TicketRepository ticketRepo, UserRepository userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    public TicketResponse submitTicket(TicketRequest request) {
        Optional<User> option = userRepo.findById(request.getSubmitterId());
        if (option.isPresent()) {
            User user = option.get();
            Ticket ticket = new Ticket(user, request.getTicketIssue(), request.getAmountToBeReinbursed());
            ticketRepo.save(ticket);
            return new TicketResponse(ticket);
        } else {
            return null;
        }
    }   
}