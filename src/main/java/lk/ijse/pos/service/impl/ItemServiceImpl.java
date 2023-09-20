package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.exception.DuplicateException;
import lk.ijse.pos.exception.InUseException;
import lk.ijse.pos.exception.NotFoundException;
import lk.ijse.pos.persistance.ItemDao;
import lk.ijse.pos.persistance.OrderDetailsDao;
import lk.ijse.pos.service.ItemService;
import lk.ijse.pos.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final DataTypeConvertor convertor;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;

    public ItemServiceImpl(DataTypeConvertor convertor) {
        this.convertor = convertor;
    }

    @Override
    public ItemDTO getItemByCode(String code) {
        return convertor.getItemDTO(itemDao.findById(code).orElseThrow(() -> new NotFoundException("Item not found..!")));
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        if (itemDao.findByDescription(itemDTO.getDescription()).isPresent())
            throw new DuplicateException("Item description is duplicated..!");
        return convertor.getItemDTO(itemDao.save(convertor.getItemEntity(itemDTO)));

    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        itemDao.findByDescription(itemDTO.getDescription()).filter(itemForCheckDescription -> !itemForCheckDescription.getCode().equals(itemDTO.getCode())).ifPresentOrElse(__ -> {
            throw new DuplicateException("Item description is duplicated..!");
        }, () -> {
            itemDao.findById(itemDTO.getCode()).ifPresentOrElse(item -> {
                item.setDescription(itemDTO.getDescription());
                item.setQtyOnHand(itemDTO.getQtyOnHand());
                item.setUnitPrice(itemDTO.getUnitPrice());
            }, () -> {
                throw new NotFoundException("Item not found..!");
            });
        });
    }

    @Override
    public void deleteItemByCode(String code) {
        itemDao.findById(code).ifPresentOrElse(item -> orderDetailsDao.findOrderDetailsByItem(item).ifPresentOrElse(__ -> {
            throw new InUseException("Item have orders..!");
        }, () -> {
            itemDao.delete(item);
        }), () -> {
            throw new NotFoundException("Item not found..!");
        });
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return ((List<Item>) itemDao.findAll()).stream().map(item -> convertor.getItemDTO(item)).collect(Collectors.toList());
    }
}
