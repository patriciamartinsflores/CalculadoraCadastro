package br.com.unknown.cadmed.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;



import javax.validation.Valid;

import br.com.unknown.cadmed.service.MedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import br.com.unknown.cadmed.controller.dto.MedicamentoDto;
import br.com.unknown.cadmed.controller.form.AtualizacaoMedicamentoForm;
import br.com.unknown.cadmed.controller.form.MedicamentoForm;


@Api(value="API Rest cadastro de medicamentos")
@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController {
	
	@Autowired
	private MedicamentoService medicamentoService;

	@ApiOperation(value="retorna lista de medicamentos")
	@GetMapping
	public List<MedicamentoDto> lista() 
	{
		return medicamentoService.lista();
	}

	@ApiOperation(value="Insere um medicamento no banco de dados")
	@PostMapping
	public ResponseEntity<MedicamentoDto> cadastrar(@RequestBody @Valid MedicamentoForm form, UriComponentsBuilder uriBuilder) 
	{		
		return medicamentoService.cadastrar(form, uriBuilder);	
	}

	@ApiOperation(value="Edita um medicamento do banco de dados")
	@PutMapping("/{id}")
	public ResponseEntity<MedicamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicamentoForm form)
	{
		return medicamentoService.atualizar(id,form);
	}

	@ApiOperation(value="Exclui um medicamento do banco de dados")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		return medicamentoService.remover(id);
	}

}
