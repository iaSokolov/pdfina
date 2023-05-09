package ru.iasokolov.pdfina.service

import org.springframework.stereotype.Service
import org.verapdf.gf.foundry.VeraGreenfieldFoundryProvider
import org.verapdf.pdfa.Foundries
import org.verapdf.pdfa.flavours.PDFAFlavour
import ru.iasokolov.pdfina.dto.CheckParam
import ru.iasokolov.pdfina.model.CheckResult
import ru.iasokolov.pdfina.model.StatusCheck
import java.io.InputStream


@Service
class CheckService {

    fun check(param: CheckParam, inputStream: InputStream): CheckResult {
        VeraGreenfieldFoundryProvider.initialise();

        val flavour = PDFAFlavour.fromString(param.format)

        try {
            Foundries.defaultInstance().createParser(inputStream, flavour).use { parser ->
                val validator = Foundries.defaultInstance().createValidator(flavour, false)
                val result = validator.validate(parser)
                if (result.isCompliant) {
                    // File is a valid
                    return CheckResult(
                        status = StatusCheck.valid
                    )
                } else {
                    // it isn't
                    return CheckResult(
                        status = StatusCheck.invalid
                    )
                }
            }
        }
        catch (exception: Exception) {
            return CheckResult(
                status = StatusCheck.error,
                message = exception.message
            )
        }
    }
}