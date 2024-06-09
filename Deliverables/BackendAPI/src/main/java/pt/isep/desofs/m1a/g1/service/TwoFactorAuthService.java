package pt.isep.desofs.m1a.g1.service;

import org.springframework.data.util.Pair;

public interface TwoFactorAuthService {

	Pair<String,String> getQRBarcodeURL(String username);

	boolean validateCode(String secret, int code);

}
