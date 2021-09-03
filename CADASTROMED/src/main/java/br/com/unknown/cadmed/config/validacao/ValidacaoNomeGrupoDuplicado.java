package br.com.unknown.cadmed.config.validacao;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.unknown.cadmed.controller.form.GrupoMedicamentoForm;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;

public class ValidacaoNomeGrupoDuplicado implements ConstraintValidator<NomeGrupoUnico, GrupoMedicamentoForm>{
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository; 

	@Override
	public void initialize(NomeGrupoUnico constraintAnnotation) {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean isValid(GrupoMedicamentoForm grupoMedicamento, ConstraintValidatorContext context) {
		GrupoMedicamento grupo = grupoMedicamentoRepository.findByNome(grupoMedicamento.getNome());
		if(grupo == null)
			return true;
		return false;
	}
}
