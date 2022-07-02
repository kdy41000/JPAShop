package jpahook.jpashop.domain.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jpahook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "category_item",  // 다대다 조인테이블명(다대다 관계 실무에서는 사용 비추천)
				joinColumns = @JoinColumn(name = "category_id"),  // fk 
				inverseJoinColumns = @JoinColumn(name = "item_id"))  // 반대 엔티티의 fk
	private List<Item> items = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();

	// ==연관관계 메서드==//
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}

}
