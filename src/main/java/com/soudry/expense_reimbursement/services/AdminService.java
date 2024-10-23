package com.soudry.expense_reimbursement.services;

import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Request.TicketApprovalRequest;
import com.soudry.expense_reimbursement.entities.Ticket;
import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.TicketRepository;
import com.soudry.expense_reimbursement.repository.UserRepository;

@Service
public class AdminService {

      private final UserRepository userRepo;
      private final TicketRepository ticketRepo;

      public AdminService(
      UserRepository userRepo, 
      TicketRepository ticketRepo) {
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
      } 

      public Boolean processTicket(TicketApprovalRequest approval) {
        Ticket ticket = ticketRepo.findById(approval.getidString()).get();
        ticket.setApproved(approval.getApproval());
        ticket.setHandled(true);
        ticketRepo.save(ticket);
        return approval.getApproval();
      }

      public void promoteUser(String username) {
        User user = userRepo.findByUsername(username);
        user.setRole(true);
        userRepo.save(user);
      }

      public void deleteTicket(String ticket) {
        ticketRepo.deleteById(ticket);
      }
      public void deleteUser(String user) {
        userRepo.deleteById(user);
      }
}