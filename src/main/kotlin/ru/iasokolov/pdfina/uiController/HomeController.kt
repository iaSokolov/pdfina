package ru.iasokolov.pdfina.uiController

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ru.iasokolov.pdfina.dto.CheckParam
import ru.iasokolov.pdfina.service.CheckService

@Controller
class HomeController(
    private val checkService: CheckService
) {
    companion object {
        var logger: Logger = LoggerFactory.getLogger(HomeController::class.java)
    }

    @GetMapping(path = ["/", "/home", "/index"])
    fun index(model: Model): String {
        val formatList = listOf<String>("1a", "2a", "1b", "2b", "ux")
        val checkParam = CheckParam(
            format = "1a"
        )

        model.addAttribute("checkParam", checkParam);
        model.addAttribute("formatList", formatList);
        return "index"
    }

    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile,
        @ModelAttribute param: CheckParam,
        attributes: RedirectAttributes
    ): String? {

        val result = checkService.check(param, file.inputStream)

        attributes.addFlashAttribute("status", result.status)
        attributes.addFlashAttribute("message", result.message)

        return "redirect:/"
    }
}