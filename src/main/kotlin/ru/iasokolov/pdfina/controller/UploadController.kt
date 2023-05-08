package ru.iasokolov.pdfina.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class UploadController {
    companion object {
        var logger: Logger = LoggerFactory.getLogger(UploadController::class.java)
    }

    @PostMapping("/upload")
    fun upload(@RequestParam("file") uploadFile: MultipartFile): ResponseEntity<String> {
        logger.info("call upload")

        return ResponseEntity.ok("File saved successfully!")
    }
}