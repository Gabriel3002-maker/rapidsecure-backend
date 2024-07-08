package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.entity.Persona;
import com.rapidsecure.rapidsecure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> obtenerTodaslasPersonas(){
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorCedula(Long cedula){
        return personaRepository.findById(cedula);
    }

    public  Persona guardarPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id){
        personaRepository.deleteById(id);
    }

}
