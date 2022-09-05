package com.haoshuang.wolterskluwerassignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the error message to return to the frontend. When returning, it will look like a Json object that contains a key called message, and value of message.
 *
 * @author Haoshuang Lang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private String message;
}
