package br.com.transaction.pix.api.application.mapper;

import java.util.List;

import br.com.transaction.pix.api.infraestructure.entity.Pix;
import org.mapstruct.Mapper;

import br.com.transaction.pix.api.application.dto.PixDto;

@Mapper()
public interface AccountPaymentMapper {

    Pix toEntity(PixDto pixDto);
    PixDto toDto(Pix pix);
    List<PixDto> toDtoList(List<Pix> pixDtoList);

}
