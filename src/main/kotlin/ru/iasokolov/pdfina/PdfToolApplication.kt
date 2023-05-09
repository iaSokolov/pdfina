package ru.iasokolov.pdfina

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.verapdf.gf.foundry.VeraGreenfieldFoundryProvider

@SpringBootApplication
class PdfToolApplication

fun main(args: Array<String>) {
	VeraGreenfieldFoundryProvider.initialise();

	runApplication<PdfToolApplication>(*args)
}
