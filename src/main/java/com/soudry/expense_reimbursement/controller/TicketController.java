package com.soudry.expense_reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soudry.expense_reimbursement.DTO.Request.TicketRequest;
import com.soudry.expense_reimbursement.DTO.Response.TicketResponse;
import com.soudry.expense_reimbursement.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/submitTicket")
    public ResponseEntity<TicketResponse> submitTicket(@RequestBody TicketRequest request) {
        TicketResponse response = ticketService.submitTicket(request);
        return ResponseEntity.ok(response);
    }
}