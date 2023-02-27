package com.example.testetecnico.rest;

import com.example.testetecnico.entities.ParadaMaquina;
import com.example.testetecnico.repository.ParadaMaquinaRepository;
import com.example.testetecnico.service.ParadaMaquinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ParadaMaquinaTest extends AbstractTest{

	@Autowired
	private ParadaMaquinaRepository paradaMaquinaRepository;

	@Autowired
	private ParadaMaquinaResource paradaMaquinaResource;

	@Autowired
	private ParadaMaquinaService paradaMaquinaService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntityManager em;

	private ParadaMaquina paradaMaquina;

	@BeforeEach
	public void setup() {
		paradaMaquina = new ParadaMaquina();
		paradaMaquina.setId(1L);
		paradaMaquina.setMachineTag("tag_01");
		paradaMaquina.setStartTime("2023-01-02");
		paradaMaquina.setEndTime("2023-01-15");
		paradaMaquinaRepository.save(paradaMaquina);
	}

	@Test
	@Transactional
	public void getParadaMaquina() throws Exception{
		this.mockMvc.perform(get("/machine-halt/{id}", paradaMaquina.getId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").value(paradaMaquina.getId()))
		.andExpect(jsonPath("$.machineTag").value(paradaMaquina.getMachineTag()))
		.andExpect(jsonPath("$.startTime").value(paradaMaquina.getStartTime()))
		.andExpect(jsonPath("$.endTime").value(paradaMaquina.getEndTime()))
		.andExpect(jsonPath("$.reason").value(paradaMaquina.getReason()));

	}

	@Test
	@Transactional
	public void getAllParadaMaquina() throws Exception{
		String urlTeste = "?machineTag=tag_01&startTime=2023-01-02&endTime=2023-01-15";
		this.mockMvc.perform(get("/machine-halt/list"+urlTeste))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[*].id").isNotEmpty())
				.andExpect(jsonPath("$.[*].machineTag").value(hasItem(paradaMaquina.getMachineTag())))
				.andExpect(jsonPath("$.[*].startTime").value(hasItem(paradaMaquina.getStartTime())))
				.andExpect(jsonPath("$.[*].endTime").value(hasItem(paradaMaquina.getEndTime())))
				.andExpect(jsonPath("$.[*].reason").value(hasItem(paradaMaquina.getReason())));

	}

	@Test
	@Transactional
	public void deletar() throws Exception{
		//inicializa a base
		paradaMaquinaRepository.saveAndFlush(paradaMaquina);

		int dataBaseSizeBeforeDelete = paradaMaquinaRepository.findAll().size();
		//realiza o delete
		this.mockMvc.perform(delete("/machine-halt/all"))
		.andExpect(status().isNoContent());

		//valida se a base esta vazia
		List<ParadaMaquina> list = paradaMaquinaRepository.findAll();
		assertThat(list).hasSize(dataBaseSizeBeforeDelete -7 );

	}


	@Test
	@Transactional
	public void criarComSucesso() throws Exception{

		ParadaMaquina paradaMaquinaAtual = paradaMaquina;
		paradaMaquinaAtual.setStartTime("Algas marinhas");
		paradaMaquinaAtual.setStartTime("2023-02-20");

		String teste = super.mapToJson(paradaMaquinaAtual);

		this.mockMvc.perform(post("/machine-halt")
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isCreated());

	}


}
