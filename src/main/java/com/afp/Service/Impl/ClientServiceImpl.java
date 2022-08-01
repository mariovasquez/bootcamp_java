package com.afp.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afp.Exception.ModelNotFoundException;
import com.afp.Model.AFP;
import com.afp.Model.Client;

import com.afp.Repository.IAfpRepository;
import com.afp.Repository.IClientRepository;
import com.afp.Service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository;
    @Autowired
    IAfpRepository afpRepository;

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client create(Client request) {
        if (!existsClient(request.getDni())) {
            try {
                if (validateUser(request)) {
                    Client user = constructorUser(request);
                    this.afpRepository.save(user.getAfp());
                    return this.clientRepository.save(user);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        throw new ModelNotFoundException("DNI ya se encuentra registrado.");
    }
    
    @Override
    public Client update(Client request, String id) {
    	request.setDni(id);
        return this.clientRepository.save(request);
    }

    @Override
    public Client delete(String id) {
        this.clientRepository.deleteById(id);
        throw new ModelNotFoundException("El cliente ha sido eliminado.");
    }
    
    private Client constructorUser(Client request) {
        AFP afp = AFP.builder()
                            .numberAccount(request.getName().charAt(0) + request.getDni())
                            .amount(request.getAfp().getAmount())
                            .typeAccount(request.getAfp().getTypeAccount())
                            .withdrawal(0)
                            .withdrawalDate(null)
                            .build();

        Client client = Client.builder()
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .afp(afp)
                .build();
        return client;
    }

    // Valida los atributos del usuario
    private boolean validateUser(Client request) {
        if (request.getDni().length() == 8) {
            if (request.getPhone().length() == 9) {
                return true;
            }
            throw new ModelNotFoundException("El número de teléfono debe tener 9 dígitos.");
        }
        throw new ModelNotFoundException("El DNI debe tener 8 dígitos.");
    }


    // Busca por id
    private boolean existsClient(String id) {
        return this.clientRepository.existsById(id);
    }

    
    
}
