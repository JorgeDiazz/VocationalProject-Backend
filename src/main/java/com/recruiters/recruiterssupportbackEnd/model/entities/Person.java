
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.Arrays;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

/**
 *
 * @author Jhanuar Sanchez
 */
public class Person {
    
    @Id
    private String id;
    private String nit;
    private String name;
    private String email;
    private String number; 
    private Byte[] image;
    private String password;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public static boolean isCorrectForCreate(Person person) {
        return person != null && !StringUtils.isEmpty(person.getNit()) && !StringUtils.isEmpty(person.getName()) && !StringUtils.isEmpty(person.getEmail()) && !StringUtils.isEmpty(person.getNumber()) && !StringUtils.isEmpty(person.getAddress());
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", nit=" + nit + ", name=" + name + ", email=" + email + ", number=" + number + ", image=" + Arrays.toString(image) + ", password=" + password + ", address=" + address + '}';
    }
    
}
