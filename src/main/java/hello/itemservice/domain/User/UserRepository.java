package hello.itemservice.domain.User;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private long sequence = 0l;
    private Map<Long, User> usersMap = new HashMap<>();

    public User save(User user){
        user.setId(++sequence);
        usersMap.put(sequence, user);
        return user;
    }
    public User findById(Long id){

        return usersMap.get(id);
    }
    public List<User> findByAll(){
        return new ArrayList<User>(usersMap.values());
    }
    public User update(Long id, User user){
        User u = usersMap.get(id);
        u.setName(user.getName());
        u.setBirthDay(user.getBirthDay());


        return u;
    }
    public User deleteById(Long id){
        return usersMap.remove(id);
    }


}
