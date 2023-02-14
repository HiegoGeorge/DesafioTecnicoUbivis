package com.example.testetecnico.rest;

import com.example.testetecnico.entities.ParadaMaquina;
import com.example.testetecnico.form.ParadaMaquinaForm;
import com.example.testetecnico.service.ParadaMaquinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class ParadaMaquinaResource {
	private static final Logger log = LoggerFactory.getLogger(ParadaMaquinaResource.class);


	@Autowired
	private final ParadaMaquinaService paradaMaquinaService;

	public ParadaMaquinaResource(ParadaMaquinaService paradaMaquinaService) {
		this.paradaMaquinaService = paradaMaquinaService;
	}


	//Cria uma nova parada
	@PostMapping("/machine-halt")
	public ResponseEntity<ParadaMaquina> salvar(@Valid @RequestBody ParadaMaquina paradaMaquina) throws URISyntaxException	{
		paradaMaquina = paradaMaquinaService.salvarParada(paradaMaquina);
		return ResponseEntity.created(new URI("/paradaMaquina/" + paradaMaquina.getId())).body(paradaMaquina);
	}

	//Retorna uma parada por id
	@GetMapping("/machine-halt/{id}")
	public ResponseEntity<Optional<ParadaMaquina>> getParadaMaquina(@PathVariable Long id){
		return ResponseEntity.ok(paradaMaquinaService.buscar(id));
	}


	//Lista paradas de uma máquina dentro de um intervalo de tempo
	//url de exemplo com query parameters http://localhost:8080/machine-halt/list?machineTag=teste1&startTime=2023-02-13&endTime=2023-02-30
	@GetMapping("/machine-halt/list")
	public List<ParadaMaquina> getAll(@RequestParam String machineTag,
													  @RequestParam String startTime,
													  @RequestParam String endTime){
		List<ParadaMaquina> result = paradaMaquinaService.listarParadaIntervaloTempo(machineTag,startTime,endTime);
		return result;

		}


	// Formulario de entrada contendo capos para Finaliza uma parada e Altera o motivo para a parada
	@PutMapping("/machine-halt")
	public ResponseEntity<ParadaMaquina> updateParada(@RequestBody ParadaMaquinaForm paradaMaquinaForm)
	{

		List<String> msgErros = ParadaMaquinaForm.getValidarDadosInseridos(paradaMaquinaForm);

		if (!msgErros.isEmpty()){
			throw new IllegalArgumentException(msgErros.get(0));
		}

		ParadaMaquina result = paradaMaquinaService.update(paradaMaquinaForm);

		return ResponseEntity.ok().body(result);
	}


	//Remove todas as entradas de paradas
	@DeleteMapping("/machine-halt/all")
	public ResponseEntity<Void> deleteParada(){
		paradaMaquinaService.delete();
		return ResponseEntity.noContent().build();
	}
		

	
}
