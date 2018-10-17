package com.vpavlenko.restTest.service;

import com.vpavlenko.restTest.model.TableMessage;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Optional<List<TableMessage>> getAll();
    TableMessage getMessageById(Long id);
    void deleteMessage(Long id);
    void updateMessage(TableMessage message);
    void saveMessage(TableMessage message);
    void addMessage(TableMessage message);

}
