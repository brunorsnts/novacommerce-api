package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.dtos.UserDTO;
import com.novacommerce.ecommerce_api.dtos.UserInsertDTO;
import com.novacommerce.ecommerce_api.dtos.UserUpdateDTO;
import com.novacommerce.ecommerce_api.entities.User;
import com.novacommerce.ecommerce_api.exceptions.ResourceNotFoundException;
import com.novacommerce.ecommerce_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO insert(UserInsertDTO userInsertDTO) {
        User entity = new User(userInsertDTO);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        try {
            User user = repository.getReferenceById(id);
            user.setName(userUpdateDTO.name());
            user.setPhone(userUpdateDTO.phone());
            user.setBirthDate(userUpdateDTO.birthDate());
            user = repository.save(user);
            return new UserDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Nenhum usuário encontrado com o id " + id);
        }
    }
}
