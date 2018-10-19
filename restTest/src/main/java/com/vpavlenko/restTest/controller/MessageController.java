package com.vpavlenko.restTest.controller;

import com.vpavlenko.restTest.model.TableMessage;
import com.vpavlenko.restTest.service.MessageService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/api/v1/entity/list", method = RequestMethod.GET)
    public ResponseEntity getMessages() {
        return messageService.getAll()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @RequestMapping(value = "/api/v1/entity", method = RequestMethod.GET)
    public TableMessage getById(@RequestBody TableMessage tableMessage) {
        return messageService.getMessageById(tableMessage.getId());
    }

    @RequestMapping(value = "/api/v1/entity", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<Void> deleteMessage(@RequestBody TableMessage tableMessage) {
        try {
            messageService.deleteMessage(tableMessage.getId());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/v1/entity", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMessage(@RequestBody JSONObject payload)
    {

        String text = payload.getAsString("text");
        TableMessage currentMessage = messageService.getMessageById(payload.getAsNumber("id").intValue());
        try {
            int change = payload.getAsNumber("change").intValue();
            if (change > 0) {
                TableMessage prevMessage = messageService.getMessageById(payload.getAsNumber("change").intValue());
                int temp = currentMessage.getOrderNumber();
                currentMessage.setOrderNumber(prevMessage.getOrderNumber());
                prevMessage.setOrderNumber(temp);
                messageService.updateMessage(prevMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        currentMessage.setText(text);
        messageService.updateMessage(currentMessage);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping (value = "/api/v1/entity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public TableMessage addMessage(@RequestBody TableMessage tableMessage) {
       return messageService.addMessage(tableMessage);
    }
}
