package ru.javastudy.springMVC.impl;

import java.util.List;
import ru.javastudy.springMVC.model.MessageModel;

public interface MessageService {
    List<MessageModel> getMessages();

    MessageModel getMessageById(Integer var1);

    MessageModel createMessage(MessageModel var1);
}
