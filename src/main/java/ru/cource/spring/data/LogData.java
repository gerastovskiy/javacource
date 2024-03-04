package ru.cource.spring.data;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.*;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;

@Component
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogData implements Datable{
    @CsvBindByPosition(position = 0)
    String userName;
    @CsvBindByPosition(position = 1)
    public String fio;
    @CsvBindByPosition(position = 2)
    @CsvDate(value = "yyyy-MM-dd\'T\'HH:mm:ss")
    Timestamp accessDate;
    @CsvBindByPosition(position = 3)
    String application;
    boolean error = false;
}
