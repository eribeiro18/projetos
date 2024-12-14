package br.com.transaction.pix.api.application.service;

import br.com.transaction.pix.api.application.dto.PixRemetenteDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;
import br.com.transaction.pix.api.infraestructure.entity.PixRemetente;
import br.com.transaction.pix.api.infraestructure.repository.PixRemetenteRepository;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PixRemetenteService {

    private final PixRemetenteRepository pixRemetenteRepository;

    public PixRemetenteService(PixRemetenteRepository pixRemetenteRepository) {
        this.pixRemetenteRepository = pixRemetenteRepository;
    }

    public PixRemetente save(PixRemetenteDto pixRemetenteDto) {
        PixRemetente pixRemetente = toEntity(pixRemetenteDto);
        pixRemetente.setCreateAt(LocalDateTime.now());
        return pixRemetenteRepository.save(pixRemetente);
    }

    public PixRemetente update(PixRemetenteDto pixRemetenteDto) {
        pixRemetenteDto.validateUpdate();

        PixRemetente pixRemetente = findBy(pixRemetenteDto.getId())
                .orElseThrow(() -> new ValidationException("Registro Pix Remetente não encontrado"));

        pixRemetente.setNome(Optional.ofNullable(pixRemetenteDto.getNome()).orElse(pixRemetente.getNome()));
        pixRemetente.setDocumento(Optional.ofNullable(pixRemetenteDto.getDocumento()).orElse(pixRemetente.getDocumento()));
        pixRemetente.setBanco(Optional.ofNullable(pixRemetenteDto.getBanco()).orElse(pixRemetente.getBanco()));
        pixRemetente.setUpdateAt(LocalDateTime.now());

        return pixRemetenteRepository.save(pixRemetente);
    }

    public Optional<PixRemetente> findBy(Long id) {
        return pixRemetenteRepository.findById(id);
    }

    public void delete(Long id) {
        PixRemetente pixRemetente = pixRemetenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro Pix Remetente não encontrado"));
        try {
            pixRemetenteRepository.delete(pixRemetente);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Exclusão não autorizada, existem registros vinculados a este Pix Remetente.");
        }
    }

    private PixRemetente toEntity(PixRemetenteDto pixRemetenteDto) {
        PixRemetente pixRemetente = new PixRemetente();
        pixRemetente.setId(pixRemetenteDto.getId());
        pixRemetente.setNome(pixRemetenteDto.getNome());
        pixRemetente.setDocumento(pixRemetenteDto.getDocumento());
        pixRemetente.setBanco(pixRemetenteDto.getBanco());
        pixRemetente.setPix(Pix.builder().id(pixRemetenteDto.getPixId()).build());
        return pixRemetente;
    }
}
