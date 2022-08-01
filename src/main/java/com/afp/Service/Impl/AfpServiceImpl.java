package com.afp.Service.Impl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afp.Exception.ModelNotFoundException;
import com.afp.Model.AFP;
import com.afp.Repository.IAfpRepository;
import com.afp.Service.IAfpService;

@Service
public class AfpServiceImpl implements IAfpService {

    @Autowired
    IAfpRepository afpRepository;

    @Override
    public AFP get(String id) {
        AFP afp = this.afpRepository.findAll().stream()
        .filter(request -> request.getNumberAccount().equals(id))
        .collect(Collectors.toList()).get(0);
        if (afp.equals(null)) {
            throw new ModelNotFoundException("No existe la cuenta AFP.");
        } else {
            return afp;
        }
    }

    @Override
    public AFP create(String id, float withdrawal) {
        if (existAfo(id)) {
            try {
                if (validAfp(id, withdrawal)) {
                    AFP afp = this.afpRepository.getById(id);
                    afp.setWithdrawal(withdrawal);
                    String date = "2023-12-03T10:15:30";
                    afp.setWithdrawalDate(null);
                    return this.afpRepository.save(afp);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        throw new ModelNotFoundException("El id de la cuenta AFP no existe.");
    }
    private boolean existAfo(String id) {
        return this.afpRepository.existsById(id);
    }

    private boolean validAfp(String id, float withdrawal) {
        AFP afp = this.afpRepository.getById(id);
        if (afp.getAmount() /2 < withdrawal) {
            return true;
        }
        throw new ModelNotFoundException("El retiro tiene que ser mayor al 50% disponible.");
    }
    
}
