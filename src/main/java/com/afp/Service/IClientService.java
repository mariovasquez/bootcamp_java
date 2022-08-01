package com.afp.Service;

import java.util.List;

import com.afp.Model.Client;

// Creamos las interfaces de cliente para definir las cabeceras de los metodos a implementar
public interface IClientService {
    
    List<Client> findAll();
    Client create(Client request);
    Client update(Client request, String id);
    Client delete(String id);

}
