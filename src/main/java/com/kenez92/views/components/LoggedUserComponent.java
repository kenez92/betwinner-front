package com.kenez92.views.components;

import com.kenez92.config.Consts;
import com.kenez92.domain.account.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Component
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoggedUserComponent {
    private UserDto userDto;
    private String token;
    private boolean isUserLoggedIn;
    private Long expirationDate;

    public LoggedUserComponent() {
        this.expirationDate = new Date().getTime() + Consts.TOKEN_AVAILABILITY;
    }

    public boolean isUserLoggedIn() {
        if (userDto != null && token != null && new Date().getTime() < expirationDate) {
            return true;
        } else {
            //Notification.show(Consts.ERR_YOU_ARE_NOT_LOGGED_IN, 5000, Notification.Position.TOP_CENTER);
            return false;
        }
    }
}
