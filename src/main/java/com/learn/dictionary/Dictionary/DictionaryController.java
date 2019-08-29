package com.learn.dictionary.Dictionary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DictionaryController {

    @PostMapping("/upload-dictionary")
    public void saveDictionary(@RequestParam("file") MultipartFile file) {
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./src/main/resources/static/dictionary.txt");
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/find-word")
    public String findWord(@RequestParam("word") String word) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("./src/main/resources/static/dictionary.txt"));
        // read line by line
        String line;
        while ((line = br.readLine()) != null) {
            if (line.split(":")[0].equals(word)) {
                return line.split(":")[1];
            }
        }
        return "Not found";
    }
}
