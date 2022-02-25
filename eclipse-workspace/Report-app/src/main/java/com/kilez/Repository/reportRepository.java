package com.kilez.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kilez.entity.Report;

public interface reportRepository extends CrudRepository<Report, Long> {

	

}
