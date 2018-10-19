package com.vpavlenko.restTest.service;

import com.vpavlenko.restTest.model.TableMessage;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Optional<List<TableMessage>> getAll();
    TableMessage getMessageById(int id);
    void deleteMessage(int id);
    TableMessage updateMessage(TableMessage message);
    TableMessage addMessage(TableMessage message);
    Sort sortByOrderNumberAsc();

}
