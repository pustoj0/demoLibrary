package com.example.demolibrary.facebook.flow.searchFlow;

import com.example.demolibrary.client.GutendexClient;
import com.example.demolibrary.facebook.dto.receive.library.LibraryDTO;
import com.example.demolibrary.facebook.dto.send.template.generic.Button;
import com.example.demolibrary.facebook.dto.send.template.generic.Element;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateDTO;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateUtil;
import com.example.demolibrary.facebook.service.SendMessageService;
import com.example.demolibrary.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SearchFlow {
    private SendMessageService templateService;
    private GutendexClient gutendexClient;

    private GenericTemplateDTO createGenericTemplateForSearch(String recipientId, String topic) {
        Set<Book> books = gutendexClient.searchBooksByTopic(topic);
        Set<Element> elementList =
                books
                        .stream()
                        .map(book -> {
                            Element element = new Element();
                            element.setTitle(book.getTitle());
                            element.setImage_url(book.getJpeg_url());
                            Button button1 = new Button();
                            button1.setType("postback");
                            button1.setPayload("add," + "book_id," + book.getId());
                            button1.setTitle("Add to catalog");
                            element.setButtons(List.of(button1));
                            return element;
                        })
                        .limit(5)
                        .collect(Collectors.toSet());
        return GenericTemplateUtil.createGenericTemplate(recipientId, elementList);
    }
}
