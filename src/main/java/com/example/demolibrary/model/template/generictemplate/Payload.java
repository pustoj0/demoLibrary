package com.example.demolibrary.model.template.generictemplate;

import java.util.List;

public class Payload {
    public String template_type;
    public List<Element> elements;

    public Payload(String template_type, List<Element> elements) {
        this.template_type = template_type;
        this.elements = elements;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
