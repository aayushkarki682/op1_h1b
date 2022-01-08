package opt.h1b.OPT_H1B.bootstrap;

import lombok.RequiredArgsConstructor;
import opt.h1b.OPT_H1B.domain.Authority;
import opt.h1b.OPT_H1B.domain.User;
import opt.h1b.OPT_H1B.repository.AuthorityRepository;
import opt.h1b.OPT_H1B.repository.UserRepository;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BootStrap implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Authority admin = authorityRepository.save(Authority.builder().role("Admin").build());
        Authority customer = authorityRepository.save(Authority.builder().role("Customer").build());

        userRepository.save(
                User.builder()
                        .firstName("Aayush")
                        .lastName("Karki")
                        .email("ayushkarki@gmail.com")
                        .userName("ayush682")
                        .password(passwordEncoder.encode("ayush"))
                        .build()
        );
        userRepository.save(
                User.builder()
                        .firstName("Laxman")
                        .lastName("Bista")
                        .email("laxmanbista@gmail.com")
                        .userName("laxman991")
                        .password(passwordEncoder.encode("laxman"))
                        .build()
        );
    }
}
