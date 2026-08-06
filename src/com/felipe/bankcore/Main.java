package com.felipe.bankcore;
import com.felipe.bankcore.model.Card;
import com.felipe.bankcore.model.Customer;
import com.felipe.bankcore.model.Purchase;
import com.felipe.bankcore.model.PurchaseValueComparator;
import com.felipe.bankcore.service.Bank;

import java.util.Collections;
import java.util.Scanner;

/**
 * Ponto de entrada do sistema de cartão de crédito.
 * Exibe o menu interativo e direciona para as operações do sistema.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        int option = 1;

        while(option != 0){
            System.out.println("=== SISTEMA DE CARTÃO DE CRÉDITO ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar cartão para um cliente");
            System.out.println("3 - Cadastrar compra");
            System.out.println("4 - Ver compras aprovadas");
            System.out.println("5 - Ver compras recusadas");
            System.out.println("6 - Ver histórico completo (ordenado por data)");
            System.out.println("7 - Ver maior compra");
            System.out.println("8 - Ver menor compra");
            System.out.println("9 - Ver valor total gasto por cliente");
            System.out.println("10 - Ver limite disponível de um cartão");
            System.out.println("0 - Sair");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: {
                    System.out.println("Digite o nome do cliente:");
                    String name = scanner.nextLine();
                    Customer customer = bank.addCustomer(name);
                    System.out.println("Cliente cadastrado com sucesso!");
                    System.out.println(customer);
                    break;
                }
                case 2: {
                    System.out.println("Qual o Id do cliente:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Cliente não existe.");
                    }else{
                        Card card = new Card(customer);
                        System.out.println("Cartão criado com sucesso.");
                        System.out.println(card);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Qual o Id do cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Cliente não existe");
                    }else{
                       if(customer.getCards().isEmpty()){
                           System.out.println("Este cliente não tem cartão.");
                       }else{
                           for(Card card: customer.getCards()){
                               System.out.println(card);
                           }
                           System.out.println("Qual número de cartão o usuário quer usar ? ");
                           int number = scanner.nextInt();
                           scanner.nextLine();
                           Card selectedCard = customer.findCardByNumber(number);
                           if(selectedCard == null){
                               System.out.println("Cartão não encontrado.");
                           }else{
                               System.out.println("Qual o valor da compra ? ");
                               double value = scanner.nextDouble();
                               scanner.nextLine();
                               System.out.println("Qual a descrição da compra ? ");
                               String description = scanner.nextLine();
                               System.out.println("Qual o número de parcelas ?");
                               int installments = scanner.nextInt();
                               scanner.nextLine();
                               Purchase purchase = new Purchase(value,description,installments);
                               selectedCard.addPurchase(purchase);
                               System.out.println(purchase);
                           }
                       }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if (customer == null) {
                        System.out.println("Cliente não existe.");
                    } else {
                        if (customer.getCards().isEmpty()) {
                            System.out.println("Este cliente não tem cartão.");
                        } else {
                            for (Card card : customer.getCards()) {
                                System.out.println(card);
                            }
                            System.out.println("Qual número de cartão o usuário quer usar ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if (selectedCard == null) {
                                System.out.println("Cartão não encontrado");
                            } else {
                                if (selectedCard.getApprovedPurchases().isEmpty()) {
                                    System.out.println("Não existe compras aprovadas nesse cartão");
                                } else {
                                    for (Purchase approvedPurchase : selectedCard.getApprovedPurchases()) {
                                        System.out.println(approvedPurchase);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if (customer == null) {
                        System.out.println("Cliente não existe.");
                    } else {
                        if (customer.getCards().isEmpty()) {
                            System.out.println("Este cliente não tem cartão.");
                        } else {
                            for (Card card : customer.getCards()) {
                                System.out.println(card);
                            }
                            System.out.println("Qual número de cartão o usuário quer usar ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if (selectedCard == null) {
                                System.out.println("Cartão não encontrado");
                            } else {
                                if (selectedCard.getDeclinedPurchases().isEmpty()) {
                                    System.out.println("Não existe compras recusadas nesse cartão");
                                } else {
                                    for (Purchase declinedPurchase : selectedCard.getDeclinedPurchases()) {
                                        System.out.println(declinedPurchase);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Cliente não existe.");
                    }else{
                        if(customer.getCards().isEmpty()){
                            System.out.println("Este cliente não tem cartão.");
                        }else{
                            for(Card card: customer.getCards()){
                                System.out.println(card);
                            }
                            System.out.println("Qual cartão o usuário quer ver o histórico completo (ordenado por data) ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if(selectedCard == null){
                                System.out.println("Cartão não encontrado");
                            }else{
                                if(selectedCard.getShoppingList().isEmpty()){
                                    System.out.println("Não existe compras nesse cartão");
                                }else{
                                    Collections.sort(selectedCard.getShoppingList());
                                    for(Purchase purchase: selectedCard.getShoppingList()){
                                        System.out.println(purchase);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Cliente não existe.");
                    }else{
                        if(customer.getCards().isEmpty()){
                            System.out.println("Este cliente não tem cartão.");
                        }else{
                            for(Card card: customer.getCards()){
                                System.out.println(card);
                            }
                            System.out.println("Qual cartão o usuário quer ver a maior compra ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if(selectedCard == null){
                                System.out.println("Cartão não encontrado");
                            }else{
                                Purchase purchase = Collections.max(selectedCard.getShoppingList(), new PurchaseValueComparator());
                                System.out.println(purchase);
                            }
                        }
                    }
                    break;
                }
                case 8: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Cliente não existe.");
                    }else{
                        if(customer.getCards().isEmpty()){
                            System.out.println("Este cliente não tem cartão.");
                        }else{
                            for(Card card: customer.getCards()){
                                System.out.println(card);
                            }
                            System.out.println("Qual cartão o usuário quer ver a menor compra ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if(selectedCard == null){
                                System.out.println("Cartão não encontrado");
                            }else{
                                Purchase purchase = Collections.min(selectedCard.getShoppingList(), new PurchaseValueComparator());
                                System.out.println(purchase);
                            }
                        }
                    }
                    break;
                }
                case 9: {
                    System.out.println("Qual o ID do cliente ?");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Este cliente não existe.");
                    }else{
                        double selectedCustomer = customer.getTotalSpent();
                        System.out.println("Valor total gasto do cliente: R$" + selectedCustomer);
                    }
                    break;
                }
                case 10: {
                    System.out.println("Qual o Id do cliente ? ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = bank.findCustomerById(id);
                    if(customer == null){
                        System.out.println("Este cliente não existe.");
                    }else{
                        if(customer.getCards().isEmpty()){
                            System.out.println("Este cliente não tem cartão.");
                        }else{
                            for(Card card: customer.getCards()){
                                System.out.println(card);
                            }
                            System.out.println("Qual cartão o usuário quer ver o limite disponível ? ");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            Card selectedCard = customer.findCardByNumber(number);
                            if(selectedCard == null){
                                System.out.println("Cartão não encontrado.");
                            }else{
                                System.out.println("Limite disponível: R$" + selectedCard.getAvailableLimit());
                            }
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Número invalido.");
                    break;
                }
            }

        }
    }
}
