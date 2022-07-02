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
		} else {  // merge보다는 findOne으로 조회 후 업데이트 할 필드에만 setter로 값을 설정하는 것이 더 낫다.(merge는 가급적 사용 안하는 것이 좋다.)
			em.merge(item);  // update(병합은 모든 필드를 update하므로 파라미터로 세팅된 값이 없으면 null로 업데이트 될 가능성이 있음, 주의필요)
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
