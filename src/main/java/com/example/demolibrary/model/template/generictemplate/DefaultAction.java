package com.example.demolibrary.model.template.generictemplate;

public class DefaultAction {
    public String type;
    public String url;
    public String webview_height_ratio;

    public DefaultAction(String type, String url, String webview_height_ratio) {
        this.type = type;
        this.url = url;
        this.webview_height_ratio = webview_height_ratio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebview_height_ratio() {
        return webview_height_ratio;
    }

    public void setWebview_height_ratio(String webview_height_ratio) {
        this.webview_height_ratio = webview_height_ratio;
    }
}
