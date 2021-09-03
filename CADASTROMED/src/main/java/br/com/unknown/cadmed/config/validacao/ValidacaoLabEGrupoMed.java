package br.com.unknown.cadmed.config.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.unknown.cadmed.controller.form.MedicamentoForm;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.LaboratorioRepository;

public class ValidacaoLabEGrupoMed implements ConstraintValidator<LaboratorioEGrupoMedExistem, MedicamentoForm>{
		
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository; 
	
	@Autowired
	private LaboratorioRepository laboratorioRepository; 
	
	
	@Override
	public void initialize(LaboratorioEGrupoMedExistem constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(MedicamentoForm medicamento, ConstraintValidatorContext context) {
		GrupoMedicamento grupoMed = grupoMedicamentoRepository.findByNome(medicamento.getNomeGrupoMedicamento());
		Laboratorio laboratorio   = laboratorioRepository.findByNome(medicamento.getNomeLaboratorio());
		if (grupoMed == null || laboratorio == null )
			return false;
		return true;
	}

}
