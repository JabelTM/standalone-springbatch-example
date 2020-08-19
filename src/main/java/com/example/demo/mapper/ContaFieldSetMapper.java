package com.example.demo.mapper;

import com.example.demo.domain.Conta;
import com.example.demo.domain.ContaFields;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class ContaFieldSetMapper implements FieldSetMapper<Conta> {

    @Override
    public Conta mapFieldSet(FieldSet fieldSet) throws BindException {
        Conta conta = new Conta();
        conta.setAgencia(fieldSet.readString(ContaFields.AGENCIA.getPosition()));
        conta.setConta(fieldSet.readString(ContaFields.CONTA.getPosition()));
        conta.setSaldo(Double.parseDouble(fieldSet
                    .readString(ContaFields.SALDO.getPosition())
                        .replace(",", ".")));
        conta.setStatus(fieldSet.readString(ContaFields.STATUS.getPosition()));
        return conta;
    }

}
