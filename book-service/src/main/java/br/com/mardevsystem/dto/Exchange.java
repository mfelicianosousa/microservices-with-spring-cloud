package br.com.mardevsystem.dto;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Exchange implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long Id;

    private String from;

    private String to;

    private BigDecimal conversionFactor;

    private Double convertedValue;

    private String environment;

    public Exchange(){}

    public Exchange(Long id, String from, String to, BigDecimal conversionFactor, Double convertedValue, String environment) {
        Id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Exchange exchange)) return false;
        return Objects.equals(Id, exchange.Id) && Objects.equals(from, exchange.from) && Objects.equals(to, exchange.to) && Objects.equals(conversionFactor, exchange.conversionFactor) && Objects.equals(convertedValue, exchange.convertedValue) && Objects.equals(environment, exchange.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, from, to, conversionFactor, convertedValue, environment);
    }
}
