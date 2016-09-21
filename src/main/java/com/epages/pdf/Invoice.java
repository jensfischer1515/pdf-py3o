package com.epages.pdf;

import static com.google.common.collect.Lists.newArrayList;

import lombok.Data;
import lombok.Singular;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class Invoice {

    @Singular
    private List<Item> items = newArrayList();

    private Document document;

    @Data
    public static class Item {
        @JsonProperty("InvoiceRef")
        private String invoiceRef;
        @JsonProperty("Amount")
        private Double amount;
        @JsonProperty("Currency")
        private String currency;
        private String val1;
        private String val2;
        private Double val3;
    }

    @Data
    public static class Document {
        private Double total;
    }
}
