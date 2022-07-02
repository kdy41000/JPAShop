package jpahook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import jpahook.jpashop.domain.category.Category;
import jpahook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // db의 슈퍼타입 서브타입 논리 모델을 실제 물리 모델로 구현하는 기능
@DiscriminatorColumn(name = "dtype") // 부모 클래스에 선언한다. 하위 클래스를 구분하는 용도의 컬럼이다. 관례는 default = DTYPE
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
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<Category>();
	
	//==비즈니스 로직==//
	/**
	 * stock 증가
	 * */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	/**
	 * stock 감소
	 * */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
	
}
