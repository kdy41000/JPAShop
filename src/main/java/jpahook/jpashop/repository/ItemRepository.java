package jpahook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpahook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	public void save(Item item) {
		
		if(item.getId() == null) {
			em.persist(item);  // insert
		} else {  // merge���ٴ� findOne���� ��ȸ �� ������Ʈ �� �ʵ忡�� setter�� ���� �����ϴ� ���� �� ����.(merge�� ������ ��� ���ϴ� ���� ����.)
			em.merge(item);  // update(������ ��� �ʵ带 update�ϹǷ� �Ķ���ͷ� ���õ� ���� ������ null�� ������Ʈ �� ���ɼ��� ����, �����ʿ�)
		}
		
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
	
}
