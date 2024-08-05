package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Embeddable
@Entity
@Table(name = "raz")
public class Razred1Entity extends BaseEntity {
	
	@Column(name="ime_razreda")
	@JsonView(Views.Private.class)
	private String imeRazreda;

	public String getImeRazreda() {
		return imeRazreda;
	}

	public void setImeRazreda(String imeRazreda) {
		this.imeRazreda = imeRazreda;
	}

	public Razred1Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
