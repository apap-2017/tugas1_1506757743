package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KeluargaModel;

@Mapper
public interface KeluargaMapper {
	
	@Select("select * from keluarga where id = #{id}")
	@Results(value = {
			@Result(property = "idKeluarga", column = "id"),
			@Result(property = "nomorKK", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"),
			@Result(property = "idKelurahan", column = "id_kelurahan"),
			@Result(property = "isTidakBerlaku", column = "is_tidak_berlaku")
	})
	KeluargaModel selectKeluarga(@Param("id") String idKeluarga);
	
	@Select("select * from keluarga where nomor_kk = #{nomorKK}")
	@Results(value = {
			@Result(property = "idKeluarga", column = "id"),
			@Result(property = "nomorKK", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"),
			@Result(property = "idKelurahan", column = "id_kelurahan"),
			@Result(property = "isTidakBerlaku", column = "is_tidak_berlaku")
	})
	KeluargaModel selectKeluargaByNKK(@Param("nomorKK") String nomorKK);
	
	@Insert("insert into keluarga (id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) values (#{idKeluarga}, #{nomorKK}, #{alamat}, #{rt}, #{rw}, #{idKelurahan}, #{isTidakBerlaku})")
	void addKeluarga(KeluargaModel keluarga);
	
	@Select("select count(nomor_kk) from keluarga where nomor_kk like #{nkk}")
    int countNKKKeluarga(String nkk);
    
    @Select("select count(*) from keluarga")
    int countKeluarga();
    
    @Update("update keluarga set nomor_kk = #{nomorKK}, alamat = #{alamat}, rt = #{rt}, rw = #{rw}, id_kelurahan = #{idKelurahan}, is_tidak_berlaku = #{isTidakBerlaku} where id = #{idKeluarga}")
    void updateKeluarga(KeluargaModel keluarga);
}
