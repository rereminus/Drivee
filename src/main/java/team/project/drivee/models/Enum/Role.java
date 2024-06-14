package team.project.drivee.models.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_DRIVER;

    @Override
    public String getAuthority() {
        return name();
    }
}