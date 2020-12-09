package SocialMedia;

import SocialMedia.dao.ProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/social-media")
public class SocialMediaController {

    @GetMapping("/sign")
    public String signUp(Model model) {
        model.addAttribute("profile", new Profile());
        return "sign_up";
    }

    @PostMapping
    public String create(@ModelAttribute("profile") @Valid Profile profile,
                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "sign_up";
        profileDAO.save(profile);
        return "redirect:/social-media";
    }

    private final ProfileDAO profileDAO;

    @Autowired
    public SocialMediaController(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @GetMapping()
    public String mainPage() {

        return "general_page";
    }

    @GetMapping("/all-users")
    public String index(Model model) {
        model.addAttribute("allProfiles", profileDAO.index());
        return "all_users";
    }

    @GetMapping("/id{id}")
    public String userPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("profile", profileDAO.show(id));
        return "profile";
    }

    @GetMapping("/id{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("profile", profileDAO.show(id));
        return "edit";
    }

    @PatchMapping("/id{id}")
    public String update(@ModelAttribute("profile") @Valid Profile profile, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "edit";

        profileDAO.update(id, profile);
        return "redirect:/social-media";
    }

    @DeleteMapping("/id{id}")
    public String delete(@PathVariable("id") int id) {
        profileDAO.delete(id);
        return "redirect:/social-media";
    }
}

