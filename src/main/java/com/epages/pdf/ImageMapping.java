package com.epages.pdf;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@ToString(of = "mappings")
public class ImageMapping {

    @Singular
    @Getter(onMethod = @__(@JsonAnyGetter))
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> mappings;
}
