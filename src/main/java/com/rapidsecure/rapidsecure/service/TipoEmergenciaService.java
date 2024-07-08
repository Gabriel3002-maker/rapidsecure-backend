package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.entity.Estado;
import com.rapidsecure.rapidsecure.entity.TipoEmergencia;
import com.rapidsecure.rapidsecure.repository.TipoEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEmergenciaService {
    @Autowired
    private TipoEmergenciaRepository tipoEmergenciaRepository;

    public List<TipoEmergencia> obtenerTiposEmergencia() {
        return tipoEmergenciaRepository.findAll();
    }

    public Optional<TipoEmergencia> obtenerTipoEmergenciaPorId(Long id) {
        return tipoEmergenciaRepository.findById(id);
    }

    public TipoEmergencia guardarTipoEmergencia(TipoEmergencia tipoEmergencia) {
        return tipoEmergenciaRepository.save(tipoEmergencia);
    }

    public void eliminarTipoEmergencia(Long id) {
        tipoEmergenciaRepository.deleteById(id);
    }
}
