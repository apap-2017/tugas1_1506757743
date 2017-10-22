package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanService {
	
	@Autowired
	KelurahanMapper kelurahanMapper;
	
	public KelurahanModel selectKelurahan(String idKelurahan) {
		log.info("select kelurahan with id kelurahan {}", idKelurahan);
		log.info(kelurahanMapper.selectKelurahan(idKelurahan).toString(), "");
		return kelurahanMapper.selectKelurahan(idKelurahan);
	}
	
	public List<KelurahanModel> selectAllKelurahan() {
		log.info("select all kelurahan");
		return kelurahanMapper.selectAllKelurahan();
	}
	
	public List<KelurahanModel> selectKelurahanByIdKecamatan(String idKecamatan) {
		log.info("select kelurahan by id kecamatan {}", idKecamatan);
		return kelurahanMapper.selectKelurahanByIdKecamatan(idKecamatan);
	}
}
