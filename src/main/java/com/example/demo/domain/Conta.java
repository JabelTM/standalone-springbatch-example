package com.example.demo.domain;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Conta {

    private String agencia;
    private String conta;
    private double saldo;
    private String status;
    private boolean isProcessado;

}