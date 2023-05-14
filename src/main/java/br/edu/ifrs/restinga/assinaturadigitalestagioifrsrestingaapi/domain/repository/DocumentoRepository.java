package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Long> {

}
