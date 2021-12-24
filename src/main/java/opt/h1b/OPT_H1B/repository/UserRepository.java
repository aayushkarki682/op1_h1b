package opt.h1b.OPT_H1B.repository;

import opt.h1b.OPT_H1B.domain.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass, Long> {

}
