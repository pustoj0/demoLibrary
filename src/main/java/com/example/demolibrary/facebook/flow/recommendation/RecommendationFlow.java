package com.example.demolibrary.facebook.flow.recommendation;

import com.example.demolibrary.client.GutendexClient;
import com.example.demolibrary.facebook.dto.receive.library.LibraryDTO;
import com.example.demolibrary.facebook.dto.send.template.generic.*;
import com.example.demolibrary.facebook.flow.Flow;
import com.example.demolibrary.facebook.service.SendMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecommendationFlow implements Flow {
    private SendMessageService templateService;
    private GutendexClient gutendexClient;

    @Override
    public void run(String recipientId) {
        GenericTemplateDTO genericTemplateDTO
                = createGenericTemplateForRecommendations(recipientId);
        templateService.sendMessage(genericTemplateDTO);
    }

    private GenericTemplateDTO createGenericTemplateForRecommendations(String recipientId) {
        LibraryDTO libraryDTO = gutendexClient.getLibraryDto();
        assert libraryDTO != null;
        Set<Element> elementList =
                libraryDTO.getResults()
                        .stream()
                        .map(result -> {
                            Element element = new Element();
                            element.setTitle(result.getTitle());
                            element.setImage_url(result.getFormats().getImageJpeg());
                            Button button1 = new Button();
                            button1.setType("postback");
                            button1.setPayload("add," + "book_id," + result.getId());
                            button1.setTitle("Add to catalog");
                            element.setButtons(List.of(button1));
                            return element;
                        })
                        .limit(5)
                        .collect(Collectors.toSet());
        return GenericTemplateUtil.createGenericTemplate(recipientId, elementList);
    }
}
