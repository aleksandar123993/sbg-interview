package com.ivanisevic.sbg.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metadata {
    private String library_id;
    private String reference_genome;
    private String sample_type;
    private String platform;
    private String investigation;
    private String case_id;
    private String sample_id;
    private String experimental_strategy;
    private String gender;
}
