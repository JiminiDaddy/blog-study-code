package com.chpark.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.io.InputStream;

public class JackSonExample {
    public static void main(String[] args) throws IOException {
        JackSonExample jackSonExample = new JackSonExample();
        // Json to Java
        jackSonExample.jsonToJavaObjectByJackson();
        System.out.println();
        // Java to Json
        jackSonExample.javaObjectToJsonByJackson();
    }

    private void jsonToJavaObjectByJackson() throws IOException {
        System.out.println("JackSonExample.jsonToJavaObjectByJackson");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        InputStream inputStream = JackSonExample.class.getClassLoader().getResourceAsStream("json-sample.json");
        Profile parsedProfile = mapper.readValue(inputStream, Profile.class);
        System.out.println(parsedProfile.toString());
    }

    private void javaObjectToJsonByJackson() throws JsonProcessingException {
        System.out.println("JackSonExample.javaObjectToJsonByJackson");
        Profile profile = createProfile();
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(profile);
        System.out.println(result);
    }

    private Profile createProfile() {
        Profile profile = new Profile("tester", 20);
        profile.addJob("programmer");
        profile.addPosition("app");
        profile.addSkill("mobile", "android");
        profile.addSkill("mobile", "ios");
        profile.addSkill("mobile", "flutter");

        profile.addSkill("language", "java"); profile.addSkill("language", "kotlin");
        profile.addSkill("language", "swift");
        profile.addSkill("language", "dart");

        return profile;
    }
}
