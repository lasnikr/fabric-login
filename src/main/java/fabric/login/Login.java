package fabric.login;

import java.net.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import fabric.login.mixin.MinecraftClientMixin;
import fabric.login.mixin.SessionMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

public class Login {
	private final Logger logger = LogManager.getLogger("fabric-login");
	/**
	 * Log into an account without a password. You only have the specified username
	 * in Singleplayer and on offline servers.
	 * <p />
	 * Logging into an online server will result in an error screen.
	 */
	public Login(String username) {
		new Thread(() -> {
			((SessionMixin) MinecraftClient.getInstance().getSession()).setUsername(username);
			logger.info("Logged in as: " + username);
		}).start();
	}

	/**
	 * Log into an account with an username and a password. It behaves the same way
	 * as logging into your account in the minecraft launcher.
	 */
	public Login(String email, String password) {
		this(email, password, Proxy.NO_PROXY, null, Agent.MINECRAFT, "mojang");
	}

	/**
	 * Log into an account with an username, a password, a proxy, a client token, an
	 * agent and an account type. It behaves the same way as logging into your
	 * account in the minecraft launcher but gives you more customization with
	 * changable parameters.
	 * <p />
	 * You can use the default values of
	 * {@link #Login(String email, String password)} if you don't want to change
	 * all parameters.
	 */
	public Login(String email, String password, Proxy proxy, String clientToken, Agent agent, String accountType) {
		new Thread(() -> {
			YggdrasilUserAuthentication userAuthentication = 
					(YggdrasilUserAuthentication) new YggdrasilAuthenticationService(proxy, clientToken)
					.createUserAuthentication(agent);

			userAuthentication.setUsername(email);
			userAuthentication.setPassword(password);

			try {
				userAuthentication.logIn();
				
				((MinecraftClientMixin) MinecraftClient.getInstance()).setSession(new Session(
						userAuthentication.getSelectedProfile().getName(), 
						userAuthentication.getUserID().toString(),
						userAuthentication.getAuthenticatedToken(), 
						accountType));
				
				logger.info("Logged in as: " + userAuthentication.getSelectedProfile().getName());
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
			
		}).start();
	}
}