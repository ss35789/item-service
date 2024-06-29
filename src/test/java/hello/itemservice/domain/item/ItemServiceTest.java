package hello.itemservice.domain.item;

import hello.itemservice.exceptions.ItemNotFoundException;
import hello.itemservice.service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @BeforeEach
    void beforeEach(){
        itemService.clearStore();
    }

    @AfterEach
    void afterEach() {
        itemService.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemService.save(item);

        //then
        Item findItem = itemService.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findById() {
        //given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemService.save(item);

        //when
        Item findItem = itemService.findById(savedItem.getId());

        //then
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findById_notFound() {
        //when, then
        assertThatThrownBy(() -> itemService.findById(999L))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Item not found with id 999");
    }

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
        Item updateParam = new Item("updatedItem", 20000, 20);
        itemService.updateItem(itemId, updateParam);

        //then
        Item findItem = itemService.findById(itemId);
        assertThat(findItem.getName()).isEqualTo(updateParam.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void updateItem_notFound() {
        //given
        Item updateParam = new Item("updatedItem", 20000, 20);

        //when, then
        assertThatThrownBy(() -> itemService.updateItem(999L, updateParam))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Item not found with id 999");
    }

    @Test
    void deleteById() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        itemService.save(item1);
        Long itemId = item1.getId();

        //when
        itemService.deleteById(itemId);

        //then
        assertThatThrownBy(() -> itemService.findById(itemId))
                .isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void deleteById_notFound() {
        //when, then
        assertThatThrownBy(() -> itemService.deleteById(999L))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Item not found with id 999");
    }


}
