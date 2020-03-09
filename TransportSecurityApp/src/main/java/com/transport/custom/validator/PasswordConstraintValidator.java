package com.transport.custom.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.DictionaryRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.transport.custom.annotations.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	public static final Logger LOG = LoggerFactory.getLogger(PasswordConstraintValidator.class);
	private DictionaryRule dictionaryRule;

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		/*
		 * try { String invalidPasswordList =
		 * this.getClass().getResource("/invalid-password-list.txt").getFile();
		 * dictionaryRule = new DictionaryRule( new
		 * WordListDictionary(WordLists.createFromReader( // Reader around the
		 * word list file new FileReader[] { new FileReader(invalidPasswordList)
		 * }, // True for case sensitivity, false otherwise false, //
		 * Dictionaries must be sorted new ArraysSort() ))); } catch
		 * (IOException e) { throw new
		 * RuntimeException("could not load word list", e); }
		 */
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		boolean isFound = password.indexOf("PBKDF2WithHmacSHA256:") != -1 ? true : false; // true
		if (isFound) {
			return true;
		} else {
			PasswordValidator validator = new PasswordValidator(Arrays.asList(
					// at least 8 characters
					// new LengthRule(8),
					new LengthRule(8, 30),
					// at least one upper-case character
					new CharacterRule(EnglishCharacterData.UpperCase, 1),
					// at least one lower-case character
					new CharacterRule(EnglishCharacterData.LowerCase, 1),
					// at least one digit character
					new CharacterRule(EnglishCharacterData.Digit, 1),
					// at least one symbol (special character)
					new CharacterRule(EnglishCharacterData.Special, 1),
					// no whitespace
					new WhitespaceRule()
			// no common passwords
			// dictionaryRule
			));
			RuleResult result = validator.validate(new PasswordData(password));
			if (result.isValid()) {
				return true;
			}
			List<String> messages = validator.getMessages(result);
			String messageTemplate = messages.stream().collect(Collectors.joining(","));
			context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
					.disableDefaultConstraintViolation();
			return false;
		}
	}
}