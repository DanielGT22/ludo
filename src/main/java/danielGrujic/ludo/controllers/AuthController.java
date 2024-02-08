package danielGrujic.ludo.controllers;

import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.BadRequestException;
import danielGrujic.ludo.payloads.AuthPayloads.AuthRequestDTO;
import danielGrujic.ludo.payloads.AuthPayloads.TokenResponseDTO;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRequestDto;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRespondDto;
import danielGrujic.ludo.services.AuthService;
import danielGrujic.ludo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespondDto register(@RequestBody @Validated UtenteRequestDto body,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
            return authService.save(body);
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO generateToken(@RequestBody AuthRequestDTO body){
        return authService.authenticateUser(body);
    }
}
