package com.maxcheung.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID of the customer */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    /** Lastname of the customer */
    private String lastname;

    @Column
    /** Firstname of the customer */
    private String firstname;

    public Customer() {

    }


    public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return String.format("Customer[ID= %d, Lastname= '%s', Firstname= '%s'", id, lastname,
                firstname);
    }

}