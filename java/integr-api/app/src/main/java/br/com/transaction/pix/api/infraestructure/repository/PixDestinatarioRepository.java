package br.com.transaction.pix.api.infraestructure.repository;

import br.com.transaction.pix.api.infraestructure.entity.PixDestinatario;
import br.com.transaction.pix.api.infraestructure.repository.filter.api.PixDestinatarioRepositoryFilter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixDestinatarioRepository extends CrudRepository<PixDestinatario, Long>, PixDestinatarioRepositoryFilter {

}
