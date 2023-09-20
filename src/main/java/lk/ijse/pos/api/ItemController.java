package lk.ijse.pos.api;

import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.service.ItemService;
import lk.ijse.pos.util.RegexValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final RegexValidator regexValidator;

    public ItemController(ItemService itemService, RegexValidator regexValidator) {
        this.itemService = itemService;
        this.regexValidator = regexValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveItem(@RequestBody ItemDTO itemDTO) {
        regexValidator.itemValidation(itemDTO);
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
    ResponseEntity<?> updateItem(@PathVariable String code, @RequestBody ItemDTO itemDTO) {
        regexValidator.itemValidation(itemDTO);
        itemDTO.setItemCode(code);
        itemService.updateItem(itemDTO);
        return new ResponseEntity<>("Item " + code + " is updated", HttpStatus.OK);
    }
}
