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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // db�� ����Ÿ�� ����Ÿ�� �� ���� ���� ���� �𵨷� �����ϴ� ���
@DiscriminatorColumn(name = "dtype") // �θ� Ŭ������ �����Ѵ�. ���� Ŭ������ �����ϴ� �뵵�� �÷��̴�. ���ʴ� default = DTYPE
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
	
	//==����Ͻ� ����==//
	/**
	 * stock ����
	 * */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	/**
	 * stock ����
	 * */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
	
}
