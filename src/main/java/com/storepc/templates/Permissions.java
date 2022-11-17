package com.storepc.templates;

public enum Permissions {

    READ("read"),
    CREATE("create"),
    WRITE("write");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
