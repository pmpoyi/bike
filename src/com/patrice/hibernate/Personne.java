package com.patrice.hibernate;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Personne
 *
 */
@Entity
@Table(name="Personne")
public class Personne implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@Basic
	private String nom;
	@Basic
	private String prenom;

	public Personne() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
}
