package filters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import cern.mpe.systems.core.domain.SystemUnderTest;

public interface FilterInterface {
	
	public Collection<SystemUnderTest> filter(Collection<SystemUnderTest> listUnderTest);
}
