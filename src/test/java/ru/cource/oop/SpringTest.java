package ru.cource.oop;

import org.aspectj.lang.annotation.Before;
import org.h2.store.DataReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cource.Main;
import ru.cource.spring.AppConfig;
import ru.cource.spring.data.*;
import ru.cource.spring.fix.DataFixer;
import ru.cource.spring.read.FileReader;
import ru.cource.spring.read.FolderReader;
import ru.cource.spring.read.Readable;
import ru.cource.spring.write.H2Writer;

import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {FolderReader.class, DataFixer.class,  FixFIO.class, FixData.class, FixApplicationType.class, AppConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringTest {
    @Autowired
    FolderReader dataReader;
    @Autowired
    DataFixer dataFixer;
    @Mock
    FolderReader dataReaderMock;
    @Mock
    FolderReader dataReaderFixedMock;
    @Value("${reader.path}")
    String filePath;

    public LogData getCorrectLogData()
    {
        LogData correctLog = new LogData();
        correctLog.setUserName("testCorrect");
        correctLog.setFio("Correct Correctiovich");
        correctLog.setAccessDate(Timestamp.valueOf(LocalDateTime.of(2020,01,01,01,02,03)));
        correctLog.setApplication("web");

        return correctLog;
    }

    public LogData getIncorrectLogData()
    {
        LogData incorrectLog = new LogData();
        incorrectLog.setUserName("testIncorrect");
        incorrectLog.setFio("incorrect incorrectovich");
        incorrectLog.setApplication("app");

        return incorrectLog;
    }

    public LogData getFixedIncorrectLogData()
    {
        LogData incorrectLog = new LogData();
        incorrectLog.setUserName("testIncorrect");
        incorrectLog.setFio("Incorrect Incorrectovich");
        incorrectLog.setApplication("other: app");
        incorrectLog.setError(true);

        return incorrectLog;
    }

    public FolderReader getAllReadersData()
    {
        List<Datable> logDataList = List.of(getCorrectLogData(), getIncorrectLogData());
        FileReader fileReader = new FileReader(logDataList, Path.of(filePath,"test.log"));

        FolderReader folderReader = new FolderReader(filePath, LogData.class);
        folderReader.addLogsData(fileReader);

        return folderReader;
    }

    public FolderReader getAllFixedReadersData()
    {
        List<Datable> logDataList = List.of(getCorrectLogData(), getFixedIncorrectLogData());
        FileReader fileReader = new FileReader(logDataList, Path.of(filePath,"test.log"));

        FolderReader folderReader = new FolderReader(filePath, LogData.class);
        folderReader.addLogsData(fileReader);

        return folderReader;
    }

    @BeforeEach
    public void setUpAllReaderData(){
        FolderReader folderReader = getAllReadersData();

        Mockito.when(dataReaderMock.get())
                .thenReturn(folderReader);
    }
    @BeforeEach
    public void setUpAllFixedReaderData(){
        FolderReader folderReader = getAllFixedReadersData();

        Mockito.when(dataReaderFixedMock.get())
                .thenReturn(folderReader);
    }

    @Test
    @Order(1)
    public void readFileCheck(){
        dataReader.get();
        FolderReader dataReaderMockGet = dataReaderMock.get();

        Assertions.assertEquals(dataReaderMockGet, dataReader);
    }
    @Test
    @Order(2)
    public void fixFileCheck(){
        dataFixer.fix();
        FolderReader dataReaderFixedMockGet = dataReaderFixedMock.get();

        Assertions.assertEquals(dataReader, dataReaderFixedMockGet);
    }

}
