package com.myproject.stockApp.tools;

import com.myproject.stockApp.code.ErrorCodes;
import com.myproject.stockApp.exception.StockAppException;

import java.util.Objects;

public class ValidationTools {

    public static void validateEmptyField(String value, String title) throws StockAppException {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new StockAppException(ErrorCodes.FIELD_IS_EMPTY, title + " is null");
        }
    }

    public static void validationFieldLength(String value, Integer length, String title) throws StockAppException {
        if (value.length() < length) {
            throw new StockAppException(ErrorCodes.LENGTH_LESS, " length " + title + " can not be less than " + length + " character");
        }
    }
}
