package com.example.testetecnico.service;

import com.example.testetecnico.entities.ParadaMaquina;
import com.example.testetecnico.form.ParadaMaquinaForm;
import com.example.testetecnico.repository.ParadaMaquinaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParadaMaquinaService {
	
	@Autowired
	private ParadaMaquinaRepository paradaMaquinaRepository;


	public ParadaMaquina salvarParada(ParadaMaquina paradaMaquina) {
		return this.paradaMaquinaRepository.save(paradaMaquina);
	}

	public Optional<ParadaMaquina> buscar(Long id) {
		return paradaMaquinaRepository.findById(id);
	}
	
	public List<ParadaMaquina> listarParadaIntervaloTempo(String machineTag, String intervalStart, String intervalEnd){

		if(intervalStart == null && intervalEnd == null){
			throw new IllegalArgumentException("Data de inicio é data de fim devem ser informadas");
		}

		List<ParadaMaquina> listarParadaIntervaloTempo = paradaMaquinaRepository.listarIntervaloTempo(machineTag,intervalStart,intervalEnd);
		return listarParadaIntervaloTempo;
	}
	
	
	public ParadaMaquina update(ParadaMaquinaForm paradaMaquinaForm) {
		ParadaMaquina paradaMaquinaAtual = findById(paradaMaquinaForm.getId());

		paradaMaquinaAtual.setId(paradaMaquinaForm.getId());
		paradaMaquinaAtual.setEndTime(paradaMaquinaForm.getEndTime());
		paradaMaquinaAtual.setReason(paradaMaquinaForm.getReason());

		return paradaMaquinaRepository.save(paradaMaquinaAtual);
	}
	
	public void delete() {
		ParadaMaquina paradaMaquina = new ParadaMaquina();
		paradaMaquinaRepository.delete(paradaMaquina);
	}

	
	public ParadaMaquina findById(Long id) {
		Optional<ParadaMaquina> pessoa = paradaMaquinaRepository.findById(id);
		return pessoa.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + ParadaMaquina.class.getName(), id.toString()));
	}


}
