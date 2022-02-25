package com.kilez.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kilez.Repository.reportRepository;
import com.kilez.entity.Report;

@Service
public class reportServiceImp implements reportService {

	
	@Autowired
	private reportRepository reportRepository;
	
	
	@Override
	public Iterable<Report> findAll() {
		return reportRepository.findAll();
	}

	@Override
	public Report save(Report report) {
		return reportRepository.save(report);
	}

	@Override
	public Optional<Report> findById(Long id) {
		return reportRepository.findById(id);
	}

	@Override
	public void delateById(Long id) {
		reportRepository.deleteById(id);
		
	}

}
