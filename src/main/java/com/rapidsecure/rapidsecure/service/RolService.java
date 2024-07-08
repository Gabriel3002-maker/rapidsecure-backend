package com.rapidsecure.rapidsecure.service;

import com.rapidsecure.rapidsecure.entity.Rol;
import com.rapidsecure.rapidsecure.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import  java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> obtenerRoles(){
        return  rolRepository.findAll();
    }

    public Optional<Rol> obtenerRolPorId(Long id){
        return  rolRepository.findById(id);
    }

    public Rol guardarRol(Rol rol){
        return rolRepository.save(rol);
    }

    public void eliminarRol(Long id){
        rolRepository.deleteById(id);
    }
}
