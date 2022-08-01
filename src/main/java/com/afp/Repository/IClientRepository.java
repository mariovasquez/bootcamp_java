package com.afp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afp.Model.Client;

public interface IClientRepository extends JpaRepository<Client, String> {

}
