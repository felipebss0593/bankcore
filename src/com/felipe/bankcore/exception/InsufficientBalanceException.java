package com.felipe.bankcore.exception;

/**
 * representa uma exception personalizada caso o valor do saldo,
 * seja insuficiente para uma transação do tipo saque.
 * return uma message.
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
