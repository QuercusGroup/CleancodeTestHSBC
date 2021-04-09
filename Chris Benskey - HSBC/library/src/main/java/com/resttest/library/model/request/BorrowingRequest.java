package com.resttest.library.model.request;

import com.resttest.library.model.entity.BorrowingStatus;

/**
 * Request body for borrowing a book
 */
public class BorrowingRequest {

    private BorrowingStatus status;

    private String borrowerName;

    public BorrowingStatus getStatus() {
        return status;
    }

    public void setStatus(final BorrowingStatus status) {
        this.status = status;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(final String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public String toString() {
        return "BorrowingRequest{" +
                "status=" + status +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}
