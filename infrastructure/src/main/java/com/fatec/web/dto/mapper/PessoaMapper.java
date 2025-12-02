package com.fatec.web.dto.mapper;

import com.fatec.entity.Pessoa;
import com.fatec.web.dto.PessoaCreateDto;
import com.fatec.web.dto.PessoaResponseDto;
import com.fatec.web.dto.PessoaUpdateDto;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PessoaMapper {

    private static String datePattern = "dd/MM/yyyy";

    private PessoaMapper() {
    }

    public static Pessoa toEntity(PessoaCreateDto createDto){
        LocalDateTime date = LocalDate.parse(createDto.getDtNasc(), DateTimeFormatter.ofPattern(datePattern))
                .atStartOfDay();
        ModelMapper modelMapper = new ModelMapper();
        Pessoa pessoa = modelMapper.map(createDto, Pessoa.class);
        pessoa.setDtNasc(date);
        return pessoa;
    }

    public static Pessoa toEntity(PessoaUpdateDto createDto){
        LocalDateTime date = null;
        if (createDto.getDtNasc() != null) {
            date = LocalDate.parse(createDto.getDtNasc(), DateTimeFormatter.ofPattern(datePattern))
                    .atStartOfDay();
        }
        ModelMapper modelMapper = new ModelMapper();
        Pessoa pessoa = modelMapper.map(createDto, Pessoa.class);
        pessoa.setDtNasc(date);
        return pessoa;
    }

    public static PessoaResponseDto toResponse(Pessoa pessoa){
        String date = pessoa.getDtNasc().toLocalDate().format(DateTimeFormatter.ofPattern(datePattern));
        ModelMapper mapper = new ModelMapper();
        PessoaResponseDto dto = mapper.map(pessoa, PessoaResponseDto.class);
        dto.setDtNasc(date);
        return dto;
    }

}
