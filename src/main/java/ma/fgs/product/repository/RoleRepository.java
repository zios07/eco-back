package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fgs.product.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleCode(String roleCode);
}
