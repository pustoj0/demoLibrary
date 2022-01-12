package com.example.demolibrary.facebook.flow.showcatalog;

import com.example.demolibrary.facebook.dto.send.template.generic.Button;
import com.example.demolibrary.facebook.dto.send.template.generic.Element;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateDTO;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateUtil;
import com.example.demolibrary.facebook.dto.send.textmessage.TextMessageUtil;
import com.example.demolibrary.facebook.flow.Flow;
import com.example.demolibrary.facebook.service.SendGenericTemplateService;
import com.example.demolibrary.facebook.service.SendTextMessageService;
import com.example.demolibrary.model.Book;
import com.example.demolibrary.model.Catalog;
import com.example.demolibrary.model.User;
import com.example.demolibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CatalogFlow implements Flow {
    private SendGenericTemplateService templateService;
    private SendTextMessageService textMessageService;
    private UserService userService;

    @Override
    public void run(String recipientId) {
        User user = userService.getUserByRecipientId(recipientId).orElseThrow();
        Catalog catalog = user.getCatalog();
        Set<Book> books = catalog.getBooks();
        GenericTemplateDTO genericTemplateDTO
                = createCatalogGenericTemplateDTO(books, recipientId);
        if (genericTemplateDTO.getMessage().getAttachment().getPayload().getElements().size() > 0) {
            templateService.sendGenericTemplate(genericTemplateDTO);
        } else {
            textMessageService.sendTextMessage(
                    TextMessageUtil.createTextMessage(recipientId, "Your catalog is empty"));
        }
    }

    private GenericTemplateDTO createCatalogGenericTemplateDTO(Set<Book> books, String recipientId) {
        Set<Element> elementList =
                books.stream().map(book -> {
                            Element element = new Element();
                            element.setTitle(book.getTitle());
                            element.setImage_url(book.getJpeg_url());
                            Button button1 = new Button();
                            button1.setType("postback");
                            button1.setPayload("delete," + "book_id," + book.getId());
                            button1.setTitle("Delete");
                            element.setButtons(List.of(button1));
                            return element;
                        })
                        .collect(Collectors.toSet());
        return GenericTemplateUtil.createGenericTemplate(recipientId, elementList);
    }
}
