package application;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.domain.relation.SystemRelations;
import cern.mpe.systems.core.service.manage.SystemsManager;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemRelationProvider;

public class RandomGenRelations implements SystemRelationProvider{
	
	private Collection<SystemRelation> relations;
	
	public void genAllRelations(SystemsManagerImpl systemManager) {

        Random random = new Random();
        Collection<SystemRelation> relations = new HashSet<>();
        Collection<SystemUnderTest> systems = systemManager.getAllSystemsUnderTest();
        for (int i=0; i<10; i++) {
        	int id1 = 0;
        	int id2 = 0;
        	id1 = random.nextInt(systemManager.getAllSystemsUnderTest().size());
        	while (id1==id2)
        		id2 = random.nextInt(systemManager.getAllSystemsUnderTest().size());
        	
                        relations.add(SystemRelations.unspecifiedFromTo((SystemUnderTest)systems.toArray()[id1],
                        		(SystemUnderTest)systems.toArray()[id2]));
        }
}
		
	    @Override
	    public Collection<SystemRelation> getAllSystemRelations(SystemsManager systemsManager) {
	        Set<SystemRelation> relationsToReturn = new HashSet<>();
	        for (SystemUnderTest systemUnderTest : systemsManager.getAllSystemsUnderTest()) {
	            SystemRelation relation = findRelation(systemUnderTest);
	            if (relation != null && !relationsToReturn.contains(relation)) {
	                relationsToReturn.add(relation);
	            }
	        }
	        return relationsToReturn;
	    }

	    private SystemRelation findRelation(SystemUnderTest system) {
	        for (SystemRelation relation : relations) {
	            if (relation.getSource().equals(system) || relation.getTarget().equals(system)) {
	                return relation;
	            }
	        }
	        return null;
	    }

}
