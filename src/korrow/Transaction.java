/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Dell
 */
public class Transaction {

    private long transactionId;
    private String itemId;
    private long amount;
    private byte statusId;
    private HashMap<Byte, String> status;
    private Timestamp borrowDate;
    private Timestamp returnDate;
    private String note;
    private Timestamp lastUpdate;
    private String borrowerId;
    private String lenderId;

    public Transaction() {

    }

    public Transaction(long transactionId, String item, long amount, byte statusId,
            HashMap<Byte, String> status, Timestamp borrowDate, Timestamp returnDate,
            String note, Timestamp lastUpdate, String borrowerId, String lenderId, String itemId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.statusId = statusId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.note = note;
        this.lastUpdate = lastUpdate;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTranactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public byte getStatusId() {
        return statusId;
    }

    public void setStatusId(byte statusId) {
        
        this.statusId = statusId;
    }

    public HashMap<Byte, String> getStatus() {
        return status;
    }

    public void setStatus(HashMap hmStatus) {
        this.status = hmStatus;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getLenderId() {
        return lenderId;
    }

    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    public static void getTransactionById(String id) {

    }

    public static void getTransactionByStatus() {

    }

    public static void getTransactionByUserId(String userId) {

    }

    public static void getTransactionByUserIdAndStatus(String userId, String status) {

    }

    public static boolean createNewTransaction() {
        boolean result = false;
        return result;
    }

    public boolean saveToDB() {
        boolean result = false;
        return result;
    }

}
