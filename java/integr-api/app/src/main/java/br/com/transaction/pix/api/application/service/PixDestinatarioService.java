package br.com.transaction.pix.api.application.service;

import br.com.transaction.pix.api.application.dto.PixDestinatarioDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;
import br.com.transaction.pix.api.infraestructure.entity.PixDestinatario;
import br.com.transaction.pix.api.infraestructure.repository.PixDestinatarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PixDestinatarioService {

    private final PixDestinatarioRepository pixDestinatarioRepository;

    public PixDestinatarioService(PixDestinatarioRepository pixDestinatarioRepository) {
        this.pixDestinatarioRepository = pixDestinatarioRepository;
    }

    public PixDestinatario save(PixDestinatarioDto pixDestinatarioDto) {
        PixDestinatario pixDestinatario = toEntity(pixDestinatarioDto);
        pixDestinatario.setCreateAt(LocalDateTime.now());
        return pixDestinatarioRepository.save(pixDestinatario);
    }

    public PixDestinatario update(PixDestinatarioDto pixDestinatarioDto) {
        pixDestinatarioDto.validateUpdate();

        PixDestinatario pixDestinatario = findBy(pixDestinatarioDto.getId())
                .orElseThrow(() -> new ValidationException("Registro Pix Destinatário não encontrado"));

        pixDestinatario.setNome(Optional.ofNullable(pixDestinatarioDto.getNome()).orElse(pixDestinatario.getNome()));
        pixDestinatario.setDocumento(Optional.ofNullable(pixDestinatarioDto.getDocumento()).orElse(pixDestinatario.getDocumento()));
        pixDestinatario.setBanco(Optional.ofNullable(pixDestinatarioDto.getBanco()).orElse(pixDestinatario.getBanco()));
        pixDestinatario.setUpdateAt(LocalDateTime.now());

        return pixDestinatarioRepository.save(pixDestinatario);
    }

    public Optional<PixDestinatario> findBy(Long id) {
        return pixDestinatarioRepository.findById(id);
    }

    public void delete(Long id) {
        PixDestinatario pixDestinatario = pixDestinatarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro Pix Destinatário não encontrado"));
        try {
            pixDestinatarioRepository.delete(pixDestinatario);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Exclusão não autorizada, existem registros vinculados a este Pix Destinatário.");
        }
    }

    private PixDestinatario toEntity(PixDestinatarioDto pixDestinatarioDto) {
        PixDestinatario pixDestinatario = new PixDestinatario();
        pixDestinatario.setId(pixDestinatarioDto.getId());
        pixDestinatario.setNome(pixDestinatarioDto.getNome());
        pixDestinatario.setDocumento(pixDestinatarioDto.getDocumento());
        pixDestinatario.setBanco(pixDestinatarioDto.getBanco());
        pixDestinatario.setPix(Pix.builder().id(pixDestinatarioDto.getPixId()).build());
        return pixDestinatario;
    }
}
