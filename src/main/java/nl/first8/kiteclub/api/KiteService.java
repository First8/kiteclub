package nl.first8.kiteclub.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import nl.first8.kiteclub.db.KiteDao;
import nl.first8.kiteclub.domain.Kite;

@Stateless
@Path("/kites")
public class KiteService {
	private static final Logger LOG = Logger.getLogger(KiteService.class.getName());

	private static final String ATTR_ID = "employeeNumber";

	@Inject
	private KiteDao kiteDao;

	@Resource
	private SessionContext ctx;

	@GET
	@Produces("application/json")
	@RolesAllowed({ "Admin" })
	public Response all() {
		return Response.status(200).entity(kiteDao.all()).build();
	}

	@GET
	@Produces("application/json")
	@Path("/{id}")
	@RolesAllowed({ "Member" })
	public Response get(@PathParam("id") int id) {
		Kite kite = kiteDao.get(id);
		if (kite == null) {
			return Response.status(Status.NOT_FOUND).encoding("Kite not found").build();
		}

		if (kite.getOwner() != getEmployeeNumber()) {
			return Response.status(Status.FORBIDDEN).encoding("You are not the owner of this kite").build();
		}
		return Response.status(200).entity(kite).build();
	}

	@GET
	@Produces("application/json")
	@Path("/mine")
	@RolesAllowed({ "Member" })
	public Response getMine() {
		return Response.status(200).entity(kiteDao.getByOwner(getEmployeeNumber())).build();
	}

	Integer getEmployeeNumber() {
		@SuppressWarnings("unchecked")
		KeycloakPrincipal<KeycloakSecurityContext> p = (KeycloakPrincipal<KeycloakSecurityContext>) ctx
				.getCallerPrincipal();
		AccessToken token = p.getKeycloakSecurityContext().getToken();
		Map<String, Object> otherClaims = token.getOtherClaims();
		dumpClaims(otherClaims);
		Object idObject = otherClaims.get(ATTR_ID);
		if (idObject == null) {
			throw new ForbiddenException("user-employeeNumber attribute is null -> not set");
		}
		return Integer.parseInt((String) idObject);
	}

	static void dumpClaims(Map<String, Object> otherClaims) {
		for (String key : otherClaims.keySet()) {
			LOG.log(Level.INFO, "Key {0}: value: {1}", new Object[] { key, otherClaims.get(key) });
		}
	}
}