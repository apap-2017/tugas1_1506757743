package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KotaModel {
	
	@NotNull
	private String idKota;
	
	@NotNull
	private String kodeKota;
	
	@NotNull
	private String namaKota;

}
