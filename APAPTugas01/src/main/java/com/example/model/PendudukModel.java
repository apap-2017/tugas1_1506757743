package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
	
	@NotNull
	private String idPenduduk;
	
	@NotNull
	private String nik;
	
	@NotNull
	private String nama;
	
	@NotNull
	private String tempatLahir;

	@NotNull
	private String tanggalLahir;
	
	@NotNull
	private Integer jenisKelamin;
	
	@NotNull
	private Integer isWni;
	
	@NotNull
	private String idKeluarga;
	
	@NotNull
	private String agama;
	
	@NotNull
	private String pekerjaan;
	
	@NotNull
	private String statusPerkawinan;
	
	@NotNull
	private String statusDalamKeluarga;
	
	@NotNull
	private String golonganDarah;
	
	@NotNull
	private String isWafat;
	
//	@NotNull
//	private String alamat;
//	
//	@NotNull
//	private String rt;
//	
//	@NotNull
//	private String rw;
//	
//	@NotNull
//	private String kelurahan;
//	
//	@NotNull
//	private String kecamatan;
//	
//	@NotNull
//	private String kota;
}
