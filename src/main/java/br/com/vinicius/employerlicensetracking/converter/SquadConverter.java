package br.com.vinicius.employerlicensetracking.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.SquadEntity;
import br.com.vinicius.employerlicensetracking.repository.ISquadRepository;

@Component
public class SquadConverter implements Converter {

	
	@Autowired
	private ISquadRepository squadRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null)
			return null;
		
		return squadRepository.findById(Integer.valueOf(value)).get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return "";
		
		return ((SquadEntity)value).getId().toString();
	}
}
