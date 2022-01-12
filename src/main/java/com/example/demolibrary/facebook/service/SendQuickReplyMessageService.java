package com.example.demolibrary.facebook.service;

import com.example.demolibrary.facebook.dto.send.quickreply.QuickReply;
import com.example.demolibrary.facebook.dto.send.quickreply.QuickReplyDTO;

import java.util.List;

public interface SendQuickReplyMessageService {
    void sendQuickReplyMessage(QuickReplyDTO quickReplyDTO);
}
