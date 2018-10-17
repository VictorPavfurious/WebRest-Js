package com.vpavlenko.restTest.controller;

import com.vpavlenko.restTest.model.TableMessage;
import com.vpavlenko.restTest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class RestApiController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getMessages() {
        return messageService.getAll()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TableMessage saveMessages(@RequestBody TableMessage message) {
         messageService.saveMessage(message);
         return messageService.getMessageById(message.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TableMessage getById(@PathVariable("id") Long id) {
        return messageService.getMessageById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable ("id") Long id) {
        messageService.deleteMessage(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateMessage(@RequestBody TableMessage tableMessage) {
        messageService.updateMessage(tableMessage);
    }

    @RequestMapping (value = "/add", method = RequestMethod.POST)
    public void addMessage(@RequestBody TableMessage tableMessage) {
        messageService.addMessage(tableMessage);
    }
}
