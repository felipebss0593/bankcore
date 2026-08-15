package com.felipe.bankcore.model;

import com.felipe.bankcore.exception.CdiApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

/**
 * representa uma conta corrente que rende o valor,
 * do saldo com a taxa do cdi buscada em uma API externa.
 */
public class CheckingAccount extends Account{

    /**
     * Cria uma nova conta corrente, repassando o dono para a superclasse Account.
     * @param owner o cliente dono da conta.
     */
    public CheckingAccount(Customer owner) {
        super(owner);
    }

    private static String endereco = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.12/dados/ultimos/1?formato=json";

    /**
     * faz uma requisição http para buscar essa API do banco central.
     * faz uma desserialização com a biblioteca Gson para transformar,
     * o valor do CDI de String para Double.
     * faz o calculo para deixar o valor do CDI em taxaDecimal ao inves de %.
     * faz o calculo do rendimento do valor do CDI multiplicando o valor do saldo com a taxa decimal.
     * @return getBalance * taxaDecimal.
     */
    @Override
    public double calcularRendimento() {
        try{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            RegistroCdi[] registros = gson.fromJson(response.body(), RegistroCdi[].class);
            double valorDoCdi = Double.parseDouble(registros[0].valor());
            double taxaDecimal = valorDoCdi / 100;
            return getBalance() * taxaDecimal;

    } catch (IOException | InterruptedException e){
            throw new CdiApiException("Não foi possivel obter a taxa CDI: " + e.getMessage());
        }
    }
}
