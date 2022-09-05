package com.haoshuang.wolterskluwerassignment.exception;

/**
 * This class is represents the exception that will be thrown when the file extension is not txt/html/xml
 *
 * @author Haoshuang Lang
 */
public class FileExtensionException extends IllegalArgumentException {
    public FileExtensionException(String message) {
        super(message);
    }
}
