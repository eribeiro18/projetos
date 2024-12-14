package br.com.transaction.pix.api.application.service;

import br.com.transaction.pix.api.application.dto.PixDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;
import br.com.transaction.pix.api.infraestructure.repository.PixRepository;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PixService {

	private final PixRepository pixRepository;

	public PixService(PixRepository pixRepository) {
		this.pixRepository = pixRepository;
	}

	public Pix save(PixDto pixDto) {
		Pix pix = toEntity(pixDto);
		pix.setCreateAt(LocalDateTime.now());
		return pixRepository.save(pix);
	}

	public Pix update(PixDto pixDto) {
		pixDto.validateUpdate();

		Pix pix = findBy(pixDto.getId())
				.orElseThrow(() -> new ValidationException("Registro Pix não encontrado"));

		pix.setDescription(Optional.ofNullable(pixDto.getDescription()).orElse(pix.getDescription()));
		pix.setPaymentDate(Optional.ofNullable(pixDto.getPaymentDate()).orElse(pix.getPaymentDate()));
		pix.setPaymentAmount(Optional.ofNullable(pixDto.getPaymentAmount()).orElse(pix.getPaymentAmount()));
		pix.setSituation(Optional.ofNullable(pixDto.getSituation()).orElse(pix.getSituation()));
		pix.setUpdateAt(LocalDateTime.now());

		return pixRepository.save(pix);
	}

	public Optional<Pix> findBy(Long id) {
		return pixRepository.findById(id);
	}

	public void delete(Long id) {
		Pix pix = pixRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro Pix não encontrado"));
		try {
			pixRepository.delete(pix);
		} catch (DataIntegrityViolationException e) {
			throw new ValidationException("Exclusão não autorizada, existem registros vinculados a este Pix.");
		}
	}

	private Pix toEntity(PixDto pixDto) {
		Pix pix = new Pix();
		pix.setId(pixDto.getId());
		pix.setPaymentDate(pixDto.getPaymentDate());
		pix.setPaymentAmount(pixDto.getPaymentAmount());
		pix.setDescription(pixDto.getDescription());
		pix.setSituation(pixDto.getSituation());
		return pix;
	}
}
