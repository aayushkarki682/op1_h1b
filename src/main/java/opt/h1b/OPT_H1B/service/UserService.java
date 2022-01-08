package opt.h1b.OPT_H1B.service;
import opt.h1b.OPT_H1B.domain.User;

import java.util.List;

public interface UserService {

     User save(User user);
     List<User> getAll();
     User findByUserName(String userName);
}
