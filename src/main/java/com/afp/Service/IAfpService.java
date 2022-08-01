package com.afp.Service;

import com.afp.Model.AFP;

public interface IAfpService {

    AFP get(String id);
    AFP create(String id, float retiro);
    
}
