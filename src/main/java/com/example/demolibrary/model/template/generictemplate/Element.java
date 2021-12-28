package com.example.demolibrary.model.template.generictemplate;

import java.util.List;

public class Element {
    public String title;
    public String image_url;
    public String subtitle;
    public DefaultAction default_action;
    public List<Button> buttons;

    public Element(String title, String image_url, String subtitle, DefaultAction default_action, List<Button> buttons) {
        this.title = title;
        this.image_url = image_url;
        this.subtitle = subtitle;
        this.default_action = default_action;
        this.buttons = buttons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public DefaultAction getDefault_action() {
        return default_action;
    }

    public void setDefault_action(DefaultAction default_action) {
        this.default_action = default_action;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
