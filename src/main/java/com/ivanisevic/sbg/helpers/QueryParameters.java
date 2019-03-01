package com.ivanisevic.sbg.helpers;

import static com.ivanisevic.sbg.helpers.Constants.NUMBER;
import static com.ivanisevic.sbg.helpers.Constants.WORD;

public class QueryParameters {

    public static final String NAME = " --name=" + WORD;
    public static final String OFFSET = " --offset=" + NUMBER;
    public static final String LIMIT = " --limit=" + NUMBER;
    public static final String FIELDS = " --fields=" + WORD;
    public static final String PROJECT = " --project=" + WORD;
    public static final String PARENT = " --parent=" + WORD;
    public static final String ORIGIN_TASK = " --origin.task=" + WORD;
    public static final String ORIGIN_DATASET = " --origin.dataset=" + WORD;
    public static final String TAG = " --tag=" + WORD;
    public static final String METADATA_LIBRARY_ID = " --metadata.library_id=" + WORD;
    public static final String METADATA_REFERENCE_GENOME = " --metadata.reference_genome=" + WORD;
    public static final String METADATA_SAMPLE_TYPE = " --metadata.sample_type=" + WORD;
    public static final String METADATA_PLATFORM = " --metadata.platform=" + WORD;
    public static final String METADATA_INVESTIGATION = " --metadata.investigation=" + WORD;
    public static final String METADATA_CASE_ID = " --metadata.case_id=" + WORD;
    public static final String METADATA_SAMPLE_ID = " --metadata.sample_id=" + WORD;
    public static final String METADATA_EXPERIMENTAL_STRATEGY = " --metadata.experimental_strategy=" + WORD;
    public static final String METADATA_GENDER = " --metadata.gender=" + WORD;

    public static final String[] PROJECTS_LISTS_AVAILABLE_QUERIES = { NAME, OFFSET, LIMIT, FIELDS };
    public static final String[] FILES_LISTS_AVAILABLE_QUERIES = { NAME, FIELDS, PROJECT, PARENT, ORIGIN_DATASET,
            ORIGIN_TASK, TAG, METADATA_CASE_ID, METADATA_EXPERIMENTAL_STRATEGY, METADATA_GENDER, METADATA_INVESTIGATION,
            METADATA_LIBRARY_ID, METADATA_PLATFORM, METADATA_REFERENCE_GENOME, METADATA_SAMPLE_ID, METADATA_SAMPLE_TYPE };
    public static final String[] FILES_STAT_AVAILABLE_QUERIES = { FIELDS };
    public static final String[] FILES_UPDATE_AVAILABLE_QUERIES = { FIELDS };
    public static final String[] FILES_DOWNLOAD_AVAILABLE_QUERIES = { FIELDS };
}
