package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "predmet_entity")
public class PredmetEntity extends BaseEntity {
	
	@Column(name = "ime_Predmeta")
	@JsonView(Views.Public.class)
	private String imePredmeta;
	
	
	@Column(name = "nedeljni_fond_caosva")
	@JsonView(Views.Public.class)
	private Integer NedeljniFondCasova;
	
	public PredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImePredmeta() {
		return imePredmeta;
	}
	public void setImePredmeta(String imePredmeta) {
		this.imePredmeta = imePredmeta;
	}

	public Integer getNedeljniFondCasova() {
		return NedeljniFondCasova;
	}

	public void setNedeljniFondCasova(Integer nedeljniFondCasova) {
		NedeljniFondCasova = nedeljniFondCasova;
	}
	
	
	
	
	
}
