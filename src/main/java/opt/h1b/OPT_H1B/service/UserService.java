package opt.h1b.OPT_H1B.service;
import opt.h1b.OPT_H1B.domain.UserClass;

import java.util.List;

public interface UserService {

     UserClass save(UserClass user);
     List<UserClass> getAll();
}
