package ru.cource.spring.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public final class Logger implements Loggerable{
    @Value("${logger.path}")
    private String logPath;
    @Value("${logger.fileError}")
    private String logErrorFile;
    @Value("${logger.fileInfo}")
    private String logInfoFile;

    @Override
    public void logError(String errorData) throws IOException {
        Path path = Path.of(logPath,logErrorFile);
        Files.writeString(path,errorData.concat(System.lineSeparator()), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }
    @Override
    public void logData(String logData) throws IOException {
        Path path = Path.of(logPath,logInfoFile);
        Files.writeString(path,logData.concat(System.lineSeparator()), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }
}
