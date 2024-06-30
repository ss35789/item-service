package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item save(Item item){
        try{
            return itemRepository.save(item);

        }catch(Exception e){
            throw new RuntimeException("Failed to save item", e);
        }

    }
    public Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Item not found with id "+ id));
    }

    public List<Item> findAll(){
        try{
            return itemRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Failed to retrieve items", e);
        }
    }

    public Item updateItem(Long id, Item paramItem){

        Item existItem = findById(id);
        existItem.setName(paramItem.getName());
        existItem.setPrice(paramItem.getPrice());
        existItem.setQuantity(paramItem.getQuantity());

        try{
            itemRepository.save(existItem);

            return existItem;
        }catch(Exception e){
            throw new RuntimeException("Failed to update item", e);
        }
    }

    public void deleteById(Long id){
        Item item = findById(id);

        try{
            itemRepository.delete(item);
        }catch(Exception e){
            throw new RuntimeException("Failed to delete item", e);
        }
    }

    public void clearStore(){
        try{
            itemRepository.deleteAll();
        }catch(Exception e){
            throw new RuntimeException("Failed to clear items", e);
        }
    }


}
