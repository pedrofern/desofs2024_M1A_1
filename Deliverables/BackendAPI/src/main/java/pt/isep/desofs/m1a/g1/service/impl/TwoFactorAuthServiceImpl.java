package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.ICredentialRepository;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.service.TwoFactorAuthService;

@Service
@RequiredArgsConstructor
public class TwoFactorAuthServiceImpl implements TwoFactorAuthService {

	@Autowired
	private GoogleAuthenticator gAuth;
	@Autowired
	private ICredentialRepository userExtensionRepository;

	@Override
	public Pair<String,String> getQRBarcodeURL(String username) {
		String secretKey = userExtensionRepository.getSecretKey(username);
        GoogleAuthenticatorKey key;
        if (secretKey == null || secretKey.isEmpty()) {
            key = gAuth.createCredentials(username);
            userExtensionRepository.saveUserCredentials(username, key.getKey(), 0, null);
        } else {
            key = new GoogleAuthenticatorKey.Builder(secretKey).build();
        }
		String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("ElectricGo", username, key);
		return Pair.of(key.getKey(), otpAuthURL);
	}

	@Override
	public boolean validateCode(String secret, int code) {
		return gAuth.authorize(secret, code);
	}

}
