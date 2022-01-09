package opt.h1b.OPT_H1B.service;

import opt.h1b.OPT_H1B.domain.User;
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
    public User save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAll() {
       return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public User findByUserName(String userName) {
        System.out.println(userName);
        User user =  userRepository.findByUserName(userName).isPresent() ? userRepository.findByUserName(userName).get() : null;

      if(user != null){

          return user;
      } else {

          return null;

      }
    }

    @Override
    public User findById(long id) {
        User user =  userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;

        if(user != null){

            return user;
        } else {

            return null;

        }
    }


}
