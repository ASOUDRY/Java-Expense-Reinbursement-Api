package com.soudry.expense_reimbursement.DTO.Request;

public class TicketRequest {
    private String submitterId  = "";
    private String ticketIssue = "";
    private double amountToBeReinbursed;

    public void setSubmitterId(String id) {
        this.submitterId = id;
    };
    public String getSubmitterId() {
         return this.submitterId;
    };
    public void setTicketIssue(String issue) {
        this.ticketIssue = issue;
    };
    public String getTicketIssue() {
        return this.ticketIssue;
   };
    public void setAmountToBeReinbursed(double amount) {
        this.amountToBeReinbursed = amount;
    };
    public double getAmountToBeReinbursed() {
        return this.amountToBeReinbursed;
   };
}