package com.melit.springbootdemo.domain.googleapis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @JsonProperty("formatted_address")
    private String formattedAddress;

    private Geometry geometry;

}
