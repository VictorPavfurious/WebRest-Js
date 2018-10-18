package com.vpavlenko.restTest.controller;

import com.vpavlenko.restTest.model.TableMessage;
import com.vpavlenko.restTest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
       }
           catch (Exception e) {
               return ResponseEntity.notFound().build();
           }

    }

    @RequestMapping(value = "/api/v1/entity", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public TableMessage updateMessage(@RequestBody TableMessage tableMessage)
    {
         TableMessage currentMessage = messageService.getMessageById(tableMessage.getId());
//         int current = currentMessage.getOrderNumber();
//         int change = tableMessage.getOrderNumber();
//        if(current > change) {
//
//            TableMessage tableMessage1 = messageService.getMessageByOrderNumber(change);
//            tableMessage1.setOrderNumber(current);
//            messageService.updateMessage(tableMessage1);
//         } else if (current < change) {
//             TableMessage tableMessage1 = messageService.getMessageByOrderNumber(current);
//             tableMessage1.setOrderNumber(change);
//             messageService.updateMessage(tableMessage1);
//         }

        return messageService.updateMessage(tableMessage);
    }

    @RequestMapping (value = "/api/v1/entity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public TableMessage addMessage(@RequestBody TableMessage tableMessage) {
       return messageService.addMessage(tableMessage);
    }
}
