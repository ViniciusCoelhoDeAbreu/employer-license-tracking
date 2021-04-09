package br.com.vinicius.employerlicensetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinicius.employerlicensetracking.entity.EmployeeSquadEntity;

@Repository
public interface IEmployeeSquadRepository extends JpaRepository<EmployeeSquadEntity, Integer>{

	
	@Query("select es from EmployeeSquadEntity es where es.employee.id = :param")
	List<EmployeeSquadEntity> findByFuncionarioId(@Param("param") Integer funcionarioId);
	
	@Transactional
	@Modifying
	@Query("delete from EmployeeSquadEntity where employee.id = :param")
	void deleteAllByFuncionarioId(@Param("param") Integer funcionarioId);
}
