package com.example.testetecnico.service;

import com.example.testetecnico.Utils;
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



	//
	public ParadaMaquina salvarParada(ParadaMaquina paradaMaquina) {
		List<ParadaMaquina> listarParada = paradaMaquinaRepository.listarParada(paradaMaquina.getStartTime(),paradaMaquina.getEndTime());

		//validar se ja existe data de inicio é fim iguais. Caso haja nao deixa salvar
		listarParada.forEach(p -> {
			if(p.getStartTime().equals(paradaMaquina.getStartTime()) || p.getEndTime().equals(paradaMaquina.getEndTime())){
				throw new IllegalArgumentException("Ja existe paradas com as mesmas datas.");
			}
		});

		return this.paradaMaquinaRepository.save(paradaMaquina);
	}

	public Optional<ParadaMaquina> buscar(Long id) {
		return paradaMaquinaRepository.findById(id);
	}
	
	public List<ParadaMaquina> listarParadaIntervaloTempo(String machineTag, String intervalStart, String intervalEnd){
		// valida se data inserida esta no padrao correto
		Utils.dataIsValid(intervalStart);
		Utils.dataIsValid(intervalEnd);


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
		paradaMaquinaRepository.deleteAllInBatch();
	}

	
	public ParadaMaquina findById(Long id) {
		Optional<ParadaMaquina> paradaMaquina = paradaMaquinaRepository.findById(id);
		return paradaMaquina.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + ParadaMaquina.class.getName(), id.toString()));
	}


	// Api get retorna todas as paradas cuja o fim é null

	public List<ParadaMaquina> listarParadasIsNull(){
		List<ParadaMaquina> listarParadaIntervaloTempo = paradaMaquinaRepository.listarIntervaloIsNull();
		return listarParadaIntervaloTempo;
	}



}
