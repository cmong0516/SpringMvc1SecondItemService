package hello.itemservice;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // Item 객체 itme 생성
        Item item = new Item("itemA", 10000, 10);
        // item 저장
        Item savedItem = itemRepository.save(item);
        // 저장한 item 찾기
        Item findItem = itemRepository.findById(item.getId());
        // 정상처리 되었다면 저장한 아이템 = 찾은 아이템
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // 두 Item 객체 item1, item2 생성
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        // 생성한 item1, item2 save
        itemRepository.save(item1);
        itemRepository.save(item2);

        // itemRepository.findAll() 로 저장된 모든 객체 찾기.
        List<Item> result = itemRepository.findAll();

        // item1,item2 save 했으므로 result.size() 는 2
        assertThat(result.size()).isEqualTo(2);
        // result 에 item1, item2 가 있느냐 ? ㅇㅋ;
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // Item 객체 item1 생성
        Item item1 = new Item("item1", 10000, 10);
        // item1 저장
        Item savedItem = itemRepository.save(item1);

//        Long itemId = savedItem.getId();
//        itemId 를 따로 선언하지 않고 item1.getId() 를 사용함


        Item item2 = new Item("item2", 20000, 20);
        itemRepository.update(item1.getId(), item2);

        Item findItem = itemRepository.findById(item1.getId());
        // 이름 바뀐거 확인.
        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
        // 가격 바뀐거 확인.
        assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
        // 수량 바뀐거 확인.
        assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());

    }
}
