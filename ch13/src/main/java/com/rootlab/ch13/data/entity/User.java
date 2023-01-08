package com.rootlab.ch13.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
@EqualsAndHashCode(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
public class User extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String uid; // 회원 ID (JWT 토큰 내 정보)

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Json 결과로 출력하지 않을 데이터에 대해 해당 어노테이션 설정 값 추가
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	/**
	 * RDB에는 컬렉션과 같은 형태의 데이터를 컬럼에 저장할 수 없기 때문에
	 * 별도의 테이블을 생성하여 컬렉션을 관리해야한다.
	 * 이때 컬렉션 객체임을 JPA에게 알려주는 어노테이션이 @ElementCollection이다.
	 * JPA는 @Entity가 아닌 Basic Type이나 Embeddable Class로 정의된 컬렉션을 테이블로 생성하며 One-To-Many 관계를 다룬다.
	 */

	/**
	 * @ElementCollection: 연관된 부모 Entity 하나에만 연관되어 관리된다. (부모 Entity와 독립적으로 사용 X)
	 * 항상 부모와 함께 저장되고 삭제되므로 cascade 옵션은 제공하지 않는다. (cascade = ALL 인 셈)
	 * 부모 Entity Id와 추가 Column들로 구성된다.
	 * 기본적으로 식별자 개념이 없으므로 컬렉션 값 변경 시, 전체 삭제 후 새로 추가한다.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	// @Builder.Default: 빌더 패턴을 통해 인스턴스를 만들 때 특정 필드를 특정 값으로 초기화
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	/**
	 * security 에서 사용하는 회원 구분 id
	 *
	 * @return uid
	 */
	@Override
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getUsername() {
		return this.uid;
	}

	/**
	 * 계정이 만료되었는지 체크하는 로직
	 * 이 예제에서는 사용하지 않으므로 true 값 return
	 *
	 * @return true
	 */
	@Override
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 계정이 잠겼는지 체크하는 로직
	 * 이 예제에서는 사용하지 않으므로 true 값 return
	 *
	 * @return true
	 */
	@Override
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 계정의 패스워드가 만료되었는지 체크하는 로직
	 * 이 예제에서는 사용하지 않으므로 true 값 return
	 *
	 * @return true
	 */
	@Override
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 계정이 사용가능한지 체크하는 로직
	 * 이 예제에서는 사용하지 않으므로 true 값 return
	 *
	 * @return true
	 */
	@Override
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isEnabled() {
		return true;
	}

}
