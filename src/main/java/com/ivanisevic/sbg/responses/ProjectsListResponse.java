package com.ivanisevic.sbg.responses;

import lombok.Data;

import java.util.List;

@Data
public class ProjectsListResponse {

    private String href;
    private List<ProjectItem> items;
    private List<LinkItem> links;
}
