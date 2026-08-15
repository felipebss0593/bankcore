package com.felipe.bankcore.model;

/**
 * representa uma conta poupança,
 * que rende o valor do saldo com um ataxa fixa.
 */
public class SavingsAccount extends Account{

    private double taxaFixa = 0.005;// 0,5% ao mês

    /**
     * Cria uma nova conta poupança, repassando o dono para a superclasse Account.
     * @param owner o cliente dono da conta.
     */
    public SavingsAccount(Customer owner) {
        super(owner);
    }

    /**
     * calcula o rendimento da conta,
     * multiplicando o valor do saldo com a taxa fixa.
     * @return resultado do rendimento.
     */
    @Override
    public double calcularRendimento() {
        return getBalance() * taxaFixa;
    }
}
