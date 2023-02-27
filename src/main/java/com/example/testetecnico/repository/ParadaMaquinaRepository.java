package com.example.testetecnico.repository;

import com.example.testetecnico.entities.ParadaMaquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ParadaMaquinaRepository extends JpaRepository<ParadaMaquina, Long> {



    @Query( nativeQuery = true, value =
            "select \n" +
            "       pq.id, \n" +
            "       pq.machine_tag, \n" +
            "       pq.start_time, \n" +
            "       pq.end_time, \n" +
            "       pq.reason \n" +
            "   from parada_maquina pq \n" +
            "where pq.machine_tag = :machine_tag \n" +
            "and pq.start_time >= :start_time and pq.end_time <= :end_time \n")
    List<ParadaMaquina> listarIntervaloTempo(@Param("machine_tag") String machineTag,
                                             @Param("start_time") String startTime,
                                             @Param("end_time") String endTime);


    @Query( nativeQuery = true, value =
            "select \n" +
                    "       pq.id, \n" +
                    "       pq.machine_tag, \n" +
                    "       pq.start_time, \n" +
                    "       pq.end_time, \n" +
                    "       pq.reason \n" +
                    "   from parada_maquina pq \n" +
                    "where  pq.end_time is null\n")
    List<ParadaMaquina> listarIntervaloIsNull();



    @Query( nativeQuery = true, value =
            "select \n" +
                    "       pq.id, \n" +
                    "       pq.machine_tag, \n" +
                    "       pq.start_time, \n" +
                    "       pq.end_time, \n" +
                    "       pq.reason \n" +
                    "   from parada_maquina pq \n" +
                    "where pq.start_time >= :start_time and pq.end_time <= :end_time")
    List<ParadaMaquina> listarParada(@Param("start_time") String startTime,
                                     @Param("end_time") String endTime);




}
