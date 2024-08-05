package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="izostanci_entity")
public class IzostanciEntity extends BaseEntity {
	@Column(name="opravdani_izostanci")
	@JsonView(Views.Private.class)
	private String opravdaniIzostanci;
	
	
	@Column(name="neopravdani_izostanci")
	@JsonView(Views.Private.class)
	private String neopravdaniIzostanci;
	
	@Column(name="datum_izostanka")
	@JsonView(Views.Private.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private String datumIzostanka;
	
	@ManyToOne
	@JoinColumn(name="ucenik_id")
	@JsonIgnoreProperties({"roditelj", "razred"})
	private UcenikEntity ucenik;

	public IzostanciEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOpravdaniIzostanci() {
		return opravdaniIzostanci;
	}

	public void setOpravdaniIzostanci(String opravdaniIzostanci) {
		this.opravdaniIzostanci = opravdaniIzostanci;
	}

	public String getNeopravdaniIzostanci() {
		return neopravdaniIzostanci;
	}

	public void setNeopravdaniIzostanci(String neopravdaniIzostanci) {
		this.neopravdaniIzostanci = neopravdaniIzostanci;
	}

	public String getDatumIzostanka() {
		return datumIzostanka;
	}

	public void setDatumIzostanka(String datumIzostanka) {
		this.datumIzostanka = datumIzostanka;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}
}
