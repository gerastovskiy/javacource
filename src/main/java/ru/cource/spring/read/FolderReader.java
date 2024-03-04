package ru.cource.spring.read;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import ru.cource.spring.log.LogTransformation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DependsOn("properties")
public class FolderReader implements Readable {
    @Getter
    private final List<FileReader> logsData = new ArrayList<>();
    @Value("${reader.path}")
    private String folderName;
    @Autowired
    Class datable;

    public void addLogsData(FileReader fileReader){
        logsData.add(fileReader);
    }
    @Override
    @LogTransformation
    public FolderReader get(){
        try {
            Files.walk(Paths.get(folderName))
                .filter(Files::isRegularFile)
                .forEach(file -> addLogsData(new FileReader(file).apply(datable)));
        } catch (IOException e) {
            throw new RuntimeException("Get file Error " + e);
        }
        return this;
    }
}