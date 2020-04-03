package com.auth.AuthDemo.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for mapping of question objects to answers for passing between controllers.
 * Spring boot does not support Collection object passing from one controller to another,
 * so this class is created for such purpose.
 */
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