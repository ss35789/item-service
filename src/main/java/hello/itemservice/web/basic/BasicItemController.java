package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemService itemService;


    @GetMapping
    public String items(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

   @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
   }

   @GetMapping("/add")
    public String save(){
        return "basic/addForm";
   }

   @PostMapping("/add")
    public String save(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        itemService.save(item);
       redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/basic/items/{itemId}";
   }

   @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model){
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
   }

   @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,
                       @ModelAttribute Item item){
        itemService.updateItem(itemId, item);

        return "redirect:/basic/items/{itemId}";
   }

   @DeleteMapping("/{itemId}")
    public ResponseEntity<String> delete(@PathVariable Long itemId){
        itemService.deleteById(itemId);

        return new ResponseEntity<>("데이터가 삭제되었습니다", HttpStatus.OK);
   }

}
