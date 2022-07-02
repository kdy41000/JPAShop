package jpahook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpahook.jpashop.domain.item.Book;
import jpahook.jpashop.domain.item.Item;
import jpahook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	@Transactional
	public Item updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId); // 영속성(상태)
		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);
		
		// update call을 따로 할 필요가 없다. (영속성으로 조회 후 setter에서 이미 flush된 상태)
		return findItem;
	}
	
	public List<Item> findItems() {
		return itemRepository.findAll();
	}
	
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	
}
