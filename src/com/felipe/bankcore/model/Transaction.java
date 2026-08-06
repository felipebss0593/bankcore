package com.felipe.bankcore.model;

import java.time.LocalDate;

public class Transaction {
    private static int nextId = 1;
    private int id;
    private double value;
    private LocalDate date;
    private TransactionType type;

    public Transaction(double value, LocalDate date, TransactionType type) {
        this.value = value;
        this.date = date;
        this.type = type;

        //pega o valor atual antes de incrementar, pra IDs começarem em 1;
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
