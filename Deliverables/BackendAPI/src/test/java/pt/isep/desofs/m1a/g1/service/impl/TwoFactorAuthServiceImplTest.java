package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.ICredentialRepository;

public class TwoFactorAuthServiceImplTest {

	@Mock
	private GoogleAuthenticator gAuth;

	@Mock
	private ICredentialRepository userExtensionRepository;

	@InjectMocks
	private TwoFactorAuthServiceImpl twoFactorAuthService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetQRBarcodeURL() {
		String username = "username";
		String secretKey = "secretKey";
		String otpAuthURL = "otpAuthURL";

		when(userExtensionRepository.getSecretKey(username)).thenReturn(secretKey);

		try (MockedStatic<GoogleAuthenticatorQRGenerator> mocked = Mockito
				.mockStatic(GoogleAuthenticatorQRGenerator.class)) {
			mocked.when(() -> GoogleAuthenticatorQRGenerator.getOtpAuthURL(Mockito.eq("ElectricGo"),
					Mockito.eq(username), Mockito.any())).thenReturn(otpAuthURL);

			Pair<String, String> result = twoFactorAuthService.getQRBarcodeURL(username);

			assertEquals(secretKey, result.getFirst());
			assertEquals(otpAuthURL, result.getSecond());
		}
	}
	
	@Test
	public void testGetQRBarcodeURLSecretNull() {
		String username = "username";
		String secretKey = "secretKey";
		GoogleAuthenticatorKey key = new GoogleAuthenticatorKey.Builder(secretKey).build();
		String otpAuthURL = "otpAuthURL";

		when(userExtensionRepository.getSecretKey(username)).thenReturn("");
		when(gAuth.createCredentials(username)).thenReturn(key);

		try (MockedStatic<GoogleAuthenticatorQRGenerator> mocked = Mockito
				.mockStatic(GoogleAuthenticatorQRGenerator.class)) {
			mocked.when(() -> GoogleAuthenticatorQRGenerator.getOtpAuthURL(Mockito.eq("ElectricGo"),
					Mockito.eq(username), Mockito.any())).thenReturn(otpAuthURL);

			Pair<String, String> result = twoFactorAuthService.getQRBarcodeURL(username);

			assertEquals(key.getKey(), result.getFirst());
			assertEquals(otpAuthURL, result.getSecond());
		}
	}

	@Test
	public void testValidateCode() {
		int code = 123456;
		String secret = "secretKey";

		when(gAuth.authorize(secret, code)).thenReturn(true);

		boolean result = twoFactorAuthService.validateCode(secret, code);

		assertEquals(true, result);
	}
}
