package fr.ebiz.cdb.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by Bertrand Pestre on 04/04/17.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    public static final String LOGIN = "login";

    /**
     * Get login page.
     *
     * @param model model
     * @param error error
     * @return login
     */
    @GetMapping
    public String getLogin(ModelMap model, @RequestParam("error") Optional<String> error) {

        model.addAttribute("error", error.orElse(null));
        return LOGIN;
    }

}
