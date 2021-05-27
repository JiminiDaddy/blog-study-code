package com.chpark.json;

import java.util.*;

class Profile {
    private String name;

    private int age;

    private String job;

    private String position;

    private String favoriteLanguage;

    private String favoriteIde;

    private List<String> willSkills;

    private Map<String, List<String>> hasSkills;

    Profile(String name, int age) {
        this.name = name;
        this.age = age;
        this.hasSkills = new HashMap<>();
        this.willSkills = new ArrayList<>();
    }

    void addJob(String job) {
        this.job = job;
    }

    void addFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    void addFavoriteIde(String favoriteIde) {
        this.favoriteIde = favoriteIde;
    }

    void addPosition(String position) {
        this.position = position;
    }

    void addSkill(String type, String skill) {
        List<String> skillContents = hasSkills.getOrDefault(type, new ArrayList<>());
        skillContents.add(skill);
        hasSkills.put(type, skillContents);
    }

    void addWillSkill(String skill) {
        this.willSkills.add(skill);
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    String getJob() {
        return job;
    }

    String getPosition() {
        return position;
    }

    String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    String getFavoriteIde() {
        return favoriteIde;
    }

    List<String> getWillSkills() {
        return willSkills;
    }

    Map<String, List<String>> getHasSkills() {
        return hasSkills;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        sb.append("name:<").append(name).append(">\n");
        sb.append("age:<").append(age).append(">\n");
        sb.append("job:<").append(job).append(">\n");
        sb.append("position:<").append(position).append(">\n");
        sb.append("willSkill:").append("\n\t<");
        for (String willSkill : willSkills) {
            sb.append(willSkill).append(", ");
        }
        sb.replace(sb.lastIndexOf(", "), sb.length(), ">\n");

        sb.append("hasSkill:").append("\n");
        for (String skillType : hasSkills.keySet()) {
            List<String> skillContents = hasSkills.get(skillType);
            sb.append("\t").append(skillType).append(":\n\t\t");
            sb.append(Arrays.toString(skillContents.toArray())).append("\n");
        }
        return sb.toString();
    }
}
