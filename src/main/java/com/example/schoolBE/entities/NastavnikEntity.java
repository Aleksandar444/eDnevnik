package com.example.schoolBE.entities;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.schoolBE.security.Views;
import com.example.schoolBE.services.LoggerService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="nastavnik_entity")
public class NastavnikEntity extends BaseEntity {

	@Column(name="ime")
	@JsonView(Views.Private.class)
	private String ime;
	
	
	@Column(name="prezime")
	@JsonView(Views.Private.class)
	private String prezime;
	
	public NastavnikEntity() {
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
