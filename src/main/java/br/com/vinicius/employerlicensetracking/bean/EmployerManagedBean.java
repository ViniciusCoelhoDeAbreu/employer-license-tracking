package br.com.vinicius.employerlicensetracking.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.CompanyEntity;
import br.com.vinicius.employerlicensetracking.entity.EmployeeCompanyEntity;
import br.com.vinicius.employerlicensetracking.entity.EmployeeEntity;
import br.com.vinicius.employerlicensetracking.entity.EmployeeServiceEntity;
import br.com.vinicius.employerlicensetracking.entity.EmployeeSquadEntity;
import br.com.vinicius.employerlicensetracking.entity.ServiceEntity;
import br.com.vinicius.employerlicensetracking.entity.SquadEntity;
import br.com.vinicius.employerlicensetracking.repository.ICompanyRepository;
import br.com.vinicius.employerlicensetracking.repository.IEmployeeCompanyRepository;
import br.com.vinicius.employerlicensetracking.repository.IEmployeeRepository;
import br.com.vinicius.employerlicensetracking.repository.IEmployeeServiceRepository;
import br.com.vinicius.employerlicensetracking.repository.IEmployeeSquadRepository;
import br.com.vinicius.employerlicensetracking.repository.IServiceRepository;
import br.com.vinicius.employerlicensetracking.repository.ISquadRepository;
import lombok.Data;
import lombok.NonNull;

@Component
@Scope("session")
@Data
public class EmployerManagedBean {
	
	private final @NonNull IServiceRepository serviceRepository;
	private final @NonNull ICompanyRepository companyRepository;
	private final @NonNull ISquadRepository squadRepository;
	private final @NonNull IEmployeeRepository employeeRepository;
	private final @NonNull IEmployeeServiceRepository employeeServiceRepository;
	private final @NonNull IEmployeeCompanyRepository employeeCompanyRepository;
	private final @NonNull IEmployeeSquadRepository employeeSquadRepository;
	
	private List<ServiceEntity> services;
	private List<CompanyEntity> companies;
	private List<SquadEntity> squads;
	
	private List<ServiceEntity> servicesSelected;
	private List<CompanyEntity> companiesSelected;
	private List<SquadEntity> squadsSelected;
	
	private EmployeeEntity employeeSelected;
	private EmployeeEntity employee;
	
	@PostConstruct
	public void initialize() {
		services = serviceRepository.findAll();
		companies = companyRepository.findAll();
		squads = squadRepository.findAll();
		employee = new EmployeeEntity();
		servicesSelected = new ArrayList<ServiceEntity>();
		companiesSelected = new ArrayList<CompanyEntity>();
		squadsSelected = new ArrayList<SquadEntity>();
		employeeSelected = new EmployeeEntity();
	}
	
	public void save() {
		if(employee.getNome() == null || employee.getNome().isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!!", "Nome do funcionário precisa ser específicado."));
			return;
		}
		
		if(employee.getId() != null) {
			deleteAllFromCurrentEmployee();
		}
		
		employee = employeeRepository.save(employee);
		servicesSelected.stream().map(service -> new EmployeeServiceEntity(employee, service)).forEach(employeeServiceRepository::save);
		companiesSelected.stream().map(company -> new EmployeeCompanyEntity(employee, company)).forEach(employeeCompanyRepository::save);
		squadsSelected.stream().map(squad -> new EmployeeSquadEntity(employee, squad)).forEach(employeeSquadRepository::save);
		
		initialize();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!!", "Funcionário salvo com sucesso!"));
	}
	
	public void delete() {
		deleteAllFromCurrentEmployee();
		employeeRepository.delete(employee);
		initialize();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "SUCESSO!!", "Funcionário deletado com sucesso!"));
	}
	
	
	public void deleteAllFromCurrentEmployee() {
		employeeServiceRepository.deleteAllByFuncionarioId(employee.getId());
		employeeCompanyRepository.deleteAllByFuncionarioId(employee.getId());
		employeeSquadRepository.deleteAllByFuncionarioId(employee.getId());
	}
	
	public List<EmployeeEntity> completeEmployee(String query) {
		String queryLowerCase = query.toLowerCase();
		queryLowerCase = queryLowerCase.concat("%");
		return employeeRepository.findByNome(queryLowerCase);
	}
	
	public void onItemSelect(SelectEvent<EmployeeEntity> event) {
		employee = event.getObject();
		servicesSelected = employeeServiceRepository.findByFuncionarioId(employee.getId()).stream().map(es -> es.getService()).collect(Collectors.toList());
		companiesSelected = employeeCompanyRepository.findByFuncionarioId(employee.getId()).stream().map(ec -> ec.getCompany()).collect(Collectors.toList());
		squadsSelected = employeeSquadRepository.findByFuncionarioId(employee.getId()).stream().map(es -> es.getSquad()).collect(Collectors.toList());
    }
	
}
