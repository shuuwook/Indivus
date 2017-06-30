package indivus.cosmos.model.server;

/**
 * Created by seowo on 2017-06-25.
 */

public class SignUpData {
    String email;
    String password;
    String username;

    public SignUpData(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
