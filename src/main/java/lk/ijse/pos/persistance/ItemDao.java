package lk.ijse.pos.persistance;

import lk.ijse.pos.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 11:38 PM
 */

public interface ItemDao extends CrudRepository<Item, String> {
    Optional<Item> findByDescription(String description);
}
