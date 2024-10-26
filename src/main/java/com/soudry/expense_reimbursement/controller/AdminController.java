package com.soudry.expense_reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soudry.expense_reimbursement.DTO.Request.TicketApprovalRequest;
import com.soudry.expense_reimbursement.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/deleteTicket/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable String ticketId) {
        adminService.deleteTicket(ticketId);
        return ResponseEntity.ok(ticketId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok("Your user has been deleted");
    }

    @PatchMapping("/processTicket")
    public ResponseEntity<String> processTicket(@RequestBody TicketApprovalRequest request) {
        Boolean isApproved = adminService.processTicket(request);
        if (isApproved) {
            return ResponseEntity.ok("Ticket Approved");
        } else {
            return ResponseEntity.ok("Ticket Rejected");
        }
        
    }
    @PatchMapping("/promoteUser/{username}")
    public ResponseEntity<String> promoteUser(@PathVariable String username) {
        adminService.promoteUser(username);
        return ResponseEntity.ok("User Promoted");
    }
}