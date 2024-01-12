package uk.ac.bangor.jml20vql.csee.NHSPrototype2.repository;

import org.springframework.data.repository.CrudRepository;

import uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance.Distance;

public interface DistanceRepository extends CrudRepository<Distance, String> {
	
}
