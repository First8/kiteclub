package nl.first8.kiteclub.api;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

		return Response.status(200).entity(kite).build();
	}

	@GET
	@Produces("application/json")
	@Path("/mine")
	@RolesAllowed({ "Member" })
	public Response getMine() {
		return Response.status(200).entity(kiteDao.getByOwner(getEmployeeNumber())).build();
	}

	private int getEmployeeNumber() {
		// TODO: read the employeeNumber from somewhere...
		return 0;
	}
}