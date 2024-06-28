package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    // 생성자 주입을 사용합니다.
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void updateItem(Long itemId, Item updateParam) {
        Item existingItem = findById(itemId);
        if (existingItem != null) {
            existingItem.setName(updateParam.getName());
            existingItem.setPrice(updateParam.getPrice());
            existingItem.setQuantity(updateParam.getQuantity());
            itemRepository.save(existingItem);
        }
    }

    public void deleteById(Long itemId) {
        itemRepository.delete(findById(itemId));
    }

    public void clearStore() {
        itemRepository.deleteAll();
    }
}
