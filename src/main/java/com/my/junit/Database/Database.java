package com.my.junit.Database;

import java.util.Collection;
import java.util.UUID;

public interface Database<T extends IdentifiableObject> {
    public boolean Has(UUID id);

    public T Get(UUID id);

    public Collection<T> GetAll();

    public void Add(T object) throws Exception;
    
    public void Remove(UUID id) throws Exception;

    public void Update(T object) throws Exception;
}
