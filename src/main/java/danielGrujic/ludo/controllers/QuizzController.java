package danielGrujic.ludo.controllers;

import danielGrujic.ludo.entities.Quizz;
import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.payloads.QuizzPayloads.QuizzRequestDTO;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRequestDto;
import danielGrujic.ludo.repositories.QuizzRepository;
import danielGrujic.ludo.services.AuthService;
import danielGrujic.ludo.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quizzes")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    @Autowired
    private AuthService authService;

    @Autowired
    private QuizzRepository quizzRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Quizz> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "uuid") String ordetBy) {
        return quizzService.getAll(page, size, ordetBy);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Quizz findById(@PathVariable UUID uuid) {
        return quizzService.findByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Quizz createQuizz(@Validated @RequestBody QuizzRequestDTO quizzRequestDTO) {
        return quizzService.save(quizzRequestDTO);
    }
}
