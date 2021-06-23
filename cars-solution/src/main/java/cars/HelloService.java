package cars;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private static final String WELCOME = "Üdvözlünk az oldalon!";

    public String sayHello() {
        return WELCOME;
    }
}
