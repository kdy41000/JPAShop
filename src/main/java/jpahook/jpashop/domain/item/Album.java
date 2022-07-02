package jpahook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")  // 하위 클래스에 선언한다. 엔티티를 저장할 때 슈퍼타입의 구분 컬럼에 저장할 값을 지정한다. (어노테이션을 선언하지 않으면 기본값으로 클래스 이름이 들어간다.)
@Getter
@Setter
public class Album extends Item {

	private String artist;
	private String etc;
	
}
