package br.com.vinicius.employerlicensetracking.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.ServiceEntity;
import br.com.vinicius.employerlicensetracking.repository.IServiceRepository;

@Component
public class ServiceConverter implements Converter {

	
	@Autowired
	private IServiceRepository serviceRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null)
			return null;
		
		return serviceRepository.findById(Integer.valueOf(value)).get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return "";
		
		return ((ServiceEntity)value).getId().toString();
	}
}
