package ru.iasokolov.pdfina.service

import org.springframework.stereotype.Service
import org.verapdf.pdfa.Foundries
import org.verapdf.pdfa.flavours.PDFAFlavour
import ru.iasokolov.pdfina.dto.CheckParamDto
import ru.iasokolov.pdfina.dto.CheckResultDto
import ru.iasokolov.pdfina.dto.ProfileCheckDto
import ru.iasokolov.pdfina.dto.RuleDto
import ru.iasokolov.pdfina.model.StatusCheck
import java.io.InputStream

@Service
class CheckService {

    fun check(param: CheckParamDto, inputStream: InputStream): CheckResultDto {
        val flavour = PDFAFlavour.fromString(param.format)

        try {
            Foundries.defaultInstance().createParser(inputStream, flavour).use { parser ->
                val validator = Foundries.defaultInstance().createValidator(flavour, false)
                val result = validator.validate(parser)
                if (result.isCompliant) {
                    return CheckResultDto(
                        status = StatusCheck.valid,
                        profile = ProfileCheckDto(
                            name = result.profileDetails.name,
                            description = result.profileDetails.description
                        )
                    )
                } else {
                    return CheckResultDto(
                        status = StatusCheck.invalid,
                        profile = ProfileCheckDto(
                            name = result.profileDetails.name,
                            description = result.profileDetails.description
                        ),
                        failedRules = result.failedChecks.map { failedRule ->
                            RuleDto(
                                specification = failedRule.key.specification.id,
                                clause = failedRule.key.clause,
                                description = validator.profile.rules.find {
                                        rule -> rule.ruleId.specification.id == failedRule.key.specification.id &&
                                        rule.ruleId.clause == failedRule.key.clause }
                                    ?.description
                                    ?: ""
                            )
                        }
                    )
                }
            }
        } catch (exception: Exception) {
            return CheckResultDto(
                status = StatusCheck.error,
                message = exception.message
            )
        }
    }
}