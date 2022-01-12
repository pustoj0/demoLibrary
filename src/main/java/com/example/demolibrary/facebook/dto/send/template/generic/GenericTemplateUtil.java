package com.example.demolibrary.facebook.dto.send.template.generic;

import com.example.demolibrary.facebook.dto.receive.library.LibraryDTO;
import com.example.demolibrary.facebook.dto.send.template.TemplateType;
import com.example.demolibrary.facebook.dto.send.template.generic.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenericTemplateUtil {
    public static GenericTemplateDTO createGenericTemplate(String recipientId, Set<Element> elementList){
        GenericTemplateDTO genericTemplateDTO = new GenericTemplateDTO();
        Recipient recipient = new Recipient(recipientId);
        Message message = new Message();
        Attachment attachment = new Attachment();
        message.setAttachment(attachment);
        attachment.setType("template");
        Payload payload = new Payload();
        attachment.setPayload(payload);
        payload.setTemplate_type(TemplateType.generic.name());
        payload.setElements(elementList);
        return new GenericTemplateDTO(recipient, message);
    }
}
