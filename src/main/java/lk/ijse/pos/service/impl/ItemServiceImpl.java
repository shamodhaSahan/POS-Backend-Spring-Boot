package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.service.ItemService;
import lk.ijse.pos.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    DataTypeConvertor conversion;

    @Override
    public ItemDTO getItemByCode(String code) {
        return null;
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {

    }

    @Override
    public void deleteItemByCode(String code) {

    }

    @Override
    public List<ItemDTO> getAllItem() {
        return null;
    }
}
