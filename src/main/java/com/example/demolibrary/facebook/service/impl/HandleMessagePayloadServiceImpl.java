package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.client.GutendexClient;
import com.example.demolibrary.facebook.dto.receive.message.MessagePayloadDTO;
import com.example.demolibrary.facebook.dto.receive.message.Postback;
import com.example.demolibrary.facebook.dto.send.textmessage.TextMessageUtil;
import com.example.demolibrary.facebook.service.HandleMessagePayloadService;
import com.example.demolibrary.facebook.flow.Flow;
import com.example.demolibrary.facebook.service.SendMessageService;
import com.example.demolibrary.model.Book;
import com.example.demolibrary.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Log4j2
@AllArgsConstructor
public class HandleMessagePayloadServiceImpl implements HandleMessagePayloadService {
    @Resource(name = "stringFlowMap")
    private Map<String, Flow> stringFlowMap;
    private GutendexClient gutendexClient;
    private BookService bookService;
    private SendMessageService messageService;
    private static final String REGEX = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int PAYLOAD_TEXT_INDEX = 1;
    private static final int ITEM_INDEX = 2;

    @Override
    public void handle(MessagePayloadDTO messagePayloadDTO) {
        messagePayloadDTO.getEntry().forEach(entry -> {
            entry.getMessaging().forEach(messaging -> {
                String recipientId = messaging.getSender().getId();
                Postback postback = messaging.getPostback();
                if (postback != null) {
                    handlePostback(postback, recipientId);
                }
            });
        });
    }

    private void handlePostback(Postback postback, String recipientId) {
        if (stringFlowMap.containsKey(postback.getPayload())) {
            stringFlowMap.get(postback.getPayload()).run(recipientId);
        } else {
            String[] strings = postback.getPayload().split(REGEX);
            String operation = strings[OPERATION_INDEX];
            String payload = strings[PAYLOAD_TEXT_INDEX];
            String itemId = strings[ITEM_INDEX];
            if (payload.equals("book_id")) {
                Book book = gutendexClient.getBookById(Long.parseLong(itemId));
                if (operation.equals("add")) {
                    bookService.addBookToCatalog(Long.parseLong(itemId), recipientId);
                    messageService.sendMessage(
                            TextMessageUtil.createTextMessage(
                                    recipientId, book.getTitle() + " was added to your catalog"));
                }
                if (operation.equals("delete")) {
                    bookService.deleteBookFromCatalog(Long.parseLong(itemId), recipientId);
                    messageService.sendMessage(
                            TextMessageUtil.createTextMessage(
                                    recipientId, book.getTitle() + " was deleted from your catalog"));
                }
            }
        }
    }
}
