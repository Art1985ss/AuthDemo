package com.auth.AuthDemo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Wraper class for List objects to be passed between controllers. Spring boot does not
 * support Collection object passing from one controller to another, so this class is created for such purpose
 */
public class ListCreationDto implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    private List<String> answers = new ArrayList<>();

    public ListCreationDto(){

    }

    /**
     * Constructor which populates list with empty elements based on passed parameter
     * @param ansCount Answer count set on testmanage.html - so that answers can be mapped
     *                 appropriately on question input form.
     */
    public ListCreationDto(Integer ansCount){
        for (int i=0; i < ansCount; i++){
            this.addToList("");
        }
    }

    public void addToList(String answer) {
        this.answers.add(answer);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
