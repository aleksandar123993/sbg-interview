package com.ivanisevic.sbg.model;

import com.ivanisevic.sbg.helpers.ParameterHelper;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.FILES_UPDATE;
import static com.ivanisevic.sbg.helpers.Constants.FILE_ID;
import static com.ivanisevic.sbg.helpers.Constants.WORD;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_UPDATE_AVAILABLE_QUERIES;

@Getter
public class FilesUpdateCommand extends Command {

    private String fileId;
    private FilesUpdateRequestBody requestBody;

    public FilesUpdateCommand(String input) {
        name = CommandName.FILES_UPDATE;
        commandRegex = FILES_UPDATE;
        availableQueries = Arrays.asList(FILES_UPDATE_AVAILABLE_QUERIES);
        fileId = ParameterHelper.setRequiredParam(input, Pattern.compile(FILE_ID + WORD));
        requestBody = new FilesUpdateRequestBody();
        requestBody.setName(ParameterHelper.setOptionalParam(input, Pattern.compile(" name=" + WORD)));
        requestBody.getMetadata().setLibrary_id(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.library_id=" + WORD)));
        requestBody.getMetadata().setReference_genome(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.reference_genome=" + WORD)));
        requestBody.getMetadata().setSample_type(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.sample_type=" + WORD)));
        requestBody.getMetadata().setPlatform(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.platform=" + WORD)));
        requestBody.getMetadata().setInvestigation(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.investigation=" + WORD)));
        requestBody.getMetadata().setCase_id(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.case_id=" + WORD)));
        requestBody.getMetadata().setSample_id(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.sample_id=" + WORD)));
        requestBody.getMetadata().setExperimental_strategy(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.experimental_strategy=" + WORD)));
        requestBody.getMetadata().setGender(ParameterHelper.setOptionalParam(input,
                Pattern.compile(" metadata.gender=" + WORD)));
        try {
            if (checkNull(requestBody.getMetadata())) {
                requestBody.setMetadata(null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        requestBody.setTags(ParameterHelper.setArrayParam(input, Pattern.compile(" tags=" + WORD)));
        if (requestBody.getTags().size() == 0) {
            requestBody.setTags(null);
        }
    }

    public boolean checkNull(Object o) throws IllegalAccessException {
        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(o) != null)
                return false;
        }
        return true;
    }

}
