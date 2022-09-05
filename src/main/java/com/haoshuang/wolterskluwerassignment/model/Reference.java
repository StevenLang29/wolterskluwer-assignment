package com.haoshuang.wolterskluwerassignment.model;

import lombok.*;

/**
 * This class simulation a number that is found from file, it contains line number, position of character within a line, and the value of the found number
 *
 * @author Haoshuang Lang
 */
@AllArgsConstructor
@RequiredArgsConstructor
public class Reference {
    private int lineNumber;
    private int characterPosition;
    private int value;
}
