package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	
	@NotNull
	private String idKeluarga;
	
	@NotNull
	private String nomorKK;
	
	@NotNull
	private String alamat;
	
	@NotNull
	private String rt;
	
	@NotNull
	private String rw;
	
	@NotNull
	private String idKelurahan;
	
	@NotNull
	private String isTidakBerlaku;
}
