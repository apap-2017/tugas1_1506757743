package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanService {
	
	@Autowired
	KecamatanMapper kecamatanMapper;

	public KecamatanModel selectKecamatan(String idKecamatan) {
		log.info("select kelurahan with id kelurahan {}", idKecamatan);
		log.info(kecamatanMapper.selectKecamatan(idKecamatan).toString(), "");
		return kecamatanMapper.selectKecamatan(idKecamatan);
	}
	
	public List<KecamatanModel> selectKecamatanByIdKota(String idKota) {
		log.info("select kecamatan by kota id {}", idKota);
		return kecamatanMapper.selectKecamatanByIdKota(idKota);
	}
}
