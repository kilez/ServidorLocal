package com.kilez.Service;

import java.util.Optional;

import com.kilez.entity.Report;

public interface reportService {
	
public Iterable <Report> findAll();
	
	public Report save(Report report);
	
	public Optional <Report> findById(Long id);
	
	public void delateById(Long id);

	

}
