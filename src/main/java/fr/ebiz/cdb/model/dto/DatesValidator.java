package fr.ebiz.cdb.model.dto;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Bertrand Pestre on 22/03/17.
 */
public class DatesValidator implements ConstraintValidator<ValidDates, Object> {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private String introduced;
    private String discontinued;

    @Override
    public void initialize(ValidDates validDates) {
        introduced = validDates.introduced();
        discontinued = validDates.discontinued();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if ( o instanceof ComputerDTO) {
            ComputerDTO computerDTO = (ComputerDTO)o;
            introduced = computerDTO.getIntroduced();
            discontinued = computerDTO.getDiscontinued();
        }

        if (introduced != null && discontinued != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate introducedLD = LocalDate.parse(introduced.trim(), formatter);
            LocalDate discontinuedLD = LocalDate.parse(discontinued.trim(), formatter);

            if(introducedLD.isBefore(discontinuedLD)) {
                return true;
            } else {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("message")
                        .addPropertyNode(discontinued)
                        .addConstraintViolation();

                return false;
            }


        }

        return true;
    }
}
