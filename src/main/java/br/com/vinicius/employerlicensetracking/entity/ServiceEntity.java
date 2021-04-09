package br.com.vinicius.employerlicensetracking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(schema = "geral", name = "servico")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class ServiceEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
	        name = "serviceSequenceGenerator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SERVICO_SEQUENCE"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "serviceSequenceGenerator")
	private Integer id;
	
	private @NonNull String nome;
	
	@Column(name = "qtde_licencas_previstas", columnDefinition = "INTEGER default 0")
	private Integer qtdeLicencasPrevistas;

	
}
