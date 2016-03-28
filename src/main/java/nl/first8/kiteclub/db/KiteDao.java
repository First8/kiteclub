package nl.first8.kiteclub.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import nl.first8.kiteclub.domain.Kite;

@ApplicationScoped
public class KiteDao {
	private Map<Integer, Kite> kites = new HashMap<Integer, Kite>();

	@PostConstruct
	public void postConstruct() {
		put(new Kite(1, 11, "Toothless", "hexcopter.jpg"));
		put(new Kite(2, 12, "Stormfly", "quadcopter-met-lampen.jpg"));
		put(new Kite(3, 13, "Meatlug", "quadcopter-with-camera-2.jpg"));
		put(new Kite(4, 14, "Hookfang", "robot-op-quadcopter.jpg"));
		put(new Kite(5, 15, "Barf and Belch", "startup-driving-quadcopter.jpg"));
		// TODO: Find new images
		put(new Kite(6, 15, "Eret", "hexcopter.jpg"));
		put(new Kite(7, 12, "Cloudjumper", "hexcopter.jpg"));
		put(new Kite(8, 12, "Skullcrusher", "hexcopter.jpg"));
	}

	private void put(Kite kite) {
		if (kites.keySet().contains(kite.getId())) {
			throw new IllegalStateException("Kite already in list: " + kite.getName());
		}
		kites.put(kite.getId(), kite);
	}

	public Collection<Kite> all() {
		return kites.values();
	}

	public Kite get(int id) {
		return kites.get(id);
	}

	public List<Kite> getByOwner(int owner) {
		return kites.values().stream().filter(x -> x.getOwner() == owner).collect(Collectors.toList());
	}
}
