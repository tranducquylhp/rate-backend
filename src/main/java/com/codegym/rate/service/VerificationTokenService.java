package com.codegym.rate.service;

import com.codegym.rate.model.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}
