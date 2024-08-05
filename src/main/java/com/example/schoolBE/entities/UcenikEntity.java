package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
@Entity
public class UcenikEntity extends BaseEntity {
	@Column(name = "ime")
	@JsonView(Views.Private.class)
	private String ime;
	
	
	@Column(name = "prezime")
	@JsonView(Views.Private.class)
	private String prezime;
	
	@ManyToOne
	@JoinColumn(name="roditelj_id")
	private RoditeljEntity roditelj;

	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}
	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
}
