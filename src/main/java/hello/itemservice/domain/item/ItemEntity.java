package hello.itemservice.domain.item;

import lombok.Data;

import javax.persistence.*;

//@Data
//public class Item {
//    private Long id;
//    private String itemName;
//    private Integer price;
//    private Integer quantity;
//
//    public Item(){
//
//    }
//    public Item(String itemName, Integer price, Integer quantity) {
//        this.itemName = itemName;
//        this.price = price;
//        this.quantity = quantity;
//    }
//}
@Data
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemEntity(){

    }
    public ItemEntity(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}