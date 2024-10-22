package com.soudry.expense_reimbursement.DTO.Response;

import com.soudry.expense_reimbursement.entities.Ticket;
import com.soudry.expense_reimbursement.entities.User;

public class TicketResponse {

    private String issue = "";
    private double amount = 0.00;
    private User submittedBy;
    private boolean handled = false;

    public TicketResponse(Ticket ticket) {
        this.issue = ticket.getIssue();
        this.amount = ticket.getAmount();
        this.submittedBy = ticket.getsubmittedBy();
        this.handled = ticket.getHandled();  
    }

    public User getsubmittedBy() {
        return this.submittedBy;
    };

    public String getIssue() {
        return this.issue;
    };

    public double getAmountToBeReinbursed() {
       return this.amount;
    };

    public Boolean getHandled() {
        return this.handled;
    };
}