package com.patrice.hibernate;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")

public class Student implements Serializable {
	
	private static final long serialVersionUID = -6192832626602644784L;
	@Id 
	@GeneratedValue 
	private int rollNumber; 
    private String name;

    public Student() {}

	public Student(String name) {
		this.name = name;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
        return "Student Object : " + rollNumber + ", name = " + name ;
    }


}
