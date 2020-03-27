package com.auth.AuthDemo.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoPropertiesForm {
    private Map<Long, String> properties = new HashMap<>();

    public Map<Long, String> getProperties() {
        return properties;
    }

    public void setProperties(List<DtoQuestion> questions) {
        for (DtoQuestion question : questions) {
            properties.put(question.getId(), question.getUserAnswer());
        }
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "DtoPropertiesForm{" +
                "properties=" + properties +
                '}';
    }
}