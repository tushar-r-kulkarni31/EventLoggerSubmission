package com.app.event.service;

import com.app.event.pojo.LogDetail;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@Primary
public class FileReaderImpl implements FileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderImpl.class);

    @Override
    public synchronized List<LogDetail> parseLogFile(String filePath) {
        LOGGER.info("Parsing started for the file >> " + filePath);

        java.io.FileReader reader = null;
        try {
            reader = new java.io.FileReader(filePath);
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Parsing failed during the file reading process",e);
        }

        BufferedReader file = new BufferedReader(reader);
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        try {
            inputBuffer.append("[");
            while ((line = file.readLine()) != null) {
                if(line.contains("[")) {
                    line = line.replaceAll("\\[","");
                }
                if(line.contains("]")) {
                    line = line.replaceAll("\\]","");
                }
                if(line.contains("},")) {
                    line = line.replaceAll("},","}");
                }

                if(inputBuffer.length() == 1) {
                    inputBuffer.append(line);
                } else {
                    inputBuffer.append("," + line);
                }
            }

            inputBuffer.append("]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream inputStream = new ByteArrayInputStream(inputBuffer.toString().getBytes());;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, LogDetail.class);

        List<LogDetail> lstLogDetail = null;

        try {
            lstLogDetail = mapper.readValue(inputStream, collectionType);
        }  catch (DatabindException e){
            throw  new RuntimeException(e);
        } catch (StreamReadException e) {
            throw  new RuntimeException(e);
        } catch (IOException e){
            throw  new RuntimeException(e);
        }

        LOGGER.info("Parsing completed for the file >> " + filePath);

        //System.out.println(lstLogDetail);
        return lstLogDetail;
    }
}
