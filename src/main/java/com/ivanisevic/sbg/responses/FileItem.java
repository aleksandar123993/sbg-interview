package com.ivanisevic.sbg.responses;

import lombok.Data;

@Data
public class FileItem {

    private String href;
    private String id;
    private String name;
    private String project;
    private String parent;
    private String type;
}
