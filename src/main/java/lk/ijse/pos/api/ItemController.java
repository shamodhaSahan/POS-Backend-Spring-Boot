package lk.ijse.pos.api;

import jakarta.validation.Valid;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.exception.InvalidException;
import lk.ijse.pos.service.ItemService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 12:55 PM
 */

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin("*")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveItem(@Valid @RequestBody ItemDTO itemDTO, Errors errors) {
        if (errors.hasFieldErrors())
            throw new InvalidException(errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return new ResponseEntity<>(itemService.saveItem(itemDTO), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping(value = "{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSelectedItem(@PathVariable String code) {
        return new ResponseEntity<>(itemService.getItemByCode(code), HttpStatus.OK);
    }

    @DeleteMapping(value = "{code}")
    ResponseEntity<?> deleteItem(@PathVariable String code) {
        itemService.deleteItemByCode(code);
        return new ResponseEntity<>("Item " + code + " is deleted", HttpStatus.OK);
    }

    @PatchMapping(value = "{code}")
    ResponseEntity<?> updateItem(@PathVariable String code, @Valid @RequestBody ItemDTO itemDTO, Errors errors) {
        if (errors.hasFieldErrors())
            throw new InvalidException(errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        itemDTO.setCode(code);
        itemService.updateItem(itemDTO);
        return new ResponseEntity<>("Item " + code + " is updated", HttpStatus.OK);
    }
}
