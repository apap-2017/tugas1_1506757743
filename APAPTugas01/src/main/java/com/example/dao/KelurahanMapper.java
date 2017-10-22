package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {

	@Select("select * from kelurahan where id = #{idKelurahan}")
	@Results(value = {
			@Result(property = "idKelurahan", column = "id"),
			@Result(property = "kodeKelurahan", column = "kode_kelurahan"),
			@Result(property = "namaKelurahan", column = "nama_kelurahan"),
			@Result(property = "idKecamatan", column = "id_kecamatan"),
			@Result(property = "kodePos", column = "kode_pos")
	})
	KelurahanModel selectKelurahan(String idKelurahan);
	
	@Select("select * from kelurahan")
	@Results(value = {
			@Result(property = "idKelurahan", column = "id"),
			@Result(property = "kodeKelurahan", column = "kode_kelurahan"),
			@Result(property = "namaKelurahan", column = "nama_kelurahan"),
			@Result(property = "idKecamatan", column = "id_kecamatan"),
			@Result(property = "kodePos", column = "kode_pos")
	})
	List<KelurahanModel> selectAllKelurahan();
	
	@Select("select * from kelurahan where id_kecamatan = #{idKecamatan}")
	@Results(value = {
			@Result(property = "idKelurahan", column = "id"),
			@Result(property = "kodeKelurahan", column = "kode_kelurahan"),
			@Result(property = "namaKelurahan", column = "nama_kelurahan"),
			@Result(property = "idKecamatan", column = "id_kecamatan"),
			@Result(property = "kodePos", column = "kode_pos")
	})
	List<KelurahanModel> selectKelurahanByIdKecamatan(String idKecamatan);
}
