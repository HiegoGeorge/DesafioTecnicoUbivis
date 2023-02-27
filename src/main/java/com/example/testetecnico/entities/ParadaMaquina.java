package com.example.testetecnico.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


@Entity
@Table(name= "parada_maquina")
public class ParadaMaquina implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // identificador da parada (inteiro, não nulo)

	@NotNull
	@Size(max = 24)
	@Column(name = "machine_tag", nullable = false)
	private String machineTag; // identificador da máquina (texto de até 24 caracteres, não nulo)

	@NotNull
	@Size(max = 10)
	@Column(name = "start_time", nullable = false)
	private String startTime; // tempo de início da parada (não nulo)

	@Size(max = 10)
	@Column(name = "end_time", nullable = true)
	private String endTime; // tempo de finalização da parada

	@Size(max = 128)
	@Column(name = "reason", nullable = true)
	private String reason; // Descrição do motivo da parada (texto de até 128 caracteres)


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineTag() {
		return machineTag;
	}

	public void setMachineTag(String machineTag) {
		this.machineTag = machineTag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
