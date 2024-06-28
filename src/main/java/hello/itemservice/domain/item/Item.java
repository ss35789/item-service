package hello.itemservice.domain.item;

import hello.itemservice.domain.User.User;
import lombok.Data;


@Data
public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private User user;

    public Item(){

    }
    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
