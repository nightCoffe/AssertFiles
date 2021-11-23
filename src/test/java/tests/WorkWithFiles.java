package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.FilesMethod.getPdf;
import static utils.FilesMethod.readXlsxFromPath;

public class WorkWithFiles extends TestBase {

    @Test
    @DisplayName("Загрузка .xlsx файла и проверка содержимого")
    void downloadExcelFileTest() throws IOException {
        Configuration.downloadsFolder = "./downloads";
        openUrlXlsx();
        File fileXlsxPath = $(".item-page").$("a").download();
        String fileData = "\"Бюджетная линейка извещателей\"";
        String actualData = readXlsxFromPath(fileXlsxPath);
        assertThat(actualData, containsString(fileData));
    }

    @Test
    void xlsTest() throws IOException {
        String xmlFilePath = "./src/test/resources/blank.xls";
        String expectedData = "Сведения об индивидуальном предпринимателе, содержащиеся в Едином государственном реестре индивидуальных предпринимателей";
        XLS xls = new XLS(new File(xmlFilePath));
        String actualData = xls.excel.getSheetAt(0).getRow(11).getCell(4).toString();
        assertThat(actualData, containsString(expectedData));
    }

    @Test
    @DisplayName("Проверка содержимого .pdf файла")
    void pdfFileTest() throws IOException {
        String pdfFilePath = "./src/test/resources/Glossary.pdf";
        String expectedData = "Стандартный глоссарий терминов, используемых в ";
        PDF actualPdfData = getPdf(pdfFilePath);
        assertThat(actualPdfData, containsText(expectedData));
    }

    @Test
    @DisplayName("Проверка содержимого .docx файла")
    void docFile() throws IOException {
        String docxFilePath = "./src/test/resources/rabbit.docx";
        String expectedData = "Привет друзья";
        String fileContent = readFileToString(new File(docxFilePath), StandardCharsets.UTF_8);
        assertThat(fileContent, containsString(expectedData));
    }
}
