package br.com.vinicius.employerlicensetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vinicius.employerlicensetracking.entity.SquadEntity;

@Repository
public interface ISquadRepository extends JpaRepository<SquadEntity, Integer>{

	
	@Query("select e from SquadEntity e where lower(e.nome) like :name")
	List<SquadEntity> findByNome(@Param("name") String name);
}
