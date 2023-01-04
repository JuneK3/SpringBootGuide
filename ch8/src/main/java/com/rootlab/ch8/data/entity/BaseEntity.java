package com.rootlab.ch8.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/** @MappedSuperclass 정리
 * 상속관계 매핑이 아니다.
 * @MappedSuperclass가 선언되어 있는 클래스는 엔티티가 아니다. 당연히 테이블과 매핑도 안된다.
 * 단순히 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
 * 조회, 검색이 불가하다. -> em.find(BaseEntity) 불가능
 * 직접 생성해서 사용할 일이 없으므로 추상 클래스로 만드는 것을 권장한다.
 * 테이블과 관계가 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역할을 한다.
 * 주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용한다.
 * JPA에서 @Entity 클래스는 @Entity나 @MappedSuperclass로 지정한 클래스만 상속할 수 있다.
 */

/** @EntityListeners(AuditingEntityListener.class)
 *  @EntityListeners: 엔티티를 데이터베이스에 적용하기 전후로 콜백을 요청할 수 있게 하는 어노테이션
 *  AuditingEntityListener: 엔티티의 Auditing 정보를 주입하는 Jpa 엔티티 리스너 클래스
 */

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate // 데이터 생성 날짜를 자동 주입
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate // 데이터 수정 날짜를 자동 주입
	private LocalDateTime updatedAt;
}
