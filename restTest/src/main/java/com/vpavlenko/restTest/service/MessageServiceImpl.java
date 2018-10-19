package com.vpavlenko.restTest.service;

import com.vpavlenko.restTest.messagedao.MessageDao;
import com.vpavlenko.restTest.model.TableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public Optional<List<TableMessage>> getAll() {


        return Optional.ofNullable(messageDao.findAll(sortByOrderNumberAsc()));
    }

    @Override
    public TableMessage getMessageById(int id)
    {
        return messageDao.getOne(id);
    }

    @Override
    public void deleteMessage(int id) {
        messageDao.deleteById(id);
    }


    @Override
    public TableMessage updateMessage(TableMessage message) {
        return messageDao.save(message);
    }

    @Override
    public TableMessage addMessage(TableMessage message) {
        return messageDao.save(message);
    }

        @Override
    public Sort sortByOrderNumberAsc() {
        return new Sort(Sort.Direction.ASC, "orderNumber");
    }
}
