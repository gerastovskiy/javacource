package ru.cource.spring.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Component
public final class FixApplicationType implements Consumer<LogData>, Fixable {
    @Value("${fixer.applicationType.pattern}")
    private String applicationPattern;
    @Value("${fixer.applicationType.changeValue}")
    private String changeValue;
    @Override
    public void accept(LogData logData) {
        var pattern = Pattern.compile(applicationPattern);
        if (!pattern.matcher(logData.application).matches()) logData.application = changeValue + logData.application;
    }
}