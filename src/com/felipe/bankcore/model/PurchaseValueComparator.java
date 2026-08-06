package com.felipe.bankcore.model;

import java.util.Comparator;

/**
 * Compara duas compras pelo valor.
 * Permite ordenar o hitórico por maior ou menor compra.
 *
 */
public class PurchaseValueComparator implements Comparator<Purchase> {

    @Override
    public int compare(Purchase p1, Purchase p2) {
        return Double.compare(p1.getValue(), p2.getValue());
    }
}
