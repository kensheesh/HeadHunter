package kg.attractor.headhunter.controller.mvc;

import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/{resumeId}")
    public String getResume(@PathVariable Integer resumeId, Model model) {
        model.addAttribute("resume", resumeService.getResumeById(resumeId));
        return "resume_info";
    }
}
