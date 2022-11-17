package com.storepc.templates;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permissions.READ)),
    ADMIN(Set.of(Permissions.READ, Permissions.CREATE)),
    DIRECTOR(Set.of(Permissions.READ, Permissions.CREATE, Permissions.WRITE));

    private final Set<Permissions> permissions;


    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
