package br.com.transaction.pix.api.infraestructure.repository;

import br.com.transaction.pix.api.infraestructure.entity.PixRemetente;
import br.com.transaction.pix.api.infraestructure.repository.filter.api.PixRemetenteRepositoryFilter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRemetenteRepository extends CrudRepository<PixRemetente, Long>, PixRemetenteRepositoryFilter {

}
