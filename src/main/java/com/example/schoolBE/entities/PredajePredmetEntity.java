package com.example.schoolBE.entities;

import com.example.schoolBE.security.Views;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="predaje_predmet")
public class PredajePredmetEntity extends BaseEntity {

		@ManyToOne
		@JoinColumn(name="nastavnik_ïd")
		@JsonView(Views.Private.class)
		private NastavnikEntity nastavnik;
		
		@ManyToOne
		@JoinColumn(name="predmet_ïd")
		@JsonView(Views.Private.class)
		private PredmetEntity predmet;
		

		public PredajePredmetEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public NastavnikEntity getNastavnik() {
			return nastavnik;
		}

		public void setNastavnik(NastavnikEntity nastavnik) {
			this.nastavnik = nastavnik;
			/*if (nastavnik != null && predmet != null) {
	            nastavnik.setPredmet(predmet.getId());
	        }*/
		}

		public PredmetEntity getPredmet() {
			return predmet;
		}

		public void setPredmet(PredmetEntity predmet) {
			this.predmet = predmet;
		}
		
		
}
