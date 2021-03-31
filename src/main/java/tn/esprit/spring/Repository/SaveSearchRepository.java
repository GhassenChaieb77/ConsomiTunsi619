package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entities.SaveSearch;

public interface SaveSearchRepository extends CrudRepository<SaveSearch, Long> {
    @Query("select DISTINCT s "
			+"from SaveSearch s "
			+"where s.userid=:userid ")
     
	public List<SaveSearch> searchBYuser(@Param("userid") long userid);
}
