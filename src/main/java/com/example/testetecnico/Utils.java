package com.example.testetecnico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Utils {

    public static boolean dataIsValid(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data no formato invalido, favor inserir data no formato ANO/MES/DIA yyyy-mm-dd");
        }
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "yyyy-MM-dd";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

