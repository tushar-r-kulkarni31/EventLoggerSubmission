package com.app.event.eventlogger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class EventLoggerApplicationTests {

    @Autowired
    InitProgram initProgram;

    @Test
    void testCaseWithInocrrectCorrectFilePath() throws Exception {
        initProgram.run("D:\\Tushar\\Dev\\Microservices\\logreader\\event-logger\\src\\main\\java\\com\\app\\event\\pojo\\newFile1.txt");
    }

    @Test
    void testCaseWithCorrectFilePath() throws Exception {
        initProgram.run("D:\\Tushar\\Dev\\Microservices\\logreader\\event-logger\\src\\main\\java\\com\\app\\event\\pojo\\newFile.txt");
    }

}
