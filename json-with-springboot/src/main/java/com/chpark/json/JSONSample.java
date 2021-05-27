package com.chpark.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONSample {
    public static void main(String[] args) throws IOException {
        JSONSample jsonSample = new JSONSample();
        // JSON to JavaObject
        jsonSample.jsonFileToJavaObject("json-sample.json");

        // JavaObject to JSON
        jsonSample.javaObjectToJson();
    }

    private void jsonFileToJavaObject(String jsonFile) {
        JSONObject object = JSONFileReader.readFile(jsonFile);
        StringBuilder sb = new StringBuilder();

        String name = (String) object.get("name");
        sb.append("name:<").append(name).append(">\n");

        int age = ((Long)object.get("age")).intValue();
        sb.append("age:<").append(age).append(">\n");

        String job = (String) object.get("job");
        sb.append("job:<").append(job).append(">\n");

        String position = (String) object.get("position");
        sb.append("position:<").append(position).append(">\n");

        JSONObject hasSkill = (JSONObject) object.get("has_skills");
        sb.append("hasSkill:\n\t");
        for (Object skill : hasSkill.keySet()) {
            sb.append("skillType:\n\t\t<");
            JSONArray skillContents = (JSONArray) hasSkill.get(skill);
            for (Object contents : skillContents) {
                sb.append(contents).append(", ");
            }
            sb.replace(sb.lastIndexOf(", "), sb.length(), ">\n");
        }
        sb.append("\n");

        JSONArray willSkils = (JSONArray) object.get("will_skills");
        sb.append("willSkill:<");
        for (Object skill : willSkils) {
           sb.append(skill).append(", ");
        }
        sb.replace(sb.lastIndexOf(", "), sb.length(), ">\n");
        System.out.println(sb.toString());
    }

    private void javaObjectToJson() throws IOException {
        Profile profile = new Profile("tester", 20);
        profile.addJob("programmer");
        profile.addPosition("app");
        profile.addSkill("mobile", "android");
        profile.addSkill("mobile", "ios");
        profile.addSkill("mobile", "flutter");

        profile.addSkill("language", "java");
        profile.addSkill("language", "kotlin");
        profile.addSkill("language", "swift");
        profile.addSkill("language", "dart");

        profile.addWillSkill("python");
        profile.addWillSkill("django");

        profile.addFavoriteLanguage("java");
        profile.addFavoriteIde("vscode");

        JSONObject profileObject = new JSONObject();

        profileObject.put("name", profile.getName());
        profileObject.put("age", profile.getAge());
        profileObject.put("job", profile.getJob());
        profileObject.put("position", profile.getPosition());

        profileObject.put("favorite_language", profile.getFavoriteLanguage());
        profileObject.put("favorite_ide", profile.getFavoriteIde());

        JSONObject object = new JSONObject(profile.getHasSkills());
        profileObject.put("has_skills", object);

        profileObject.put("will_skills", profile.getWillSkills());

        String result = profileObject.toJSONString();
        System.out.println(result);

        FileWriter  fileWriter = new FileWriter("json-with-springboot/src/main/resources/json-sample-output.json");
        fileWriter.write(result);
        fileWriter.close();
    }
}
