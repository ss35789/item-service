package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";

    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @DeleteMapping("/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/items";
    }




    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId,
            @ModelAttribute Item item , Model model){
//        Item findItem = itemRepository.findById(itemId);
//        findItem.setName(item.getName());
//        findItem.setPrice(item.getPrice());
//        findItem.setQuantity(item.getQuantity());
        itemRepository.updateItem(itemId, item);

//        model.addAttribute("item", findItem);

        return "redirect:/basic/items/{itemId}";
    }




    @GetMapping("/add")
    public String addForm(){

        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String save(@ModelAttribute Item item, Model model){
//        itemRepository.save(item);
//
//        //model.addAttribute("item", item);
//        return "basic/item";
//    }

//    @PostMapping("/add")
//    public String save2( Item item, Model model){
//        itemRepository.save(item);
//
//        //model.addAttribute("item", item);
//        return "basic/item";
//    }

    @PostMapping("/add")
    public String save(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Item saveditem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",saveditem.getId());

        redirectAttributes.addAttribute("status",true);

        //model.addAttribute("item", item);
        return "redirect:/basic/items/{itemId}"; //리다이렉트로 PRG문제 예방
    }






    /*  테스트 용 데이터 */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 12000, 12));
    }




}
