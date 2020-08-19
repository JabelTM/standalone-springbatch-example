package com.example.demo.batch;

import com.example.demo.domain.Conta;
import com.example.demo.domain.ContaFields;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class Writer {

    private static final String DELIMITER = ";";

    @Value("${output.file.name}")
    private String fileName;

    public FlatFileItemWriter<Conta> getWriter() {
        return new FlatFileItemWriterBuilder<Conta>()
                .name("csvWriter")
                .resource(new FileSystemResource(fileName))
                .headerCallback(writer -> writer.write(getHeader()))
                .lineAggregator(defaultLineAggregator())
                .build();
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder();

        sb.append(ContaFields.AGENCIA.getFieldName());
        sb.append(DELIMITER);
        sb.append(ContaFields.CONTA.getFieldName());
        sb.append(DELIMITER);
        sb.append(ContaFields.SALDO.getFieldName());
        sb.append(DELIMITER);
        sb.append(ContaFields.STATUS.getFieldName());
        sb.append(DELIMITER);
        sb.append(ContaFields.PROCESSADO.getFieldName());

        return sb.toString();
    }

    private LineAggregator<Conta> defaultLineAggregator() {
        BeanWrapperFieldExtractor fieldExtractor = new BeanWrapperFieldExtractor<Conta>();
        fieldExtractor.setNames(new String[]{
                ContaFields.AGENCIA.getFieldName(),
                ContaFields.CONTA.getFieldName(),
                ContaFields.SALDO.getFieldName(),
                ContaFields.STATUS.getFieldName(),
                ContaFields.PROCESSADO.getFieldName()
        });

        DelimitedLineAggregator lineAggregator = new DelimitedLineAggregator<Conta>();
        lineAggregator.setDelimiter(DELIMITER);
        lineAggregator.setFieldExtractor(fieldExtractor);

        return lineAggregator;
    }

}
