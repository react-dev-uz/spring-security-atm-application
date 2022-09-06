package uz.pdp.springsecurityatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurityatm.service.CardService;

import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    @Secured("ROLE_DIRECTOR")
    public ResponseEntity<?> getCards(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getAllCards(page, size);
    }

    @GetMapping("/{cardId}")
    @Secured({"ROLE_DIRECTOR", "ROLE_MANAGER"})
    public ResponseEntity<?> getCard(@PathVariable("cardId") UUID id) {
        return service.getCard(id);
    }
}
