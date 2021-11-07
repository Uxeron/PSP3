package com.my.junit.UserData;

import java.util.UUID;
import com.my.junit.Database.IdentifiableObject;

public class User extends IdentifiableObject {
    public String vardas;
    public String pavarde;
    public String telefonoNr;
    public String email;
    public String adresas;
    public String slaptazodis;

    public User(
        String vardas,
        String pavarde,
        String telefonoNr,
        String email,
        String adresas,
        String slaptazodis,
        UUID id)
    {
        super(id);
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.telefonoNr = telefonoNr;
        this.email = email;
        this.adresas = adresas;
        this.slaptazodis = slaptazodis;
    }

    public User(
        String vardas,
        String pavarde,
        String telefonoNr,
        String email,
        String adresas,
        String slaptazodis)
    {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.telefonoNr = telefonoNr;
        this.email = email;
        this.adresas = adresas;
        this.slaptazodis = slaptazodis;
    }
}
