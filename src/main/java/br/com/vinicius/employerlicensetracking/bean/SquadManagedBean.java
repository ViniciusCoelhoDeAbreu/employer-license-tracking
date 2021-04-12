package br.com.vinicius.employerlicensetracking.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.SquadEntity;
import br.com.vinicius.employerlicensetracking.repository.ISquadRepository;
import lombok.Data;
import lombok.NonNull;

@Component
@Scope("view")
@Data
public class SquadManagedBean {

	private final @NonNull ISquadRepository squadRepository;
	
	private List<SquadEntity> squads;
	private SquadEntity squad;
	
	@PostConstruct
	public void initialize() {
		squads = new ArrayList<SquadEntity>();
		squad = new SquadEntity();
	}
	
	public void save() {
		if(squad.getNome() == null || squad.getNome().isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!!", "Nome da squad precisa ser específicado."));
			return;
		}
		squad = squadRepository.save(squad);
		
		initialize();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!!", "Squad salva com sucesso!"));
	}
	
	public List<SquadEntity> completeSquad(String query) {
		String queryLowerCase = query.toLowerCase();
		queryLowerCase = queryLowerCase.concat("%");
		return squadRepository.findByNome(queryLowerCase);
	}
	
	public void onItemSelect(SelectEvent<SquadEntity> event) {
		squad = event.getObject();
    }
	
}
