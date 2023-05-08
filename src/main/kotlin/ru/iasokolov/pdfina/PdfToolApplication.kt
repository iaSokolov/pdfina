package ru.iasokolov.pdfina

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PdfToolApplication

fun main(args: Array<String>) {
	runApplication<PdfToolApplication>(*args)
}
