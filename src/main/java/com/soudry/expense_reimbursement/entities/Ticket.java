package com.soudry.expense_reimbursement.entities;

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
    
}
