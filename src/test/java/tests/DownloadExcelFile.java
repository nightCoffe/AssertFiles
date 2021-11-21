package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.FilesMethod.readXlsxFromPath;

public class DownloadExcelFile extends TestBase {

    @Test
    @DisplayName("Загрузка .xlsx файла")
    void downloadExcelFile() throws IOException {
        Configuration.downloadsFolder = "./downloads";
        openUrl();
        File fileXlsxPath =  $(".item-page").$("a").download();;
        String fileData = "\"Бюджетная линейка извещателей\"";
        String actualData = readXlsxFromPath(fileXlsxPath);
        assertThat(actualData, containsString(fileData));
    }
}
