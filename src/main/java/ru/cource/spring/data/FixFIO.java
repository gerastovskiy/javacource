package ru.cource.spring.data;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public final class FixFIO implements Consumer<LogData>, Fixable {
    @Override
    public void accept(LogData logData) {
        logData.fio = WordUtils.capitalizeFully(logData.fio);
    }
}