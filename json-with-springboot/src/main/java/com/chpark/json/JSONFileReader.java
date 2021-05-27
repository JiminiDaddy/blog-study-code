package com.chpark.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class JSONFileReader {
    static JSONObject readFile(String filePath) {
        JSONObject object = new JSONObject();
        InputStream inputStream = JSONFileReader.class.getClassLoader().getResourceAsStream(filePath);
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            JSONParser jsonParser = new JSONParser();
            object = (JSONObject) jsonParser.parse(inputStreamReader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return object;
    }
}
