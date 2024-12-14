package br.com.transaction.pix.api.infraestructure.repository;

import br.com.transaction.pix.api.infraestructure.entity.Pix;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.transaction.pix.api.infraestructure.repository.filter.api.PixRepositoryFilter;

@Repository
public interface PixRepository extends CrudRepository<Pix, Long>, PixRepositoryFilter {

}
