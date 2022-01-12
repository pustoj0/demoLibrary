package com.example.demolibrary.facebook.flow;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@AllArgsConstructor
public class FlowMap {
    private Flow recommendationFlow;
    private Flow welcomeFlow;
    private Flow catalogFlow;

    @Bean(name = "stringFlowMap")
    public Map<String, Flow> getStringFlowMap() {
        Map<String, Flow> stringFlowMap = new HashMap<>();
        stringFlowMap.put("FACEBOOK_WELCOME", welcomeFlow);
        stringFlowMap.put("recommendations", recommendationFlow);
        stringFlowMap.put("showCatalog", catalogFlow);
        return stringFlowMap;
    }

}
