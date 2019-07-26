package com.artexperience.test.ArtTestSecure.service;

import com.artexperience.test.ArtTestSecure.data.UserRepositoryJpa;
import com.artexperience.test.ArtTestSecure.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private final UserRepositoryJpa repository;

    @Autowired
    public UserServices(final UserRepositoryJpa repository){
        this.repository = repository;
    }

    public List<Usuarios> findAllUsers(){
        return repository.findAll();
    }

    public Optional<Usuarios> findUsersById(Long id){
        return repository.findById(id);
    }

    public Usuarios create(final Usuarios bodyUser){
        Usuarios user = new Usuarios();
        user.setUsername(bodyUser.getUsername());
        user.setPassword(bodyUser.getPassword());
        user.setEmail(bodyUser.getEmail());
        user.setCel(bodyUser.getCel());
        user.setFullname(bodyUser.getFullname());
        user.setCreateOn(Instant.now());
        user.setStatus(true);
        user.setDeleteOn(null);// este
        user.setId(bodyUser.getId());//y este , sacar estos campos del objeto ya que se generan automaticamente
        return repository.save(user);
    }

    public void deleteUserById(Long usuarios){
        Optional<Usuarios> user = repository.findById(usuarios);
        user.get().setStatus(false);
        repository.save(user.get());
    }

}