package app.model.response;

import java.util.Objects;


// Data Transfer Object
// Объект для ответа на работу с сессией (register/login)
public class AuthSessionResponseDTO {
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

}
