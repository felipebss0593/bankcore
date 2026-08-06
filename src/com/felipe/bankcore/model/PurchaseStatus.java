package com.felipe.bankcore.model;

/**
 * Representa os possíveis status de uma compra no cartão de crédito.
 * Toda compra nasce como PENDING e é avaliada pelo Card,
 * podendo se tornar APPROVED ou DECLINED.
 */
public enum PurchaseStatus {
    PENDING,
    APPROVED,
    DECLINED
}
