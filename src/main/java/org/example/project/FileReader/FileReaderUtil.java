package org.example.project.FileReader;


import org.springframework.core.io.Resource;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FileReaderUtil {


    public List<String> readLinesFromResource(Resource resource)  {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return bufferedReader.lines().map(String::trim).filter(line -> !line.isEmpty()).toList();
        }
        catch (IOException e) {
            throw  new IllegalArgumentException("failed to read lines from classpath resource: " + resource.getFilename());
        }
    }


}
