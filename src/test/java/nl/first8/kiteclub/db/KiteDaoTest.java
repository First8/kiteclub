package nl.first8.kiteclub.db;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import nl.first8.kiteclub.domain.Kite;

public class KiteDaoTest {
	@Test
	public void testGetByOwner() throws Exception {
		KiteDao dao = new KiteDao();
		dao.postConstruct();

		assertThat(dao.getByOwner(11), hasItem(kiteWithName("Toothless")));

		List<Kite> kitesOfOwner12 = dao.getByOwner(12);
		assertThat(kitesOfOwner12, hasItem(kiteWithName("Stormfly")));
		assertThat(kitesOfOwner12, hasItem(kiteWithName("Cloudjumper")));
		assertThat(kitesOfOwner12, hasItem(kiteWithName("Skullcrusher")));
		assertThat(kitesOfOwner12, hasSize(3));
	}

	private TypeSafeMatcher<Kite> kiteWithName(String name) {
		return new TypeSafeMatcher<Kite>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Kite with name ");
				description.appendValue(name);
			}

			@Override
			protected boolean matchesSafely(Kite item) {
				return item.getName().equalsIgnoreCase(name);
			}
		};
	}
}
