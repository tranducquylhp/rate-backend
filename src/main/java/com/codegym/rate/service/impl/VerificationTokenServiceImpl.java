package com.codegym.rate.service.impl;

import com.codegym.rate.model.VerificationToken;
import com.codegym.rate.repository.VerificationTokenRepository;
import com.codegym.rate.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void save(VerificationToken token) {
        verificationTokenRepository.save(token);
    }
}
