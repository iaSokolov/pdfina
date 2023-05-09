package ru.iasokolov.pdfina.model

data class CheckResult(
    val status: StatusCheck,
    val message: String? = null
)
