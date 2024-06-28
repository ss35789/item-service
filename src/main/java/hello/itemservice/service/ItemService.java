package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item){
        return itemRepository.save(item);
    }
    public Item findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void updateItem(Long itemId, Item updateParam) {
        itemRepository.updateItem(itemId, updateParam);
    }

    public Item deleteById(Long itemId) {
        return itemRepository.deleteById(itemId);
    }
}
