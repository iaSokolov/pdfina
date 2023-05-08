package ru.iasokolov.pdfina.service

import org.springframework.stereotype.Service
import ru.iasokolov.pdfina.model.CheckResult
import ru.iasokolov.pdfina.model.StatusCheck

@Service
class CheckService {

    fun check(): CheckResult {
        return CheckResult(
            status = StatusCheck.none
        )
    }
}