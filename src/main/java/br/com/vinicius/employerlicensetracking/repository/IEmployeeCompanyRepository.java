package br.com.vinicius.employerlicensetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinicius.employerlicensetracking.entity.EmployeeCompanyEntity;

@Repository
public interface IEmployeeCompanyRepository extends JpaRepository<EmployeeCompanyEntity, Integer>{

	
	@Query("select ec from EmployeeCompanyEntity ec where ec.employee.id = :param")
	List<EmployeeCompanyEntity> findByFuncionarioId(@Param("param") Integer funcionarioId);
	
	@Transactional
	@Modifying
	@Query("delete from EmployeeCompanyEntity where employee.id = :param")
	void deleteAllByFuncionarioId(@Param("param") Integer funcionarioId);
}
