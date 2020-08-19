package com.example.demo.batch;

import com.example.demo.domain.Conta;
import com.example.demo.service.ReceitaService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Conta, Conta> {

    @Autowired
    private ReceitaService service;

    @Override
    public Conta process(Conta conta) {
        conta.setProcessado(process(conta, 1));
        return conta;
    }

    private boolean process(Conta conta, int i) {
        try {
            return service.atualizarConta(
                    conta.getAgencia(),
                    getFormatedConta(conta.getConta()),
                    conta.getSaldo(),
                    conta.getStatus());
        } catch (InterruptedException e) {
            e.printStackTrace();
            if (i <= 3) {
                return process(conta, ++i);
            }

            return false;
        }
    }

    private String getFormatedConta(String conta) {
        return conta.replace("-", "");
    }

}
