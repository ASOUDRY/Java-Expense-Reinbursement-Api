package com.soudry.expense_reimbursement.DTO.Request;
public class TicketApprovalRequest {

    private String idString;
    private Boolean approval;

    public String getidString() {
        return this.idString;
    }

    public void setidString(String id) {
        this.idString = id;
    }

    public Boolean getApproval() {
        return this.approval;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }
}
