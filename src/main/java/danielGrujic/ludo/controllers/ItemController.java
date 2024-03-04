package danielGrujic.ludo.controllers;

import danielGrujic.ludo.entities.Asosijacija;
import danielGrujic.ludo.entities.Items;
import danielGrujic.ludo.payloads.AsosijacijePayloads.AsoRequestDTO;
import danielGrujic.ludo.payloads.Item.ItemRequestDTO;
import danielGrujic.ludo.repositories.ItemmsRepository;
import danielGrujic.ludo.repositories.QuizzRepository;
import danielGrujic.ludo.services.AuthService;
import danielGrujic.ludo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthService authService;


    @Autowired
    private ItemmsRepository itemmsRepository;;





    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Items> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "100") int size,
                               @RequestParam(defaultValue = "uuid") String ordetBy) {
        return itemService.getAll(page, size, ordetBy);
    }


    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Items findById(@PathVariable UUID uuid) {
        return itemService.findByUuid(uuid);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Items createItem(@Validated @RequestBody ItemRequestDTO itemRequestDTO) {
        return itemService.save(itemRequestDTO);
    }
}
