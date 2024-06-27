package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(sequence, item);
        return item;
    }

    public Item findById(long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<Item>(store.values());
    }

    public void updateItem(Long itemId, Item updateParam){

        Item findItem = findById(itemId);

        findItem.setName(updateParam.getName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public Item deleteById(Long itemId){
       return store.remove(itemId);
    }

    public void clearStore(){
        store.clear();
    }


}
