package br.com.vinicius.employerlicensetracking.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.ServiceEntity;
import br.com.vinicius.employerlicensetracking.repository.IServiceRepository;
import lombok.Data;
import lombok.NonNull;

@Component
@Scope("view")
@Data
public class ServiceManagedBean {

	private final @NonNull IServiceRepository serviceRepository;
	
	private List<ServiceEntity> services;
	private ServiceEntity service;
	
	@PostConstruct
	public void initialize() {
		services = new ArrayList<ServiceEntity>();
		service = new ServiceEntity();
	}
	
	public void save() {
		if(service.getNome() == null || service.getNome().isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!!", "Nome do serviço precisa ser específicado."));
			return;
		}
		service = serviceRepository.save(service);
		
		initialize();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!!", "Serviço salvo com sucesso!"));
	}
	
	public List<ServiceEntity> completeService(String query) {
		String queryLowerCase = query.toLowerCase();
		queryLowerCase = queryLowerCase.concat("%");
		return serviceRepository.findByNome(queryLowerCase);
	}
	
	public void onItemSelect(SelectEvent<ServiceEntity> event) {
		service = event.getObject();
    }
	
}
