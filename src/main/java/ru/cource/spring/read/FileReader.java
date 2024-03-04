package ru.cource.spring.read;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.cource.spring.data.Datable;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@EqualsAndHashCode
public class FileReader implements Function<Class, FileReader> {
    @Getter
    private List<Datable> logData;
    private final Path filepath;
    public FileReader(Path filepath) {
        this.filepath = filepath;
    }
    @Override
    public FileReader apply(Class datable) {
        try {
            this.logData = new CsvToBeanBuilder(Files.newBufferedReader(filepath))
                    .withType(datable)
                    .withSeparator('|')
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("Read file error: " + e);
        }
        return this;
    }
}
