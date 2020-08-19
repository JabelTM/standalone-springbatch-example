package com.example.demo.domain;

public enum ContaFields {

    AGENCIA(0, "Agencia"),
    CONTA(1, "Conta"),
    SALDO(2, "Saldo"),
    STATUS(3, "Status"),
    PROCESSADO(4, "Processado");

    private int position;
    private String fieldName;

    ContaFields(int position, String fieldName) {
        this.position = position;
        this.fieldName = fieldName;
    }

    public int getPosition() {
        return position;
    }

    public String getFieldName() {
        return fieldName;
    }
}
