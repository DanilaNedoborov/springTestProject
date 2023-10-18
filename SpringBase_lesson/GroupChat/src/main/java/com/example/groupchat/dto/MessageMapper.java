package com.example.groupchat.dto;

import com.example.groupchat.model.Massage;

import java.time.format.DateTimeFormatter;

public class MessageMapper {
    public static DtoMessage map (Massage message){
        DtoMessage dtoMessage = new DtoMessage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedString = message.getDateTime().format(formatter);
        dtoMessage.setDatetime(formattedString);
        dtoMessage.setText(message.getMassage());
        dtoMessage.setUsername(message.getUser().getName());
        return dtoMessage;
    }
}
