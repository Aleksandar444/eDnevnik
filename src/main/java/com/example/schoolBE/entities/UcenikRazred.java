package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ucenik_razred")
public class UcenikRazred extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="ucenik_id")
	@JsonView(Views.Private.class)
	private UcenikEntity ucenik;
	
	
	
	@ManyToOne
	@JoinColumn(name="razred_id")
	@JsonView(Views.Private.class)
	private Razred1Entity razred;



	public UcenikEntity getUcenik() {
		return ucenik;
	}



	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}



	public Razred1Entity getRazred() {
		return razred;
	}



	public void setRazred(Razred1Entity razred) {
		this.razred = razred;
	}



	public UcenikRazred() {
		super();
		// TODO Auto-generated constructor stub
	}
}
