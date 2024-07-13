package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.dto.PersonaDTO;
import com.rapidsecure.rapidsecure.entity.Persona;
import com.rapidsecure.rapidsecure.entity.Rol;
import com.rapidsecure.rapidsecure.repository.PersonaRepository;
import com.rapidsecure.rapidsecure.repository.RolRepository;
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


    public Persona guardarPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setNombre(personaDTO.getNombre());
        persona.setCedula(personaDTO.getCedula());
        persona.setCorreo(personaDTO.getCorreo());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setPassword(personaDTO.getPassword());

        Rol rol = new Rol();
        rol.setId(personaDTO.getRolId());
        persona.setRol(rol);

        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id){
        personaRepository.deleteById(id);
    }

}
