package br.com.unknown.cadmed.config.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.unknown.cadmed.controller.form.LaboratorioForm;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.repository.LaboratorioRepository;

public class ValidacaoNomeLabDuplicado implements ConstraintValidator<NomeLabUnico, LaboratorioForm>
{
	@Autowired
	private LaboratorioRepository laboratorioRepository; 

	@Override
	public void initialize(NomeLabUnico constraintAnnotation) {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean isValid(LaboratorioForm form, ConstraintValidatorContext context) {
		Laboratorio laboratorio = laboratorioRepository.findByNome(form.getNome());
		if(laboratorio == null)
			return true;
		return false;
	}

}
