package com.ivanisevic.sbg.responses;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FilesUpdateResponse {

    private String href;
    private String id;
    private String name;
    private Long size;
    private String project;
    private Date created_on;
    private Date modified_on;
    private Storage storage;
    private Origin origin;
    private List<String> tags;
    private Metadata metadata;
}
