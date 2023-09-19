package lk.ijse.pos.service;

import lk.ijse.pos.dto.ItemDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */
public interface ItemService {
    ItemDTO getItemByCode(String code);
    ItemDTO saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItemByCode(String code);
    List<ItemDTO> getAllItem();
}
