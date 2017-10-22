package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	
	@Select("select * from penduduk where nik = #{nik}") 
	@Results(value = {
			@Result(property = "idPenduduk", column = "id"),
			@Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"),
			@Result(property = "tempatLahir", column = "tempat_lahir"),
			@Result(property = "tanggalLahir", column = "tanggal_lahir"),
			@Result(property = "jenisKelamin", column = "jenis_kelamin"),
			@Result(property = "isWni", column = "is_wni"),
			@Result(property = "idKeluarga", column = "id_keluarga"),
			@Result(property = "agama", column = "agama"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "statusPerkawinan", column = "status_perkawinan"),
			@Result(property = "statusDalamKeluarga", column = "status_dalam_keluarga"),
			@Result(property = "golonganDarah", column = "golongan_darah"),
			@Result(property = "isWafat", column = "is_wafat")
	})
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Select("select * from penduduk where id_keluarga = #{idKeluarga}")
	@Results(value = {
			@Result(property = "idPenduduk", column = "id"),
			@Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"),
			@Result(property = "tempatLahir", column = "tempat_lahir"),
			@Result(property = "tanggalLahir", column = "tanggal_lahir"),
			@Result(property = "jenisKelamin", column = "jenis_kelamin"),
			@Result(property = "isWni", column = "is_wni"),
			@Result(property = "idKeluarga", column = "id_keluarga"),
			@Result(property = "agama", column = "agama"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "statusPerkawinan", column = "status_perkawinan"),
			@Result(property = "statusDalamKeluarga", column = "status_dalam_keluarga"),
			@Result(property = "golonganDarah", column = "golongan_darah"),
			@Result(property = "isWafat", column = "is_wafat")
	})
	List<PendudukModel> selectPendudukFromKeluarga(@Param("idKeluarga") String idKeluarga);
	
    @Insert("insert into penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) values (#{idPenduduk}, #{nik}, #{nama}, #{tempatLahir}, #{tanggalLahir}, #{jenisKelamin}, #{isWni}, #{idKeluarga}, #{agama}, #{pekerjaan}, #{statusPerkawinan}, #{statusDalamKeluarga}, #{golonganDarah}, #{isWafat})")
	void addPenduduk(PendudukModel penduduk);
    
    @Select("select count(nik) from penduduk where nik like #{nik}")
    int countNIKPenduduk(String nik);
    
    @Select("select count(*) from penduduk")
    int countPenduduk();
	
    @Update("update penduduk set nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempatLahir}, tanggal_lahir = #{tanggalLahir}, jenis_kelamin = #{jenisKelamin}, is_wni = #{isWni}, id_keluarga = #{idKeluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{statusPerkawinan}, status_dalam_keluarga = #{statusDalamKeluarga}, golongan_darah = #{golonganDarah}, is_wafat = #{isWafat} where id = #{idPenduduk}")
    void updatePenduduk(PendudukModel penduduk);
    
    @Select("select * from penduduk where id_keluarga IN (select id from keluarga where id_kelurahan = #{idKelurahan})")
    @Results(value = {
			@Result(property = "idPenduduk", column = "id"),
			@Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"),
			@Result(property = "tempatLahir", column = "tempat_lahir"),
			@Result(property = "tanggalLahir", column = "tanggal_lahir"),
			@Result(property = "jenisKelamin", column = "jenis_kelamin"),
			@Result(property = "isWni", column = "is_wni"),
			@Result(property = "idKeluarga", column = "id_keluarga"),
			@Result(property = "agama", column = "agama"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "statusPerkawinan", column = "status_perkawinan"),
			@Result(property = "statusDalamKeluarga", column = "status_dalam_keluarga"),
			@Result(property = "golonganDarah", column = "golongan_darah"),
			@Result(property = "isWafat", column = "is_wafat")
	})
    List<PendudukModel> selectPendudukByIdKelurahan(String idKelurahan);
}
