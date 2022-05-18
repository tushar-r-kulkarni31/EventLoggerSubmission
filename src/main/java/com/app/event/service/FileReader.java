package com.app.event.service;

import com.app.event.pojo.LogDetail;
import org.springframework.stereotype.Service;
import java.util.List;

public interface FileReader {

    public List<LogDetail> parseLogFile(String filePath);
}
