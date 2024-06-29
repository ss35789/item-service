package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item){
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save item", e);
        }
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id " + id));
    }

    public List<Item> findAll() {
        try {
            return itemRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve items", e);
        }
    }

    public void updateItem(Long itemId, Item updateParam) {
        Item existingItem = findById(itemId);
        existingItem.setName(updateParam.getName());
        existingItem.setPrice(updateParam.getPrice());
        existingItem.setQuantity(updateParam.getQuantity());
        try {
            itemRepository.save(existingItem);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update item", e);
        }
    }

    public void deleteById(Long itemId) {
        try {
            itemRepository.delete(findById(itemId));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete item", e);
        }
    }

    public void clearStore() {
        try {
            itemRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear store", e);
        }
    }
}
