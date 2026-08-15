package com.felipe.bankcore.model;

/**
 * Representa um registro de CDI retornado pela API do Banco Central (SGS, série 12).
 * @param data a data de referência do valor informado.
 * @param valor a taxa do CDI naquela data, em formato de porcentagem (String).
 */
public record RegistroCdi(String data, String valor) {
}
