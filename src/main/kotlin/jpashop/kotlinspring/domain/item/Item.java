package jpashop.kotlinspring.domain.item;


import jakarta.persistence.*;
import jpashop.kotlinspring.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Joined 는 가장 정규화된 스타일, SingleTable 은 한 테이블에 모두 모으는 것, TablePerClass 는 클래스마다 테이블로 나누는 전략
@DiscriminatorColumn(name = "dtype")
// 상품별로 구분.
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    /**
     * manyToMany 관계는 실무에서는 절대 사용하지 않는다. 중간에 각각을 매핑해주는 테이블이 있어야 하는데 여기서는
     * manyToMany 매핑을 보여주기 위해서 구현한 내용이다.
     */
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        // check
        int resultStock = this.stockQuantity - quantity;
        if (resultStock < 0) {
            // 변경 필요
            throw new RuntimeException("need more stock");
        }
        this.stockQuantity = resultStock;
    }
}
