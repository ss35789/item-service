package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

//    @Test
//    void findById() {
//    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> list = itemRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 10000, 10);

        itemRepository.save(item1);
        Long itemId = item1.getId();


        //when
        Item updateItem1 = new Item("upitem1", 10000, 10);

        itemRepository.updateItem(itemId,updateItem1);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getName()).isEqualTo(updateItem1.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem1.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem1.getQuantity());
    }

    @Test
    void clearStore() {
    }
}