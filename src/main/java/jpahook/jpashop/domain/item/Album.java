package jpahook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")  // ���� Ŭ������ �����Ѵ�. ��ƼƼ�� ������ �� ����Ÿ���� ���� �÷��� ������ ���� �����Ѵ�. (������̼��� �������� ������ �⺻������ Ŭ���� �̸��� ����.)
@Getter
@Setter
public class Album extends Item {

	private String artist;
	private String etc;
	
}
