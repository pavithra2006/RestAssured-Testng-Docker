package com.learning.Exceptions;

public class InvalidExcelFilePathException extends InvalidPathForFilesException {
    public InvalidExcelFilePathException(String message) {
        super(message);
    }

    public InvalidExcelFilePathException(String message, Throwable cause) {
        super(message, cause);
    }
}
