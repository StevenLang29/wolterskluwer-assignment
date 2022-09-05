package com.haoshuang.wolterskluwerassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class is the controller advice class, whenever an exception is thrown, the type of the exception will be checked, and it will send http status code with message to frontend
 *
 * @author Haoshuang Lang
 */
@ControllerAdvice
public class MainExceptionHandler {
    /**
     * The function will be called when the thrown exception is none of other subtypes of exceptions, it will return the exception message with code 500
     * @param exception General exception, super class of all exceptions
     * @return Json object that contains a key called message with its value
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResult handleException(Exception exception) {
        return new ErrorResult(exception.getMessage());
    }

    /**
     * The function will be called when the thrown exception is file extension exception, it will return the exception message with code 400
     * @param exception An exception that is thrown when received file extension is not txt/html/xml
     * @return Json object that contains a key called message with its value
     */
    @ExceptionHandler(FileExtensionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handleFileExtensionException(FileExtensionException exception) {
        return new ErrorResult(exception.getMessage());
    }

    /**
     * The function will be called when the thrown exception is response status exception, it will return the exception message with code 404
     * @param exception An exception that is thrown when the frontend called a wrong method or path
     * @return Json object that contains a key called message with its value
     */
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handleResponseStatusException(ResponseStatusException exception) {
        return new ErrorResult(exception.getReason());
    }

}
