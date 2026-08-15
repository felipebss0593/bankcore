package com.felipe.bankcore.model;

import com.felipe.bankcore.exception.InsufficientBalanceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * representa uma conta bancária.
 * possui saldo, dono e uma lista de transações.
 * possui métodos deposito, saque e calcular rendimento.
 */

public abstract class Account {
    private double balance;
    private Customer owner;
    private List<Transaction> transactionList = new ArrayList<>();

    public abstract double calcularRendimento();

    /**
     * cria um depósito
     * @param amount é o valor do depósito.
     * soma o valor do saldo com o valor do depósito.
     * cria uma transação.
     * é adicionado na lista de transações.
     */
    public void deposit(double amount){
        balance += amount;
        Transaction transaction = new Transaction(amount, LocalDate.now(), TransactionType.DEPOSITO);
        transactionList.add(transaction);
    }

    /**
     * cria um saque.
     * @param amount é o valor do saque.
     * se o valor do saldo for maior que o saque, subtrai o valor do saldo com o valor do saque.
     * cria uma transação.
     * é adicionado na lista de transações.
     * se caso o saldo for menor que o saque, a exception @param InsufficientBalanceException é acionada.
     */
    public void withdraw(double amount){
        if( amount < balance){
            balance -= amount;
            Transaction transaction = new Transaction(amount, LocalDate.now(), TransactionType.SAQUE);
            transactionList.add(transaction);
        }else {
            throw new InsufficientBalanceException("O valor do saque é maior do que o saldo!");
        }
    }

    /**
     * Cria uma nova conta, vinculando o cliente dono no momento da criação.
     * @param owner o cliente dono da conta.
     */
    public Account(Customer owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
