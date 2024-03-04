package ru.cource.spring.data;

import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public final class FixData implements Consumer<LogData>, Fixable {
    @Override
    public void accept(LogData logData) {
        logData.error = logData.accessDate == null ? true : false;
    }
}
