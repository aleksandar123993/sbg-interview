package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.exceptions.ValidationException;
import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.model.FilesDownloadCommand;
import com.ivanisevic.sbg.model.FilesStatCommand;
import com.ivanisevic.sbg.model.FilesUpdateCommand;
import com.ivanisevic.sbg.responses.Metadata;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static com.ivanisevic.sbg.model.CommandName.FILES_DOWNLOAD;
import static com.ivanisevic.sbg.model.CommandName.FILES_LIST;
import static com.ivanisevic.sbg.model.CommandName.FILES_STAT;
import static com.ivanisevic.sbg.model.CommandName.FILES_UPDATE;
import static com.ivanisevic.sbg.model.CommandName.PROJECTS_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandValidatorTest extends BaseUnitTest {

    @Autowired
    CommandValidator commandValidator;

    @Test(expected = ValidationException.class)
    public void test_validateCommandWhenCommandIsNotSet() {
        commandValidator.validateAndReturnCommand("cgccli --token token without correct command.");
    }

    @Test(expected = ValidationException.class)
    public void test_validateCommandWhenMoreThenOneCommandIsSet() {
        commandValidator.validateAndReturnCommand("cgccli --token token projects list files list");
    }

    @Test
    public void test_validateProjectsListCommandWithoutQueries() {
        Command command = commandValidator.validateAndReturnCommand("cgccli --token token projects list");
        assertThat(command.getQueries().size()).isEqualTo(0);
        assertThat(command.getName()).isEqualTo(PROJECTS_LIST);
    }

    @Test
    public void test_validateProjectsListCommandWithQueries() {
        Command command = commandValidator.validateAndReturnCommand("cgccli --token token projects list " +
                "--name=name --limit=5 --offset=0 --fields=fields");
        assertThat(command.getQueries().size()).isEqualTo(4);
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("name");
        assertThat(keySet).contains("limit");
        assertThat(keySet).contains("offset");
        assertThat(keySet).contains("fields");
        assertThat(command.getName()).isEqualTo(PROJECTS_LIST);
    }

    @Test
    public void test_validateFilesListCommandWithoutQueries() {
        Command command = commandValidator.validateAndReturnCommand("cgccli --token token files list");
        assertThat(command.getQueries().size()).isEqualTo(0);
        assertThat(command.getName()).isEqualTo(FILES_LIST);
    }

    @Test
    public void test_validateFilesListCommandWithQueries() {
        Command command = commandValidator.validateAndReturnCommand("cgccli --token token files list " +
                "--name=name --fields=fields --project=project --parent=parent --origin.task=origin.task" +
                " --origin.dataset=origin.dataset --tag=tag --metadata.library_id=library_id " +
                "--metadata.reference_genome=reference_genome --metadata.sample_type=sample_type --metadata.platform=platform " +
                "--metadata.investigation=investigation --metadata.case_id=case_id --metadata.sample_id=sample_id " +
                "--metadata.experimental_strategy=experimental_strategy --metadata.gender=gender");
        assertThat(command.getQueries().size()).isEqualTo(16);
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("name");
        assertThat(keySet).contains("fields");
        assertThat(keySet).contains("project");
        assertThat(keySet).contains("parent");
        assertThat(keySet).contains("origin.task");
        assertThat(keySet).contains("origin.dataset");
        assertThat(keySet).contains("tag");
        assertThat(keySet).contains("metadata.library_id");
        assertThat(keySet).contains("metadata.reference_genome");
        assertThat(keySet).contains("metadata.sample_type");
        assertThat(keySet).contains("metadata.platform");
        assertThat(keySet).contains("metadata.investigation");
        assertThat(keySet).contains("metadata.case_id");
        assertThat(keySet).contains("metadata.sample_id");
        assertThat(keySet).contains("metadata.experimental_strategy");
        assertThat(keySet).contains("metadata.gender");
        assertThat(command.getName()).isEqualTo(FILES_LIST);
    }

    @Test(expected = ValidationException.class)
    public void test_validateFilesStatCommandWithoutPathParam() {
        commandValidator.validateAndReturnCommand("cgccli --token token files stat");
    }

    @Test
    public void test_validateFilesStatCommandWithoutQueries() {
        FilesStatCommand command = (FilesStatCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files stat --file_id=fileid");
        assertThat(command.getQueries().size()).isEqualTo(0);
        assertThat(command.getName()).isEqualTo(FILES_STAT);
        assertThat(command.getFileId()).isEqualTo("fileid");
    }

    @Test
    public void test_validateFilesStatCommandWithQueries() {
        FilesStatCommand command = (FilesStatCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files stat --file_id=fileid --fields=fields");
        assertThat(command.getQueries().size()).isEqualTo(1);
        assertThat(command.getName()).isEqualTo(FILES_STAT);
        assertThat(command.getFileId()).isEqualTo("fileid");
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("fields");
    }

    @Test(expected = ValidationException.class)
    public void test_validateFilesUpdateCommandWithoutPathParam() {
        commandValidator.validateAndReturnCommand("cgccli --token token files update");
    }

    @Test
    public void test_validateFilesUpdateCommandWithoutQueries() {
        FilesUpdateCommand command = (FilesUpdateCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files update --file_id=fileid");
        assertThat(command.getQueries().size()).isEqualTo(0);
        assertThat(command.getName()).isEqualTo(FILES_UPDATE);
        assertThat(command.getFileId()).isEqualTo("fileid");
        assertThat(command.getRequestBody().getMetadata()).isNull();
        assertThat(command.getRequestBody().getTags()).isNull();
        assertThat(command.getRequestBody().getName()).isNull();
    }

    @Test
    public void test_validateFilesUpdateCommandWithQueries() {
        FilesUpdateCommand command = (FilesUpdateCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files update --file_id=fileid --fields=fields");
        assertThat(command.getQueries().size()).isEqualTo(1);
        assertThat(command.getName()).isEqualTo(FILES_UPDATE);
        assertThat(command.getFileId()).isEqualTo("fileid");
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("fields");
        assertThat(command.getRequestBody().getMetadata()).isNull();
        assertThat(command.getRequestBody().getTags()).isNull();
        assertThat(command.getRequestBody().getName()).isNull();
    }

    @Test
    public void test_validateFilesUpdateCommandWithQueriesAndRequestBody() {
        FilesUpdateCommand command = (FilesUpdateCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files update --file_id=fileid " +
                        "--fields=fields name=name metadata.library_id=library_id metadata.reference_genome=reference_genome " +
                        "metadata.sample_type=sample_type metadata.platform=platform metadata.investigation=investigation " +
                        "metadata.case_id=case_id metadata.sample_id=sample_id " +
                        "metadata.experimental_strategy=experimental_strategy metadata.gender=gender tags=tag1, tag2, tag3");
        assertThat(command.getQueries().size()).isEqualTo(1);
        assertThat(command.getName()).isEqualTo(FILES_UPDATE);
        assertThat(command.getFileId()).isEqualTo("fileid");
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("fields");
        Metadata metadata = command.getRequestBody().getMetadata();

        assertThat(metadata.getLibrary_id()).isEqualTo("library_id");
        assertThat(metadata.getReference_genome()).isEqualTo("reference_genome");
        assertThat(metadata.getSample_type()).isEqualTo("sample_type");
        assertThat(metadata.getPlatform()).isEqualTo("platform");
        assertThat(metadata.getInvestigation()).isEqualTo("investigation");
        assertThat(metadata.getCase_id()).isEqualTo("case_id");
        assertThat(metadata.getSample_id()).isEqualTo("sample_id");
        assertThat(metadata.getExperimental_strategy()).isEqualTo("experimental_strategy");
        assertThat(metadata.getGender()).isEqualTo("gender");
        List<String> tags = command.getRequestBody().getTags();
        assertThat(tags.size()).isEqualTo(3);
        assertThat(tags).contains("tag1");
        assertThat(tags).contains("tag2");
        assertThat(tags).contains("tag3");
        assertThat(command.getRequestBody().getName()).isEqualTo("name");
    }

    @Test(expected = ValidationException.class)
    public void test_validateFilesDownloadCommandWithoutPathParam() {
        commandValidator.validateAndReturnCommand("cgccli --token token files download");
    }

    @Test(expected = ValidationException.class)
    public void test_validateFilesDownloadCommandWithoutDest() {
        commandValidator.validateAndReturnCommand("cgccli --token token files download --file_id=fileid");
    }

    @Test
    public void test_validateFilesDownloadCommandWithoutQueries() {
        FilesDownloadCommand command = (FilesDownloadCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files download " +
                        "--file_id=fileid --dest=/tmp/foo.txt");
        assertThat(command.getQueries().size()).isEqualTo(0);
        assertThat(command.getName()).isEqualTo(FILES_DOWNLOAD);
        assertThat(command.getFileId()).isEqualTo("fileid");
        assertThat(command.getDestination()).isEqualTo("/tmp/foo.txt");
    }

    @Test
    public void test_validateFilesDownloadCommandWithQueries() {
        FilesDownloadCommand command = (FilesDownloadCommand)
                commandValidator.validateAndReturnCommand("cgccli --token token files download " +
                        "--file_id=fileid --fields=fields --dest=/tmp/foo.txt");
        assertThat(command.getQueries().size()).isEqualTo(1);
        assertThat(command.getName()).isEqualTo(FILES_DOWNLOAD);
        assertThat(command.getFileId()).isEqualTo("fileid");
        Set<String> keySet = command.getQueries().keySet();
        assertThat(keySet).contains("fields");
        assertThat(command.getDestination()).isEqualTo("/tmp/foo.txt");
    }
}
