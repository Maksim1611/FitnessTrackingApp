package app.user.model;

import lombok.Getter;

@Getter
public enum UserRole {

    USER("User"),
    ADMIN("Admin");

    UserRole(String displayName) {
    }
}
