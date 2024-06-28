package hello.itemservice.domain.item;

import hello.itemservice.service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @AfterEach
    void afterEach(){
        itemService.clearStore();
    }
    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemService.save(item);

        //then
        Item findItem = itemService.findById(item.getId());
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

        itemService.save(item1);
        itemService.save(item2);

        //when
        List<Item> list = itemService.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 10000, 10);

        itemService.save(item1);
        Long itemId = item1.getId();


        //when
        Item updateItem1 = new Item("upitem1", 10000, 10);

        itemService.updateItem(itemId,updateItem1);

        //then
        Item findItem = itemService.findById(itemId);
        assertThat(findItem.getName()).isEqualTo(updateItem1.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem1.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem1.getQuantity());
    }

    @Test
    void clearStore() {
    }
}