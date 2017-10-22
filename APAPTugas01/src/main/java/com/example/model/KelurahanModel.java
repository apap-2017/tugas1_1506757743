package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	
	@NotNull
	private String idKelurahan;
	
	@NotNull
	private String kodeKelurahan;
	
	@NotNull
	private String namaKelurahan;
	
	@NotNull
	private String idKecamatan;
	
	@NotNull
	private String kodePos;
	

}
