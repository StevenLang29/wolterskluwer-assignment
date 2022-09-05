package com.haoshuang.wolterskluwerassignment.controller;

import com.haoshuang.wolterskluwerassignment.service.ParsingAndExtractingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This class is the controller of the path /parser, it is also the only controller of the project
 *
 * @author Haoshuang Lang
 */
@RestController
@RequestMapping("/parser")
@RequiredArgsConstructor
public class NumberExtractorController {

    private final ParsingAndExtractingService parsingAndExtractingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    String parseFileAndExtractNumber(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return parsingAndExtractingService.parseFileAndExtractNumber(file);
    }
}
