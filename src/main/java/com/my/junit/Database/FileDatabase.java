package com.my.junit.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class FileDatabase<T extends IdentifiableObject> implements Database<T> {
    protected File dbFile = null;
    protected ArrayList<T> objects; 

    public FileDatabase(String filename) throws Exception {
        dbFile = new File(filename);
        Load();
    }

    protected void Load() throws Exception {
        FileInputStream fi = new FileInputStream(dbFile);
        ObjectInputStream oi = new ObjectInputStream(fi);

        objects = (ArrayList<T>) oi.readObject();

        fi.close();
        oi.close();
    }

    protected void Save() throws Exception {
        FileOutputStream f = new FileOutputStream(dbFile);
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(objects);

        o.close();
        f.close();
    }

    public boolean Has(UUID id) {
        return objects.stream().filter(object -> id.equals(object.id)).count() > 0;
    }

    public T Get(UUID id) {
        return objects.stream().filter(object -> id.equals(object.id)).findFirst().orElse(null);
    }

    public ArrayList<T> GetAll() {
        return objects;
    }

    public void Add(T object) throws Exception {
        objects.add(object);
        Save();
    }
    
    public void Remove(UUID id) throws Exception  {
        objects.removeIf(obj -> obj.id == id);
        Save();
    }

    public void Update(T object) throws Exception  {
        UUID id = object.id;
        Remove(id);
        Add(object);
    }
}
