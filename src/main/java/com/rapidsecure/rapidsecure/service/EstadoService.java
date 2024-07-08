package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.entity.Estado;
import com.rapidsecure.rapidsecure.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;



    public List<Estado> obtenerTodosLosEstados() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> obtenerEstadosPorId(Long id) {
        return estadoRepository.findById(id);
    }

    public Estado guardarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void eliminarEstado(Long id) {
        estadoRepository.deleteById(id);
    }

}
