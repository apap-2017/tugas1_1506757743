package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KotaMapper;
import com.example.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaService {

	@Autowired
	KotaMapper kotaMapper;
	
	public KotaModel selectKota(String idKota) {
		log.info("select kota with id kota {}", idKota);
		log.info(kotaMapper.selectKota(idKota).toString(), "");
		return kotaMapper.selectKota(idKota);
	}
	
	public List<KotaModel> selectAllKota() {
		log.info("select all kota");
		return kotaMapper.selectAllKota();
	}
}
