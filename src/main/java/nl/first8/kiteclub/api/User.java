package nl.first8.kiteclub.api;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Stateless
@Path("/user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(User.class.getName());

	private static final String ATTR_NICKNAME = "nickname";
	private static final String ATTR_AKA = "aka";

	@Resource
	private SessionContext ctx;

	@Context
	private HttpServletRequest servletRequest;

	@GET
	@RolesAllowed({ "Member" })
	@Produces("application/json")
	public Response get(@Context ServletContext servletContext) {
		return Response.status(Status.OK).entity(getSessionInfo(servletContext)).build();
	}

	private SessionInfo getSessionInfo(ServletContext servletContext) {
		// TODO: add user info
		SessionInfo sessionInfo = new SessionInfo(//
				servletRequest.getContextPath(), //
				null, // fullName
				null, // nickname
				getAccountUrl(servletContext));
		return sessionInfo;
	}

	/**
	 * Decode JSON Web Token and write to standard out (for debugging purposes).
	 *
	 * @param token
	 */
	protected void dumpJSONWebToken(String token) {
		if (token == null || token.isEmpty())
			return;

		System.out.println("***** Dumping JSON Web Token *****");
		String[] jwt = token.split("\\.");

		assert jwt.length == 3;

		try {
			System.out.println("Header: " + new String(Base64.getDecoder().decode(jwt[0]), "UTF-8"));
			System.out.println("Body: " + new String(Base64.getDecoder().decode(jwt[1]), "UTF-8"));
			System.out.println("Signature: " + jwt[2]);

		} catch (UnsupportedEncodingException e) {
			System.out.println("Error decoding token: " + e.getMessage());
		}
	}

	@GET
	@PermitAll
	@Path("/logout")
	public Response logout(@Context ServletContext servletContext) {
		try {
			servletRequest.logout();
			HttpSession session = servletRequest.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			return Response.temporaryRedirect(new URI(getLogoutLocation(servletContext))).build();
		} catch (URISyntaxException | ServletException e) {
			LOG.log(Level.WARNING, "Could not logout", e);
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	public String getAccountUrl(@Context ServletContext servletContext) {
		String targetUri = getTargetUri(servletContext);

			return String.format(
				"%s/realms/%s/account/" //
						+ "?referrer=%s", //
				"deployment.getAuthServerBaseUrl()", //
				"deployment.getRealm()", //
				targetUri);
	}

	private String getLogoutLocation(ServletContext servletContext) {
		String targetUri = getTargetUri(servletContext);

		return String.format(
				"%s/realms/%s/tokens/logout" //
						+ "?redirect_uri=%s", //
				"deployment.getAuthServerBaseUrl()", //
				"deployment.getRealm()", //
				targetUri);
	}

	private String getTargetUri(ServletContext servletContext) {
		// TODO: find out where this can be set...
		String targetUri = servletContext.getInitParameter("kiteclub.base.url");
		if (targetUri != null) {
			return targetUri;
		}
		return "http://localhost:8580/kiteclub";
	}
}
