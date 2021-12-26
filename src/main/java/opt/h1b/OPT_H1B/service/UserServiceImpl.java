package opt.h1b.OPT_H1B.service;

import opt.h1b.OPT_H1B.domain.UserClass;
import opt.h1b.OPT_H1B.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserClass> getAll() {
       return userRepository.findAll().stream().collect(Collectors.toList());
    }


}
