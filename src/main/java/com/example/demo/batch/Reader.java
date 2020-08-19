package com.example.demo.batch;

import com.example.demo.domain.Conta;
import com.example.demo.domain.ContaFields;
import com.example.demo.mapper.ContaFieldSetMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class Reader {

    @Autowired
    private ContaFieldSetMapper contaFieldSetMapper;

    @Autowired
    private ApplicationArguments applicationArguments;

    public FlatFileItemReader getReader() {
        return new FlatFileItemReaderBuilder<Conta>()
                .name("csvReader")
                .resource(new FileSystemResource(applicationArguments.getSourceArgs()[0]))
                .linesToSkip(1)
                .lineMapper(defaultLineMapper())
                .build();
    }

    public DefaultLineMapper<Conta> defaultLineMapper() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames(
                ContaFields.AGENCIA.getFieldName(),
                ContaFields.CONTA.getFieldName(),
                ContaFields.SALDO.getFieldName(),
                ContaFields.STATUS.getFieldName());

        DefaultLineMapper<Conta> defaultLineMapper = new DefaultLineMapper<Conta>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(contaFieldSetMapper);

        return defaultLineMapper;
    }

}
