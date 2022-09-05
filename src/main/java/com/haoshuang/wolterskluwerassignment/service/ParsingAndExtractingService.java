package com.haoshuang.wolterskluwerassignment.service;

import com.google.gson.Gson;
import com.haoshuang.wolterskluwerassignment.exception.FileExtensionException;
import com.haoshuang.wolterskluwerassignment.model.Reference;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a service class, it converts an incoming file to a json array that contains all the information of found numbers of the file
 *
 * @author Haoshuang Lang
 */
@Service
@RequiredArgsConstructor
public class ParsingAndExtractingService {
    private static final List<String> SUPPORTED_FILE_TYPES = List.of("html", "xml", "txt");
    private List<Reference> references;
    private boolean parsingHTMLXMLFile;
    private boolean passedLeftAngleBracket;

    /**
     * The function converts a file to a Json array of info of found numbers
     * @param file The received file
     * @return Json array as string
     * @throws IOException An exception that happens when writing/reading a file has issues
     */
    public String parseFileAndExtractNumber(MultipartFile file) throws IOException {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (fileExtension == null || !SUPPORTED_FILE_TYPES.contains(fileExtension)) {
            throw new FileExtensionException("Only html/xml/txt file can be accepted");
        }

        switch (fileExtension) {
            case "txt":
                parsingHTMLXMLFile = false;
                return readFileByBufferedReader(file.getInputStream());
            case "html":
            case "xml":
                parsingHTMLXMLFile = true;
                return readFileByBufferedReader(file.getInputStream());
            default:
                throw new FileExtensionException("Only html/xml/txt file can be accepted");
        }
    }

    /**
     * Going through a file line by line. If it is not a html/xml file, it will check if the line has number. If no, it won't read the line.
     * But when it is a html/xml file, it will read everything, because sometimes a line can be "<meta http-equiv="Content-Type" content="text/html;",
     * it contains both right angle bracket and numbers, the right angle bracket needs to be read for later usages
     * @param inputStream InputStream of a file
     * @return The return calls a function that converts a list of instances of class reference to a json array
     */
    private String readFileByBufferedReader(InputStream inputStream) {
        references = new ArrayList<>();
        passedLeftAngleBracket = false;

        try {
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            int currentLine = 1;

            while ((line = bufferedReader.readLine()) != null) {
                if (checkIfStringContainsDigit(line) || parsingHTMLXMLFile)
                    parseLine(currentLine, line);
                currentLine ++;
            }
            reader.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return convertListOfReferencesToJson(references);
    }

    /**
     * The function converts the content of a line from string to an array of chars, when parsing a html/xml file, a right angle bracket will be viewed as beginning of html tag,
     * a left angle bracket will be viewed as end of a html tag, the tags contains numbers, but the numbers within tags will nnt be recorded. The numbers outside of tags,
     * will be recorded in a list of instances of reference class
     * @param currentLinenNumber Line number of the line that being parsed within a file
     * @param targetLine The line of content that needs to be parsed
     */
    private void parseLine(int currentLinenNumber, String targetLine) {
        char[] targetLineAsCharacters = targetLine.toCharArray();
        for (int i = 0; i < targetLineAsCharacters.length; i++) {
            if (targetLineAsCharacters[i] == '<' && parsingHTMLXMLFile) {
                passedLeftAngleBracket = true;
            } else if (targetLineAsCharacters[i] == '>' && parsingHTMLXMLFile) {
                passedLeftAngleBracket = false;
            } else if ( (Character.isDigit(targetLineAsCharacters[i]) && !parsingHTMLXMLFile) ||
                    (Character.isDigit(targetLineAsCharacters[i]) && !passedLeftAngleBracket && parsingHTMLXMLFile) ) {
                references.add(new Reference(currentLinenNumber, i + 1, Character.getNumericValue(targetLineAsCharacters[i])));
            }
        }
    }

    /**
     * The function checks whether if a string contains any number
     * @param targetString String that needs to be checked
     * @return A boolean that shows if the string contains number
     */
    private boolean checkIfStringContainsDigit(String targetString) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(targetString);
        return matcher.find();
    }

    /**
     * The function converts a list of references to json array
     * @param references A list of references
     * @return A string that represents a json array
     */
    private String convertListOfReferencesToJson(List<Reference> references) {
        return new Gson().toJson(references);
    }

}
