package br.com.vinicius.employerlicensetracking.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.EmployeeEntity;
import br.com.vinicius.employerlicensetracking.repository.IEmployeeRepository;

@Component
public class EmployerConverter implements Converter {

	@Autowired
	private IEmployeeRepository employerRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null)
			return value;
		
		return employerRepository.findById(Integer.valueOf(value)).get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return "";
		
		return ((EmployeeEntity)value).getId().toString();
	}

}
