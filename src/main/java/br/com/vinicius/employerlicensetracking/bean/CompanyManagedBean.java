package br.com.vinicius.employerlicensetracking.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.CompanyEntity;
import br.com.vinicius.employerlicensetracking.repository.ICompanyRepository;
import lombok.Data;
import lombok.NonNull;

@Component
@Scope("view")
@Data
public class CompanyManagedBean {

	private final @NonNull ICompanyRepository companyRepository;
	
	private List<CompanyEntity> companies;
	private CompanyEntity company;
	
	@PostConstruct
	public void initialize() {
		companies = new ArrayList<CompanyEntity>();
		company = new CompanyEntity();
	}
	
	public void save() {
		if(company.getNome() == null || company.getNome().isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!!", "Nome da empresa precisa ser específicado."));
			return;
		}
		company = companyRepository.save(company);
		
		initialize();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!!", "Empresa salva com sucesso!"));
	}
	
	public List<CompanyEntity> completeCompany(String query) {
		String queryLowerCase = query.toLowerCase();
		queryLowerCase = queryLowerCase.concat("%");
		return companyRepository.findByNome(queryLowerCase);
	}
	
	public void onItemSelect(SelectEvent<CompanyEntity> event) {
		company = event.getObject();
    }
	
}
