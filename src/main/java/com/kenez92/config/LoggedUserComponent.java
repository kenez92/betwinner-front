package com.kenez92.config;

import com.kenez92.domain.account.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@NoArgsConstructor
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoggedUserComponent {
    private UserDto userDto;
    private String token;
    private boolean isUserLoggedIn;

    public boolean isUserLoggedIn() {
        if (userDto != null && token != null) {
            return true;
        } else {
            return false;
        }
    }
}
