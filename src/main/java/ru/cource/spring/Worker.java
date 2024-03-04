package ru.cource.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cource.spring.fix.DataFixable;
import ru.cource.spring.read.Readable;
import ru.cource.spring.write.Writable;

@Component
public class Worker {
    @Autowired
    Readable dataReader;
    @Autowired
    DataFixable dataFixer;
    @Autowired
    Writable dataWriter;

    public void make(){
        dataReader.get();
        dataFixer.fix();
        dataWriter.write();
    }
}
