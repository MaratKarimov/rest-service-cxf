package ru.bva.impl;

import java.util.List;
import ru.bva.model.MessageModel;

public interface MessageService {
    List<MessageModel> getMessages();

    MessageModel getMessageById(Integer var1);

    MessageModel createMessage(MessageModel var1);
}
