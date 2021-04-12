package br.com.vinicius.employerlicensetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vinicius.employerlicensetracking.entity.CompanyEntity;

@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, Integer>{

	
	@Query("select e from CompanyEntity e where lower(e.nome) like :name")
	List<CompanyEntity> findByNome(@Param("name") String name);
}
