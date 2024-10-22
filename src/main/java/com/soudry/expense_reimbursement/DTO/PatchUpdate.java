package com.soudry.expense_reimbursement.DTO;

public class PatchUpdate {
    private String ticketId = null;
    private String issue = null;
    private Double amount = null;  // Corrected to Double instead of double, as primitives cannot be null.
    private Boolean handled = null;
    private Boolean approved = null;

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmountToBeReimbursed(Double amount) {
        this.amount = amount;
    }

    public Boolean getHandled() {
        return handled;
    }

    public void setHandled(Boolean handled) {
        this.handled = handled;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}

