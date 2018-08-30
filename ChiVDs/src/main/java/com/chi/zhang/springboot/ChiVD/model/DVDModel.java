package com.chi.zhang.springboot.ChiVD.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dvd")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"rental_date"}, allowGetters = true)
public class DVDModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dvd_id;
	
	@NotBlank
	private String film_name;
		
	private Long rented_by_user;
	
	private Date rental_date;

	public Long getDvd_id() {
		return dvd_id;
	}

	public void setDvd_id(Long dvd_id) {
		this.dvd_id = dvd_id;
	}

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}
	
	public Long getRented_by_user() {
		return rented_by_user;
	}

	public void setRented_by_user(Long rented_by_user) {
		this.rented_by_user = rented_by_user;
	}
	
	public Date getRental_date() {
		return rental_date;
	}

	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}


}
