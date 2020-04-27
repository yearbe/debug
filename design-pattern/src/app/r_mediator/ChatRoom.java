package app.r_mediator;

import java.time.LocalDateTime;

public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(LocalDateTime.now().toString() + " [" + user.getName() + "] : " + message);
    }
}