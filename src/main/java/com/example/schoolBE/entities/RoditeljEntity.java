package com.example.schoolBE.entities;
import org.hibernate.annotations.View;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roditelj_entity")
public class RoditeljEntity extends BaseEntity {
	@Column(name = "ime_roditelja")
	@JsonView(Views.Private.class)
	private String imeRoditelja;
	
	@Column(name = "prezime_roditelja")
	@JsonView(Views.Private.class)
	private String prezimeRoditelja;
	
	@Column(name="email_roditelja")
	@JsonView(Views.Private.class)
	private String emailRoditelja;


	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}

	public String getPrezimeRoditelja() {
		return prezimeRoditelja;
	}

	public void setPrezimeRoditelja(String prezimeRoditelja) {
		this.prezimeRoditelja = prezimeRoditelja;
	}

	public String getEmailRoditelja() {
		return emailRoditelja;
	}

	public void setEmailRoditelja(String emailRoditelja) {
		this.emailRoditelja = emailRoditelja;
	}
	
	
}
