package ru.iasokolov.pdfina.uiController

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping(path = ["/", "/home", "/index"])
    fun index(): String {
        return "index"
    }
}