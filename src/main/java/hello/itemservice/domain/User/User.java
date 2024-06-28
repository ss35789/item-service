package hello.itemservice.domain.User;

import hello.itemservice.domain.item.Item;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;
    private String name;
    private Date birthday;
    private List<Long> uploadItems; //item's id

    public User(){}

    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
