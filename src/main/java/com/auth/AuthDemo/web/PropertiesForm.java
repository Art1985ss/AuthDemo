package com.auth.AuthDemo.web;

import com.auth.AuthDemo.dto.DtoQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesForm {
    private Map<String, String> properties = new HashMap<>();

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(List<DtoQuestion> questions) {
        for (DtoQuestion question : questions) {
            properties.put(question.getId().toString(), question.getUserAnswer());
        }
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "PropertiesForm{" +
                "properties=" + properties +
                '}';
    }
}