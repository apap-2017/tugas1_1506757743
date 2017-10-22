package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaService {

	@Autowired
	KeluargaMapper keluargaMapper;

	public KeluargaModel selectKeluarga(String idKeluarga) {
		log.info("select keluarga with no idKeluarga {}", idKeluarga);
		log.info(keluargaMapper.selectKeluarga(idKeluarga).toString(), "");
		return keluargaMapper.selectKeluarga(idKeluarga);
	}
	
	public KeluargaModel selectKeluargaByNKK(String nomorKK) {
		log.info("select keluarga with no KK {}", nomorKK);
		log.info(keluargaMapper.selectKeluargaByNKK(nomorKK).toString(), "");
		return keluargaMapper.selectKeluargaByNKK(nomorKK);
	}
	
	public void addKeluarga(KeluargaModel keluarga) {
		log.info("add keluarga {}", keluarga);
		keluargaMapper.addKeluarga(keluarga);
	}
	
	public int countNKKKeluarga(String nkk) {
		log.info("count nkk keluarga with nkk {}", nkk);
		return keluargaMapper.countNKKKeluarga(nkk);
	}
	
	public int countKeluarga() {
		log.info("count keluarga");
		return keluargaMapper.countKeluarga();
	}
	
	public void updateKeluarga(KeluargaModel keluarga) {
		log.info("update keluarga {}", keluarga);
		keluargaMapper.updateKeluarga(keluarga);
	}
	
}
