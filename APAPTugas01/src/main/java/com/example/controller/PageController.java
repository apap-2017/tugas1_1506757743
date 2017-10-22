package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PageController {
	
	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO; 
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;
	
	@RequestMapping("/")
    public String index ()
    {
        return "index"; 
    }
	
	@RequestMapping("/penduduk")
	public String viewPenduduk(Model model, @RequestParam(value = "nik", required = true) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		
		if(penduduk != null) {		
			String tanggalLahir = penduduk.getTanggalLahir();
			String[] arrTglLahir = tanggalLahir.split("-");
			String bulan = arrTglLahir[1].toString();
			System.out.println(bulan);
			switch (bulan) {
			case "01":
				bulan = "Januari";
				break;
			case "02":
				bulan = "Februari"; 
				break;
			case "03":
				bulan = "Maret"; 
				break;
			case "04":
				bulan = "April"; 
				break;
			case "05":
				bulan = "Mei"; 
				break;
			case "06":
				bulan = "Juni";
				break;
			case "07": 
				bulan = "Juli"; 
				break;
			case "08":
				bulan = "Agustus"; 
				break;
			case "09":
				bulan = "September"; 
				break;
			case "10":
				bulan = "Oktober";
				break;
			case "11":
				bulan = "November"; 
				break;
			case "12":
				bulan = "Desember"; 
				break;
			default:
				break;
			}
			tanggalLahir = arrTglLahir[2] + " " + bulan + " " + arrTglLahir[0];
			
			model.addAttribute("tanggalLahir", tanggalLahir);
			
			model.addAttribute("penduduk", penduduk);

			KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
			model.addAttribute("keluarga", keluarga);
			
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
			model.addAttribute("kelurahan", kelurahan);
			
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
			model.addAttribute("kecamatan", kecamatan);
			
			KotaModel kota = kotaDAO.selectKota(kecamatan.getIdKota());
			model.addAttribute("kota", kota);
			
			return "penduduk-view";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/keluarga")
	public String viewKeluarga(Model model, @RequestParam(value = "nkk", required = true) String nomorKK) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nomorKK);
		if(keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
			model.addAttribute("kelurahan", kelurahan);
			
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
			model.addAttribute("kecamatan", kecamatan);
			
			KotaModel kota = kotaDAO.selectKota(kecamatan.getIdKota());
			model.addAttribute("kota", kota);
			
			List<PendudukModel> anggota = pendudukDAO.selectPendudukFromKeluarga(keluarga.getIdKeluarga());
			model.addAttribute("anggota", anggota);
			
			return "keluarga-view";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.GET)
	public String addPenduduk() {
		return "penduduk-add";
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String submitAddPenduduk(Model model, @Valid @ModelAttribute PendudukModel penduduk, BindingResult result) {
		log.info("add penduduk {}", penduduk);
		
		if(result.hasErrors()) {
			log.info("error {}", result.getAllErrors());
			return "penduduk-add";
		}
		
		if(!penduduk.getTanggalLahir().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
			log.info("salah format tanggal");
			return "penduduk-add";
		}
		
		String[] tanggal = penduduk.getTanggalLahir().split("-");
		int hari = Integer.parseInt(tanggal[2]);
		int bulan = Integer.parseInt(tanggal[1]);
		int tahun = Integer.parseInt(tanggal[0]) % 100;
		
		if(penduduk.getJenisKelamin() == 1) {
			hari += 40;
		}
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
		
		String nik = "";
		String kodeProvinsiKotaKecamatan = kecamatan.getKodeKecamatan().substring(0,6);
		nik = kodeProvinsiKotaKecamatan + String.format("%02d", hari) + String.format("%02d", bulan) + String.format("%02d", tahun);

		int jumlahNIK = pendudukDAO.countNIKPenduduk(nik + "%");
		int jumlahPenduduk = pendudukDAO.countPenduduk();
		
		penduduk.setNik(nik + String.format("%04d", jumlahNIK + 1));
		penduduk.setIdPenduduk(String.valueOf(jumlahPenduduk + 1));
		
		pendudukDAO.addPenduduk(penduduk);
		model.addAttribute("penduduk", penduduk);
		
		return "penduduk-success-add";
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.GET)
	public String addKeluarga(Model model) {
		List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
		model.addAttribute("listKelurahan", listKelurahan);
		return "keluarga-add";
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String submitAddKeluarga(Model model, @Valid @ModelAttribute KeluargaModel keluarga, BindingResult result) {
		log.info("add keluarga {}", keluarga);
		
		if(result.hasErrors()) {
			log.info("error {}", result.getAllErrors());
			List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("listKelurahan", listKelurahan);
			return "keluarga-add";
		}
		
		if(keluarga.getRt().length() != 3 || keluarga.getRw().length() != 3) {
			log.info("RT dan RW harus diisi 3 digit");
			List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("listKelurahan", listKelurahan);
			return "keluarga-add";
		}
		
		String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
		
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
		
		String nkk = kecamatan.getKodeKecamatan().substring(0,6) + date;
		
		int jumlahNKK = keluargaDAO.countNKKKeluarga(nkk + "%");
		int jumlahKeluarga = keluargaDAO.countKeluarga();
		
		keluarga.setNomorKK(nkk + String.format("%04d", jumlahNKK + 1)); 
		keluarga.setIdKeluarga(String.valueOf(jumlahKeluarga + 1));
		
		keluargaDAO.addKeluarga(keluarga);
		model.addAttribute("keluarga", keluarga);
		
		return "keluarga-success-add";
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.GET)
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if(penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "penduduk-update";
		}
		
		return "error";
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String submitUpdatePenduduk(Model model, @PathVariable(value = "nik") String nik, @Valid @ModelAttribute PendudukModel penduduk, BindingResult result) {
		log.info("ubah penduduk {}", penduduk);
		
		if(result.hasErrors()) {
			log.info("error {}", result.getAllErrors());
			return "penduduk-add";
		}
		
		PendudukModel dataPendudukAsli = pendudukDAO.selectPenduduk(nik);
		
		if(!penduduk.getIdKeluarga().equals(dataPendudukAsli.getIdKeluarga()) || !penduduk.getTanggalLahir().equals(dataPendudukAsli.getTanggalLahir()) 
				|| !penduduk.getJenisKelamin().equals(dataPendudukAsli.getJenisKelamin())) {
			String[] date = penduduk.getTanggalLahir().split("-");
			int hari = Integer.parseInt(date[2]);
			int bulan = Integer.parseInt(date[1]);
			int tahun = Integer.parseInt(date[0]) % 100;
			
			if(penduduk.getJenisKelamin() == 1) {
				hari += 40;
			}
			
			KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
			
			String nikBaru = "";
			String kodeProvinsiKotaKecamatan = kecamatan.getKodeKecamatan().substring(0,6);
			nikBaru = kodeProvinsiKotaKecamatan + String.format("%02d", hari) + String.format("%02d", bulan) + String.format("%02d", tahun);

			int jumlahNIK = pendudukDAO.countNIKPenduduk(nikBaru + "%");
			penduduk.setNik(nikBaru + String.format("%04d", jumlahNIK + 1));
		}
		
		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("nik", nik);
		return "penduduk-success-update";
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.GET)
	public String updateKeluaga(Model model, @PathVariable(value = "nkk") String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nkk);
		if(keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			
			List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("listKelurahan", listKelurahan);
			return "keluarga-update";
		}
		return "error";
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
	public String submitUpdateKeluarga(Model model, @PathVariable(value = "nkk") String nkk, @Valid @ModelAttribute KeluargaModel keluarga, BindingResult result) {
		log.info("ubah keluarga {}", keluarga);
		KeluargaModel dataKeluargaAsli = keluargaDAO.selectKeluargaByNKK(nkk);
		
		if(result.hasErrors()) {
			log.info("error {}", result.getAllErrors());
			List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("listKelurahan", listKelurahan);
			return "keluarga-update";
		}
		
		if(keluarga.getRt().length() != 3 || keluarga.getRw().length() != 3) {
			log.info("RT dan RW harus diisi 3 digit");
			List<KelurahanModel> listKelurahan = kelurahanDAO.selectAllKelurahan();
			model.addAttribute("listKelurahan", listKelurahan);
			return "keluarga-update";
		}
		
		if(!keluarga.getIdKelurahan().equals(dataKeluargaAsli.getIdKelurahan())) {
			String date = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
			
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getIdKecamatan());
			
			String nkkBaru = kecamatan.getKodeKecamatan().substring(0,6) + date;
			int jumlahNKK = keluargaDAO.countNKKKeluarga(nkkBaru + "%");
			keluarga.setNomorKK(nkkBaru + String.format("%04d", jumlahNKK + 1));
			
			List<PendudukModel> listPenduduk = pendudukDAO.selectPendudukFromKeluarga(keluarga.getIdKeluarga());
			for(int i = 0; i < listPenduduk.size(); i++) {
				PendudukModel penduduk = listPenduduk.get(i);
				log.info(i + ". penduduk {}", penduduk);
				
				String[] tanggal = penduduk.getTanggalLahir().split("-");
				int hari = Integer.parseInt(tanggal[2]);
				int bulan = Integer.parseInt(tanggal[1]);
				int tahun = Integer.parseInt(tanggal[0]) % 100;
				
				if(penduduk.getJenisKelamin() == 1) {
					hari += 40;
				}
				
				String nikBaru = "";
				String kodeProvinsiKotaKecamatan = kecamatan.getKodeKecamatan().substring(0,6);
				nikBaru = kodeProvinsiKotaKecamatan + String.format("%02d", hari) + String.format("%02d", bulan) + String.format("%02d", tahun);

				int jumlahNIK = pendudukDAO.countNIKPenduduk(nikBaru + "%");
				penduduk.setNik(nikBaru + String.format("%04d", jumlahNIK + 1));
				
				pendudukDAO.updatePenduduk(penduduk);
			}
		}
		
		keluargaDAO.updateKeluarga(keluarga);
		model.addAttribute("nkk", nkk);
		return "keluarga-success-update";
	}
	
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String changePendudukStatus(Model model, @Valid @ModelAttribute PendudukModel penduduk, BindingResult result) {
		log.info("change penduduk status {}", penduduk);
		penduduk.setIsWafat("1");
		pendudukDAO.updatePenduduk(penduduk);
		
		List<PendudukModel> listPenduduk = pendudukDAO.selectPendudukFromKeluarga(penduduk.getIdKeluarga());
		boolean isWafatSemua = true;
		for(int i = 0; i < listPenduduk.size(); i++) {
			log.info(i + ". penduduk {}", listPenduduk.get(i));
			if(listPenduduk.get(i).getIsWafat().equals("0")) {
				isWafatSemua = false;
				break;
			}
		}
		if(isWafatSemua) {
			KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
			keluarga.setIsTidakBerlaku("1");
			keluargaDAO.updateKeluarga(keluarga);
		}
		
		model.addAttribute("penduduk", penduduk);
		return "penduduk-success-change";
	}
	
	@RequestMapping("/penduduk/cari")
	public String searchPenduduk(Model model, @RequestParam(value = "kt", required = false) String kt, 
			@RequestParam(value = "kc", required = false) String kc, @RequestParam(value = "kl", required = false) String kl) {
		log.info("search penduduk");
		List<KotaModel> listKota = kotaDAO.selectAllKota();
		model.addAttribute("listKota", listKota);
		KotaModel kota;
		KecamatanModel kecamatan;
		KelurahanModel kelurahan;
		
		if(kt == null) {
			model.addAttribute("listKota", listKota);
			return "penduduk-search-kota";
		} else {
			kota = kotaDAO.selectKota(kt);
			if(kota == null) {
				return "error";
			}
			
			if(kc == null) {
				List<KecamatanModel> listKecamatan = kecamatanDAO.selectKecamatanByIdKota(kt);
				model.addAttribute("kota", kota);
				model.addAttribute("listKecamatan", listKecamatan);
				return "penduduk-search-kecamatan";
			} else {
				kecamatan = kecamatanDAO.selectKecamatan(kc);
				if(kecamatan == null) {
					return "error";
				}
				
				if(kl == null) {
					List<KelurahanModel> listKelurahan = kelurahanDAO.selectKelurahanByIdKecamatan(kc);
					model.addAttribute("kota", kota);
					model.addAttribute("kecamatan", kecamatan);
					model.addAttribute("listKelurahan", listKelurahan);
					return "penduduk-search-kelurahan";
				}
			}
		}
		
		kelurahan = kelurahanDAO.selectKelurahan(kl);
		if(kelurahan == null) {
			return "error";
		}
		List<PendudukModel> listPenduduk = pendudukDAO.selectPendudukByIdKelurahan(kl);
		
		PendudukModel palingMuda = listPenduduk.get(0);
		PendudukModel palingTua = listPenduduk.get(0);
		for(int i = 0; i < listPenduduk.size(); i++) {
			if(palingMuda.getTanggalLahir().compareTo(listPenduduk.get(i).getTanggalLahir()) < 0) {
				palingMuda = listPenduduk.get(i);
			}
			
			if(palingTua.getTanggalLahir().compareTo(listPenduduk.get(i).getTanggalLahir()) > 0) {
				palingTua = listPenduduk.get(i);
			}
		}
		
		model.addAttribute("muda", palingMuda);
		model.addAttribute("tua", palingTua);
		model.addAttribute("kota", kota);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("listPenduduk",listPenduduk);
		return "penduduk-search-success";
	}
	
}
