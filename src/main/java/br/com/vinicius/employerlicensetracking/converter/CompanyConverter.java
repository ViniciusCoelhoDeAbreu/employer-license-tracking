package br.com.vinicius.employerlicensetracking.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vinicius.employerlicensetracking.entity.CompanyEntity;
import br.com.vinicius.employerlicensetracking.repository.ICompanyRepository;

@Component
public class CompanyConverter implements Converter {

	
	@Autowired
	private ICompanyRepository companyRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null)
			return null;
		
		return companyRepository.findById(Integer.valueOf(value)).get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return "";
		
		return ((CompanyEntity)value).getId().toString();
	}
}
