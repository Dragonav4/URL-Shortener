package org.example.s31722tpo10.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.s31722tpo10.DataLayer.Models.LinkDTO;
import org.example.s31722tpo10.Service.LinkService;
import org.example.s31722tpo10.Utils.DeleteRes;
import org.example.s31722tpo10.Utils.UpdateRes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Locale;
import java.util.Optional;
import org.springframework.ui.Model;

@Controller()
@RequestMapping("/links")
public class LinkWebController {
    private final LinkService _linkService;
    private final LocaleResolver localeResolver;


    public LinkWebController(LinkService _linkService, LocaleResolver localeResolver) {
        this._linkService = _linkService;
        this.localeResolver = localeResolver;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("link", new LinkDTO());
        model.addAttribute("language", "pl");
        return "create";
    }

    @PostMapping("/create")
    public String createLink(@Valid @ModelAttribute("link") LinkDTO dto,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(value = "lang",defaultValue = "en") String language,
                             HttpServletRequest request) {
        Locale selectedLocale = Locale.forLanguageTag(language);
        localeResolver.setLocale(request, null, selectedLocale);

        if (bindingResult.hasErrors()) {
            return "create";
        }

        String baseUrl = getBaseUrl(request);
        LinkDTO created = _linkService.create(dto, baseUrl);

        created.setRedirectUrl(baseUrl + "/red/" + created.getId());

        model.addAttribute("link", created);
        model.addAttribute("lang", language);
        return "details";
    }
    @GetMapping("/{id}")
    public String viewLink(@PathVariable("id") String id,
                           @RequestParam(value = "lang", defaultValue = "en") String language,
                           @RequestParam(value = "password", required = false) String password,
                           Model model,
                           HttpServletRequest request) {
        Locale selectedLocale = Locale.forLanguageTag(language);
        localeResolver.setLocale(request, null, selectedLocale);
        String baseurl = getBaseUrl(request);
        Optional<LinkDTO> link = _linkService.get(id, baseurl);
        if (link.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("link", link.get());
        model.addAttribute("lang", language);
        return "details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id,
                               @RequestParam(value = "lang", defaultValue = "en") String language,
                               HttpServletRequest request,
                               Model model) {
        Locale selectedLocale = Locale.forLanguageTag(language);
        localeResolver.setLocale(request, null, selectedLocale);

        Optional<LinkDTO> link = _linkService.get(id, null); //todo check it with null
        if (link.isEmpty()) {
            return "notFound";
        }

        model.addAttribute("link", link.get());
        model.addAttribute("lang", language);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String editLink(@PathVariable String id,
                           @Valid @ModelAttribute("link") LinkDTO dto,
                           BindingResult bindingResult,
                           Model model,
                           Locale locale){
        if(bindingResult.hasErrors()){
            return "edit";
        }
        UpdateRes res = _linkService.update(id,dto,dto.getPassword());
        if(res!=UpdateRes.OK){
            model.addAttribute("updateError", res);
            return "edit";
        }
        System.out.println(locale.getLanguage());
        return "redirect:/links/"+id+"?lang="+locale.getLanguage();
    }

    @PostMapping("/{id}/delete") //todo add delete in edit, add add error and bootstarp
    public String deleteLink(@PathVariable String id,
                             @RequestParam(required = false) String password,
                             RedirectAttributes redirectAttributes){
        DeleteRes res = _linkService.delete(id,password);
        if(res!=DeleteRes.Ok){
            redirectAttributes.addFlashAttribute("deleteError", "Wrong password");
            return "redirect:/links/"+id;
        }
        redirectAttributes.addFlashAttribute("deletedMessage",
                "Link successfully removed");
        return "redirect:/links/create";
    }

    private String getBaseUrl(HttpServletRequest request) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
    }
}

















