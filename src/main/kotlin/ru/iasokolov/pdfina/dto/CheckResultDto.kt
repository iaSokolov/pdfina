package ru.iasokolov.pdfina.dto

import ru.iasokolov.pdfina.model.StatusCheck

data class CheckResultDto(
    val status: StatusCheck,
    val message: String? = null,
    val profile: ProfileCheckDto? = null,
    val failedRules: List<RuleDto>? = null
)
