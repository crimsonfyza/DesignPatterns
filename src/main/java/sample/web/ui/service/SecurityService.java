package sample.web.ui.service;

/**
 *
 * @author  Mark van Dalen
 *
 */

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}

