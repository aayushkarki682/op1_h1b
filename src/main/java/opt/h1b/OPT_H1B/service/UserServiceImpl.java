package opt.h1b.OPT_H1B.service;

import opt.h1b.OPT_H1B.domain.UserClass;
import opt.h1b.OPT_H1B.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserClass save(UserClass user) {
        UserClass savedUser = userRepository.save(user);
        return savedUser;
    }
}
