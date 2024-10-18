package com.soudry.expense_reimbursement.entities;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    private String ticketId = "";

    @ManyToOne
    @JoinColumn(name = "userId")
    private User submittedBy;

    @Column
    private String issue = "";

    @Column
    private double amountToBeReinbursed = 0.00;

    @Column
    private Boolean handled = false;

    @Column
    private Boolean approved = false;

    public Ticket(User user, String issue, double amount) {
        this.ticketId = UUID.randomUUID().toString();
        this.submittedBy = user;
        this.issue = issue;
        this.amountToBeReinbursed = amount;
    }

    public Ticket() {
        
    }

    public String getTicketId() {
        return this.ticketId;
    };

    public User getsubmittedBy() {
        return this.submittedBy;
    };

    public String getIssue() {
        return this.issue;
    };

    public double getAmountToBeReinbursed() {
       return this.amountToBeReinbursed;
    };

    public Boolean getHandled() {
        return this.handled;
    };
}