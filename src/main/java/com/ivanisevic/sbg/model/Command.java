package com.ivanisevic.sbg.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Command {

    protected CommandName name;
    protected String commandRegex;
    protected List<String> availableQueries = new ArrayList<>();
    protected Map<String, String> queries = new HashMap<>();
}
