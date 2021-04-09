package br.com.vinicius.employerlicensetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.employerlicensetracking.entity.CompanyEntity;

@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, Integer>{

}
