package com.felipe.bankcore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cartão do sistema bancário.
 * Cada cartão pode possuir múltiplas compras no cartão de crédito.
 */
public class Card {
    private static int nextNumber = 1000;
    private double creditLimit = 2000;
    private double availableLimit;
    private int number;
    private Customer owner;

    //cliente pode ter múltiplas compras;lista começa vazia e cresce conforme compras são adicionadas;
    private List<Purchase> shoppingList = new ArrayList<>();

    /**
     * Filtra a lista de compras para adicionar na lista de compras aprovadas.
     * @return lista de compras aprovadas.
     */
    public List<Purchase> getApprovedPurchases(){
        ArrayList<Purchase> listApproved = new ArrayList<>();
        for(Purchase percorreLista: shoppingList){
            if(percorreLista.getStatus() == PurchaseStatus.APPROVED){
                listApproved.add(percorreLista);
            }
        }
        return listApproved;
    }

    /**
     * Filtra a lista de compras para adicionar na lista de compras recusadas.
     * @return lista de compras recusadas.
     */
    public List<Purchase> getDeclinedPurchases(){
        ArrayList<Purchase> listDeclined = new ArrayList<>();
        for(Purchase percorreLista: shoppingList){
            if(percorreLista.getStatus() == PurchaseStatus.DECLINED){
                listDeclined.add(percorreLista);
            }
        }
        return listDeclined;
    }


    /**
     * Cria um novo cartão.
     * O number é gerado automaticamente pela classe e não pode ser definido manualmente.
     *
     * @param owner o dono do cartão.
     */
    public Card(Customer owner){
        this.owner = owner;
        owner.getCards().add(this);
        this.availableLimit = this.creditLimit;

        //pega o valor atual antes de incrementar, pra Numbers começarem em 1;
        this.number = nextNumber;
        nextNumber++;
    }

    /**
     * tenta registrar uma compra no cartão.
     * se o valor da compra couber no limite disponível, ela é aprovada.
     * e o limite é descontado. Caso contrário, é recusado, mas ainda assim
     * fica registrada no histórico de compras do cartão.
     *
     * @param purchase a compra a ser registrada.
     */
    public void addPurchase(Purchase purchase){
        if(purchase.getValue() <= availableLimit){
            purchase.approve();
            shoppingList.add(purchase);
            this.availableLimit -= purchase.getValue();
        }else {
            purchase.decline();
            shoppingList.add(purchase);
            System.out.println("Valor da compra maior do que o limite disponivel");
        }
    }

    public static int getNextNumber() {
        return nextNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getAvailableLimit() {
        return availableLimit;
    }

    public int getNumber() {
        return number;
    }

    public Customer getOwner() {
        return owner;
    }

    /**
     * Retorna a lista de compras do cartão.
     */
    public List<Purchase> getShoppingList() {
        return shoppingList;
    }

    @Override
    public String toString() {
        String result = "Card: " + number + " - creditLimit: " + creditLimit +
                " - availableLimit: " + availableLimit + " - owner: " + owner.getName() + "\n";
        for (Purchase purchase : shoppingList) {
            result += purchase.toString() + "\n";
        }
        return result;
    }
}
