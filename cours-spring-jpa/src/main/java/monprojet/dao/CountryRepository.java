package monprojet.dao;
import java.util.List;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query (value="SELECT SUM(City.Population) AS Population"
            + "FROM Country "
            + "INNER JOIN City ON City.id = Country.id "
            + "WHERE Country.id = :numero ",
             nativeQuery = true)
    public int PopulationPourLePays(Integer numero);

    @Query (value="SELECT Country.name, SUM(City.population) AS Population" 
            + "FROM Country" 
            + "INNER JOIN City ON City.id = Country.id" 
            + "GROUP BY Country.name",
             nativeQuery = true)
    public List<Country> ListPopulationParPays();
}
