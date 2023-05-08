package ru.iasokolov.pdfina.uiController

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class HomeController {
    companion object {
        var logger: Logger = LoggerFactory.getLogger(HomeController::class.java)
    }

    @GetMapping(path = ["/", "/home", "/index"])
    fun index(model: Model): String {
        //model.addAttribute("message", "You successfully uploaded");
        return "index"
    }

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile, attributes: RedirectAttributes): String? {

        Thread.sleep(3 * 1000);

        // check if file is empty
        if (file.isEmpty) {
            attributes.addFlashAttribute("message", "Please select a file to upload.")
            return "redirect:/"
        }

        // normalize the file path
        val fileName: String = StringUtils.cleanPath(file.originalFilename!!)

        // save the file on the local file system
//        try {
//            val path: Path = Paths.get(UPLOAD_DIR + fileName)
//            Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded $fileName!")
        return "redirect:/"
    }
}