package com.fatec.web.controller;

import com.fatec.entity.Pessoa;
import com.fatec.service.PessoaService;
import com.fatec.web.dto.PessoaCreateDto;
import com.fatec.web.dto.PessoaResponseDto;
import com.fatec.web.dto.PessoaUpdateDto;
import com.fatec.web.dto.mapper.PessoaMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDto> createPessoa(@Valid @RequestBody PessoaCreateDto pessoa){
        Pessoa p = service.save(PessoaMapper.toEntity(pessoa));
        return ResponseEntity.status(HttpStatus.CREATED).body(PessoaMapper.toResponse(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> getById(@PathVariable Long id){
        Pessoa p = service.findById(id);
        return ResponseEntity.ok(PessoaMapper.toResponse(p));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponseDto>> getAll(Pageable pageable){
        List<Pessoa> p = service.listAll();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), p.size());
        List<Pessoa> subList = p.subList(start, end);
        Page<Pessoa> page = new PageImpl<>(subList, pageable, p.size());
        Page<PessoaResponseDto> dtoPage = page.map(PessoaMapper::toResponse);
        return ResponseEntity.ok(dtoPage);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable Long id, @Valid @RequestBody PessoaUpdateDto pessoa){
        Pessoa p = service.update(id, PessoaMapper.toEntity(pessoa));
        return ResponseEntity.ok(PessoaMapper.toResponse(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
