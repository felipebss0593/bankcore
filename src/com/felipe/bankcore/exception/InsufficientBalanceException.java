package com.felipe.bankcore.exception;

/**
 * representa uma exception personalizada caso o valor do saldo,
 * seja insuficiente para uma transação do tipo saque.
 */
public class InsufficientBalanceException extends RuntimeException {
    /**
     * @param message a mensagem explicando o erro.
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
