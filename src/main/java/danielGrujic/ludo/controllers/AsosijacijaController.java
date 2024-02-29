package danielGrujic.ludo.controllers;

import danielGrujic.ludo.entities.Asosijacija;
import danielGrujic.ludo.payloads.AsosijacijePayloads.AsoRequestDTO;
import danielGrujic.ludo.repositories.AsosijacijeRepository;
import danielGrujic.ludo.repositories.QuizzRepository;
import danielGrujic.ludo.services.AsosijacijeService;
import danielGrujic.ludo.services.AuthService;
import danielGrujic.ludo.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/asocijacije")
public class AsosijacijaController {

    @Autowired
    private AsosijacijeService asosijacijeService ;

    @Autowired
    private AuthService authService;

    @Autowired
    private AsosijacijeRepository asosijacijeRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Asosijacija> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "uuid") String ordetBy) {
        return asosijacijeService.getAll(page, size, ordetBy);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Asosijacija findById(@PathVariable UUID uuid) {
        return asosijacijeService.findByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Asosijacija createQuizz(@Validated @RequestBody AsoRequestDTO asoRequestDTO) {
        return asosijacijeService.save(asoRequestDTO);
    }
}
