package com.example.schoolBE.entities;

import com.example.homeworkProject.security.Views;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="ocena_entity")
public class OcenaEntity extends BaseEntity {
	@Column(name="ocena",nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private Integer ocena;
	
	@Column(name = "prvo_polugodiste", nullable = true)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int prvoPolugodiste;
	
	@Column(name = "drugo_polugodiste", nullable = true)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int drugoPolugodiste;
	
	@Column(name = "zakljucne_ocene", nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int zakljucnaOcena;
	
	@Column(name = "vladanje", nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int vladanje;
	
	@Column(name = "pismeni_test", nullable = false)
	@JsonView(Views.Private.class)
	private int pismeni;
	
	@Column(name = "usmeni_test", nullable = false)
	@JsonView(Views.Private.class)
	private int usmeni;
	
	@ManyToOne
	@JoinColumn(name = "predmet_id")
	@JsonView(Views.Private.class)
	private PredmetEntity predmet;
	
	@ManyToOne
	@JoinColumn(name = "nastavnik_id")
	@JsonView(Views.Private.class)
	private NastavnikEntity nastavnik;
	
	
	@ManyToOne
	@JoinColumn(name = "ucenik_id",nullable = false)
	@JsonView(Views.Private.class)
	@NotNull(message = "Ucenik ne moze biti null")
    @JsonIgnoreProperties({"roditelj","razred","nastavnik"})
	private UcenikEntity ucenik;
	
	



	public int getPrvoPolugodiste() {
		return prvoPolugodiste;
	}





	public void setPrvoPolugodiste(int prvoPolugodiste) {
		this.prvoPolugodiste = prvoPolugodiste;
	}





	public int getDrugoPolugodiste() {
		return drugoPolugodiste;
	}





	public void setDrugoPolugodiste(int drugoPolugodiste) {
		this.drugoPolugodiste = drugoPolugodiste;
	}





	public int getZakljucnaOcena() {
		return zakljucnaOcena;
	}





	public void setZakljucnaOcena(int zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}





	public int getVladanje() {
		return vladanje;
	}





	public void setVladanje(int vladanje) {
		this.vladanje = vladanje;
	}





	public int getPismeni() {
		return pismeni;
	}





	public void setPismeni(int pismeni) {
		this.pismeni = pismeni;
	}





	public int getUsmeni() {
		return usmeni;
	}





	public void setUsmeni(int usmeni) {
		this.usmeni = usmeni;
	}





	public PredmetEntity getPredmet() {
		return predmet;
	}





	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}





	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}





	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}





	public UcenikEntity getUcenik() {
		return ucenik;
	}





	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}





	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Integer getOcena() {
		return ocena;
	}





	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}



	
	
	
}
