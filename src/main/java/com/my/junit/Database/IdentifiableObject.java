package com.my.junit.Database;

import java.io.Serializable;
import java.util.UUID;

public abstract class IdentifiableObject implements Serializable{
    public UUID id;

    public IdentifiableObject() {
        this.id = UUID.randomUUID();
    }

    public IdentifiableObject(UUID id) {
        this.id = id;
    }
}
