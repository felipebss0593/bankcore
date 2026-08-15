package com.felipe.bankcore.exception;

/**
 * representa uma exception personalizada caso a busca da API,
 * não seja feita por algum erro inesperado.
 * return uma mensagem.
 */
public class CdiApiException extends RuntimeException {
    public CdiApiException(String message) {
        super(message);
    }
}
