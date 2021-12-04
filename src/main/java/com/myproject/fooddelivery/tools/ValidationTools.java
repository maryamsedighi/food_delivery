package com.myproject.fooddelivery.tools;

import com.myproject.fooddelivery.code.ErrorCodes;
import com.myproject.fooddelivery.exception.FoodDeliveryException;

import java.util.Objects;

public class ValidationTools {

    public static void validateEmptyField(String value, String title) throws FoodDeliveryException {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new FoodDeliveryException(ErrorCodes.FIELD_IS_EMPTY, title + " is null");
        }
    }

    public static void validationFieldLength(String value, Integer length, String title) throws FoodDeliveryException {
        if (value.length() < length) {
            throw new FoodDeliveryException(ErrorCodes.LENGTH_LESS, " length " + title + " can not be less than " + length + " character");
        }
    }
}
