package com.example.schoolBE.entities;

import com.example.homeworkProject.security.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="korisnik")
public class KorisnikEntity extends BaseEntity {
	
	
	@Column(name="korisnicko_ime")
	@JsonView(Views.Admin.class)
	private String korisnickoIme;
	
	@Column(name = "sifra", nullable = false)
	@JsonIgnore
	private String sifra;
	
	@Column(name = "uloga", nullable = true)
	@JsonView(Views.Private.class)
	private String uloga;

	public KorisnikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
}
