package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KotaModel;

@Mapper
public interface KotaMapper {
	
	@Select("select * from kota where id = #{idKota}")
	@Results(value = {
			@Result(property = "idKota", column = "id"),
			@Result(property = "kodeKota", column = "kode_kota"),
			@Result(property = "namaKota", column = "nama_kota")
	})
	KotaModel selectKota(@Param("idKota") String idKota);
	
	@Select("select * from kota")
	@Results(value = {
			@Result(property = "idKota", column = "id"),
			@Result(property = "kodeKota", column = "kode_kota"),
			@Result(property = "namaKota", column = "nama_kota")
	})
	List<KotaModel> selectAllKota();
}
