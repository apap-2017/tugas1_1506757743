package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
	
	@NotNull
	private String idKecamatan;
	
	@NotNull
	private String kodeKecamatan;
	
	@NotNull
	private String namaKecamatan;
	
	@NotNull
	private String idKota;

}
