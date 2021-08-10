package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Services.QuestionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/questionform")
public class QuestionFormController {

    @Autowired
    QuestionFormService questionFormService;


    @GetMapping("/list")
    public String printAll(Model model) {
        List<QuestionForm> questionForms = questionFormService.getForms();
        model.addAttribute("questionforms", questionForms);
        return "QuestionForm/questionlist";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("questionform", new QuestionForm());
        return "QuestionForm/questionform";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute("questionform") QuestionForm questionForm, BindingResult result) {
        if (result.hasErrors()) {
            return "QuestionForm/questionform";
        }
        questionFormService.createQuestionForm(questionForm);
        return "redirect:/instalation/list";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("questionform", questionFormService.getQuestionForm(id));
        return "QuestionForm/updatequestionform";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("questionform") QuestionForm questionForm, BindingResult result) {
        if (result.hasErrors()) {
            return "questionform/update";
        }
        questionFormService.updateQuestionForm(questionForm);
        return "redirect:/questionform/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        questionFormService.deleteQuestionForm(id);
        return "redirect:/questionform/list";
    }

    @GetMapping("/view/{id}")
    public String getQuestionForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("questionform", questionFormService.getQuestionForm(id));
        return "QuestionForm/viewquestionform";
    }

}
