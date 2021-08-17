import com.pvapp.PVApp.Repositories.DBRepositories.*;
import com.pvapp.PVApp.Services.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ConstructionDBRepoTest.class, InstalationDBRepoTest.class, InverterDBRepoTest.class, PriceDBRepoTest.class,
		ProductionDBRepositoryTest.class,PVModuleDBRepoTest.class,QuestionFormDBRepoTest.class,TechnicalResultsDBTest.class,
		ConstructionServiceTest.class, InstalationServiceTest.class, InverterServiceTest.class,PVModuleDBRepoTest.class,
		QuestionFormDBRepoTest.class, PriceServiceTest.class, TechnicalResultServiceTest.class

})

public class PvAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
