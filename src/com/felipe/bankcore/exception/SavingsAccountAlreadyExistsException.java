package com.felipe.bankcore.exception;

/**
 * representa uma exception personalizada caso um cliente,
 * já tenha uma conta poupança.
 */
public class SavingsAccountAlreadyExistsException extends RuntimeException {
    /**
     * @param message a mensagem explicando o erro.
     */
    public SavingsAccountAlreadyExistsException(String message) {
        super(message);
    }
}
