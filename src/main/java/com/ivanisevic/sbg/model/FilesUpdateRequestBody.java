package com.ivanisevic.sbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ivanisevic.sbg.responses.Metadata;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilesUpdateRequestBody {
    private String name = null;
    private Metadata metadata = new Metadata();
    private List<String> tags = new ArrayList<>();
}
