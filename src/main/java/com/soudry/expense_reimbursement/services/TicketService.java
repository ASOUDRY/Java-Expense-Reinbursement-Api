package com.soudry.expense_reimbursement.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
    public List<TicketResponse> getUserTickets(String username) {
        User user = userRepo.findByUsername(username);
        List<Ticket> listOfTickets = ticketRepo.findAllBySubmittedBy(user);
          List<TicketResponse> convertedTickets = listOfTickets.stream().map(ticket -> new TicketResponse(ticket))
            .collect(Collectors.toList());
        return convertedTickets;
    }
}