package com.vpavlenko.restTest.service;

import com.vpavlenko.restTest.model.TableMessage;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Optional<List<TableMessage>> getAll();
    TableMessage getMessageById(Long id);
    void deleteMessage(Long id);
    TableMessage updateMessage(TableMessage message);
    TableMessage addMessage(TableMessage message);
    TableMessage getMessageByOrderNumber(int orderNumber);

}
