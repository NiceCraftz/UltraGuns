package tech.calista.ultraguns.utils.chat;


import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ChatUtils {
    public String color(String message) {
        return message.replace('&', '§');
    }

    public List<String> color(List<String> messages) {
        List<String> list = new ArrayList<>();
        for (String message : messages) {
            String color = color(message);
            list.add(color);
        }
        return list;
    }
}
