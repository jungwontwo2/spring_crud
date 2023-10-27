package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
//public class ItemRepository {
//    private static final Map<Long,Item> store = new HashMap<>();
//    private static long sequence=0L;
//
//    public Item save(Item item){
//        item.setId(++sequence);
//        store.put(item.getId(),item);
//        return item;
//    }
//
//    public Item findById(Long id){
//        return store.get(id);
//    }
//
//    public List<Item> findAll(){
//        return new ArrayList<>(store.values());
//    }
//
//    public void update(Long itemId,Item updateParam){
//        Item findItem = findById(itemId);
//        findItem.setItemName(updateParam.getItemName());
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setQuantity(updateParam.getQuantity());
//    }
//    public void deleteItem(Long itemId){
//        store.remove(itemId);
//        //System.out.println("ItemRepository.deleteItem");
//    }
//    public List<Item> searchById(Long searchById){
//        List<Item> items = new ArrayList<>();
//        for (Long itemId : store.keySet()) {
//            if(itemId.equals(searchById)){
//                Item item = store.get(itemId);
//                items.add(item);
//            }
//        }
//        return items;
//    }
//    public List<Item> searchByName(String searchByName){
//        List<Item> items = new ArrayList<>();
//        for (Item item : store.values()) {
//            if(item.getItemName().equals(searchByName))
//            {
//                items.add(item);
//            }
//        }
//        return items;
//    }
//    public void clearStore(){
//        store.clear();
//    }
//}
@Repository
@Transactional
public class ItemRepository {
    private final EntityManager em;
    private static final Map<Long,Item> store = new HashMap<>();
    private static long sequence=0L;

    public ItemRepository(EntityManager em) {
        this.em = em;
    }

//    public Item save(Item item){
//        item.setId(++sequence);
//        store.put(item.getId(),item);
//        return item;
//    }
    public ItemEntity save(Item item){
//        item.setId(++sequence);
//        store.put(item.getId(),item);
        ItemEntity itemEntity = item.toEntity();
        em.persist(itemEntity);
        return itemEntity;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId,Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void deleteItem(Long itemId){
        store.remove(itemId);
        //System.out.println("ItemRepository.deleteItem");
    }
    public List<Item> searchById(Long searchById){
        List<Item> items = new ArrayList<>();
        for (Long itemId : store.keySet()) {
            if(itemId.equals(searchById)){
                Item item = store.get(itemId);
                items.add(item);
            }
        }
        return items;
    }
    public List<Item> searchByName(String searchByName){
        List<Item> items = new ArrayList<>();
        for (Item item : store.values()) {
            if(item.getItemName().equals(searchByName))
            {
                items.add(item);
            }
        }
        return items;
    }
    public void clearStore(){
        store.clear();
    }
}