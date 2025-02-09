package ru.isands.test.estore.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class MultiDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat[] DATE_FORMATTERS = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"),
            new SimpleDateFormat("dd.MM.yyyy HH:mm"),
            new SimpleDateFormat("dd.MM.yyyy"),
            new SimpleDateFormat("yyyy-MM-dd")
    };

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String text = p.getText();
        for (SimpleDateFormat formatter : DATE_FORMATTERS) {
            try {
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                return formatter.parse(text);
            } catch (ParseException e) {
            }
        }
        throw new JsonParseException(p, "Unparseable date: \"" + text + "\". Supported formats: " +
                Arrays.stream(DATE_FORMATTERS).map(SimpleDateFormat::toPattern).collect(Collectors.joining("; ")));
    }
}
