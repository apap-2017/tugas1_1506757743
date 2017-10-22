package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PendudukService {
	
	@Autowired
	PendudukMapper pendudukMapper;

	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
		log.info(pendudukMapper.selectPenduduk(nik).toString() , "");
		return pendudukMapper.selectPenduduk(nik);
	}
	
	public List<PendudukModel> selectPendudukFromKeluarga(String idKeluarga) {
		log.info("select penduduk from keluarga with idKeluarga {}", idKeluarga);
		log.info(pendudukMapper.selectPendudukFromKeluarga(idKeluarga).toString(), "");
		return pendudukMapper.selectPendudukFromKeluarga(idKeluarga);
	}
	
	public void addPenduduk(PendudukModel penduduk) {
		log.info("add penduduk {}", penduduk.toString());
		pendudukMapper.addPenduduk(penduduk);
	}
	
	public int countNIKPenduduk(String nik) {
		log.info("count nik penduduk {}", nik);
		log.info(pendudukMapper.countNIKPenduduk(nik) +"", "");
		return pendudukMapper.countNIKPenduduk(nik);
	}
	
	public int countPenduduk() {
		log.info("count penduduk");
		return pendudukMapper.countPenduduk();
	}
	
	public void updatePenduduk(PendudukModel penduduk) {
		log.info("update penduduk {}", penduduk);
		pendudukMapper.updatePenduduk(penduduk);
	}
	
	public List<PendudukModel> selectPendudukByIdKelurahan(String idKelurahan) {
		log.info("select penduduk by id kelurahan", idKelurahan);
		return pendudukMapper.selectPendudukByIdKelurahan(idKelurahan);
	}
}
