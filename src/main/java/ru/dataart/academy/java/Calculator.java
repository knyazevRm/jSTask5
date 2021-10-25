package ru.dataart.academy.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        String str = "";
        try {
            str = read(zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Math.toIntExact(str.chars()
                .filter(symbol -> symbol == character)
                .count());
    }

    private String read(String zFP) throws IOException {
        String fileContent = "";
        ZipFile zipFile = new ZipFile(zFP);
        for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = entries.nextElement();
            fileContent += new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)))
                    .lines()
                    .collect(Collectors.joining(" "));
        }
        zipFile.close();
        return fileContent;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        String str = "";
        try {
            str = read(zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(str.split("\\s"))
                .max(Comparator.comparing(String::length))
                .get().length();
    }

}
