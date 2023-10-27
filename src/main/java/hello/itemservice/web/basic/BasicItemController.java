package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemEntity;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.ItemRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;
    private final ItemRepository2 itemRepository2;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        //System.out.println("BasicItemController.items");
        return "basic/items";
    }
    @DeleteMapping("/{itemId}")
    public String deleteItem(@PathVariable Long itemId, Model model){
        //System.out.println("BasicItemController.deleteItem");
        itemRepository.deleteItem(itemId);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "redirect:/basic/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));


    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        //System.out.println("BasicItemController.item");
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        System.out.println("item = " + item);
        itemRepository.save(item);

        //model.addAttribute("item",item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        //model.addAttribute("item",item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        //model.addAttribute("item",item);
        return "basic/item";
    }
    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        //model.addAttribute("item",item);
        return "redirect:/basic/items/" +item.getId();
    }
    @PostMapping("/add")
    public String addItemV6(@ModelAttribute("item") Item item, RedirectAttributes redirectAttributes) {
        ItemEntity savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);

        //model.addAttribute("item",item);
        return "redirect:/basic/items/{itemId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        //System.out.println("BasicItemController.editForm");
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        //System.out.println("BasicItemController.edit");
        return "redirect:/basic/items/{itemId}";
    }

   @GetMapping("/searchById")
   public String searchById(@RequestParam("searchId") Long itemId, Model model){

       List<Item> items = itemRepository.searchById(itemId);
       model.addAttribute("items",items);
       return "/basic/searchForm";
   }
    @GetMapping("/searchByName")
    public String searchByName(@RequestParam("searchName") String itemId, Model model){
        //System.out.println("BasicItemController.searchByName");

        List<Item> items = itemRepository.searchByName(itemId);
        model.addAttribute("items",items);
        return "/basic/searchForm";
    }
}
