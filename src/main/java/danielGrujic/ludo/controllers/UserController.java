package danielGrujic.ludo.controllers;



import danielGrujic.ludo.entities.User;
import danielGrujic.ludo.exceptions.BadRequestException;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteRespondDto;
import danielGrujic.ludo.payloads.UtentePayloads.UtenteUpdateRequestDto;
import danielGrujic.ludo.repositories.UserRepository;
import danielGrujic.ludo.services.AuthService;
import danielGrujic.ludo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0")int page,
                              @RequestParam(defaultValue = "100")int size,
                              @RequestParam(defaultValue = "uuid")String ordetBy){
        return userService.getAll(page, size, ordetBy);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID uuid){
        return userService.findByUUID(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable UUID uuid){
        userService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UtenteRespondDto modify(@RequestBody @Validated UtenteUpdateRequestDto body,
                                   @PathVariable UUID uuid, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("Error in payload for the PUT method" + bindingResult.getAllErrors());
        } else {
            return authService.put(body, uuid);
        }
    }


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User findById(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }


    @PostMapping("/me/add-friend/{friendId}")
    public ResponseEntity<String> addFriendToCurrentUser(
            @PathVariable("friendId") UUID friendId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        userService.addFriend(currentUser.getUuid(), friendId);

        return ResponseEntity.status(HttpStatus.OK).body("Friend added successfully.");
    }

}
