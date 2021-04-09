package br.com.vinicius.employerlicensetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinicius.employerlicensetracking.entity.EmployeeServiceEntity;

@Repository
public interface IEmployeeServiceRepository extends JpaRepository<EmployeeServiceEntity, Integer>{

	
	@Query("select es from EmployeeServiceEntity es where es.employee.id = :param")
	List<EmployeeServiceEntity> findByFuncionarioId(@Param("param") Integer funcionarioId);
	
	@Transactional
	@Modifying
	@Query("delete from EmployeeServiceEntity where employee.id = :param")
	void deleteAllByFuncionarioId(@Param("param") Integer funcionarioId);
}
