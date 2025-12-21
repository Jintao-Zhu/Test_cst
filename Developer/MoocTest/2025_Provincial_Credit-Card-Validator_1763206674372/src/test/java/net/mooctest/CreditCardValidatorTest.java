package net.mooctest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * 开发者做题前，请仔细阅读以下说明：
 * 
 * 1、该测试类为测试类示例，不要求完全按照该示例类的格式；
 *	    考生可自行创建测试类，类名可自行定义，但需遵循JUnit命名规范，格式为xxxTest.java，提交类似test.java的文件名将因不符合语法而判0分！
 * 
 * 2、所有测试方法放在该顶层类中，不建议再创建内部类。若必需创建内部类，则需检查JUnit对于内部测试类的要求，并添加相关注释，否则将因无法执行而判0分！
 * 
 * 3、本比赛使用jdk1.8+JUnit4，未使用以上版本编写测试用例者，不再接受低分申诉；
 * 
 * 4、不要修改被测代码；
 * 
 * 5、建议尽量避免卡点提交答案，尤其是两份报告的zip包。
 * 
 * */

public class CreditCardValidatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// Date utilities and parsing
		SimpleDateFormat yearFormat = (SimpleDateFormat) DateFormatter.yearFormat();
		SimpleDateFormat monthFormat = (SimpleDateFormat) DateFormatter.monthFormat();
		assertEquals("yy", yearFormat.toPattern());
		assertEquals("MM", monthFormat.toPattern());

		StringBuilder dateWithSlash = new StringBuilder("12/34");
		assertEquals("1234", DateParser.parseDate(dateWithSlash).toString());
		StringBuilder dateWithoutSlash = new StringBuilder("5678");
		assertEquals("5678", DateParser.parseDate(dateWithoutSlash).toString());
		assertEquals(12, DateParser.parseDate(new StringBuilder("1234"), 0, 2));
		assertEquals(34, DateParser.parseDate(new StringBuilder("1234"), 2, 4));

		StringBuilder converted = DateChecker.convertDate("11/25");
		assertEquals("11/25", converted.toString());
		assertTrue(DateChecker.compareDates(DateChecker.CURRENT_YEAR + 1, DateChecker.CURRENT_YEAR));
		assertFalse(DateChecker.compareDates(DateChecker.CURRENT_YEAR, DateChecker.CURRENT_YEAR));
		assertTrue(DateChecker.compareDates(DateChecker.CURRENT_MONTH + 1, DateChecker.CURRENT_MONTH));
		assertFalse(DateChecker.compareDates(DateChecker.CURRENT_MONTH, DateChecker.CURRENT_MONTH));
		assertTrue(DateChecker.getCurrentYear() >= 0);
		assertTrue(DateChecker.getCurrentYear() < 100);
		assertTrue(DateChecker.getCurrentMonth() >= 1);
		assertTrue(DateChecker.getCurrentMonth() <= 12);
		Calendar now = Calendar.getInstance();
		int actualCurrentYear = Integer.parseInt(yearFormat.format(now.getTime()));
		int actualCurrentMonth = Integer.parseInt(monthFormat.format(now.getTime()));
		assertEquals(actualCurrentYear, DateChecker.getCurrentYear());
		assertEquals(actualCurrentMonth, DateChecker.getCurrentMonth());

		// Credit card parsing helpers
		List<Integer> parsedDigits = CreditCardParser.parseNumber("1230");
		assertEquals(Arrays.asList(1, 2, 3, 0), parsedDigits);
		assertEquals(12, CreditCardParser.parseIIN(parsedDigits, 2));
		assertEquals(123, CreditCardParser.parseIIN(parsedDigits, 3));

		// Luhn validator behaviour
		LuhnValidator luhnValidator = new LuhnValidator();
		assertEquals(9, luhnValidator.sumOfDigits(45));
		assertTrue(luhnValidator.algorithmCheck("79927398713"));
		assertFalse(luhnValidator.algorithmCheck("79927398714"));
		assertTrue(luhnValidator.algorithmCheck("4111111111111111"));
		assertFalse(luhnValidator.algorithmCheck("50"));
		char romanTenThousand = '\u2188';
		int romanValue = Character.getNumericValue(romanTenThousand);
		assertTrue(romanValue > 9);
		String unicodeOnlyNumber = repeatChar(romanTenThousand, 120);
		assertTrue(luhnValidator.algorithmCheck(unicodeOnlyNumber));
		String unicodeMixedNumber = romanTenThousand + "79927398713";
		assertFalse(luhnValidator.algorithmCheck(unicodeMixedNumber));

		// Validator covering Luhn, CVV and expiration checks
		String validVisa = "4111111111111111";
		int futureYear = (DateChecker.CURRENT_YEAR + 1) % 100;
		int futureMonthSameYear = DateChecker.CURRENT_MONTH + 1;
		String yearAheadExpiration = String.format("%02d/%02d", DateChecker.CURRENT_MONTH, futureYear);
		String monthAheadExpiration = String.format("%02d/%02d", futureMonthSameYear, DateChecker.CURRENT_YEAR);
		int previousMonth = Math.max(0, DateChecker.CURRENT_MONTH - 1);
		String expiredExpiration = String.format("%02d/%02d", previousMonth, DateChecker.CURRENT_YEAR);

		Validator validatorSuccess = new Validator(validVisa, yearAheadExpiration, "123");
		assertTrue(validatorSuccess.validate());
		assertTrue(validatorSuccess.checkCVV());
		validatorSuccess.setExpirationMonth();
		validatorSuccess.setExpirationYear();
		assertTrue(validatorSuccess.checkExpirationDate());

		Validator validatorMonthAhead = new Validator(validVisa, monthAheadExpiration, "1234");
		assertTrue(validatorMonthAhead.checkCVV());
		assertTrue(validatorMonthAhead.checkExpirationDate());

		Validator validatorExpired = new Validator(validVisa, expiredExpiration, "123");
		assertFalse(validatorExpired.checkExpirationDate());

		Validator validatorBadCvv = new Validator(validVisa, yearAheadExpiration, "12");
		assertFalse(validatorBadCvv.checkCVV());

		Validator validatorFailure = new Validator("4111111111111112");
		try {
			validatorFailure.validate();
			fail("Expected InvalidCardException for invalid Luhn number");
		} catch (InvalidCardException e) {
			assertEquals("This card isn't invalid", e.getMessage());
		}

		// Type checker for supported brands
		CreditCardType visaType = TypeChecker.checkType(validVisa);
		assertNotNull(visaType);
		assertEquals(CreditCardType.VISA, visaType);
		assertNotEquals(CreditCardType.OTHER, visaType);
		assertEquals(CreditCardType.AMERICAN_EXPRESS, TypeChecker.checkType("340000000000000"));
		assertEquals(CreditCardType.MASTERCARD, TypeChecker.checkType("5100000000000000"));
		assertEquals(CreditCardType.DISCOVER, TypeChecker.checkType("6011000000000000"));
		assertEquals(CreditCardType.OTHER, TypeChecker.checkType("1111111111111111"));

		// Validators covering length and IIN branches
		VisaValidator visaValid = new VisaValidator(validVisa);
		assertTrue(visaValid.checkLength());
		assertTrue(visaValid.checkIINRanges());
		assertTrue(visaValid.validate());

		VisaValidator visaLowerBound = new VisaValidator("4000000000000");
		assertTrue(visaLowerBound.checkLength());
		assertTrue(visaLowerBound.checkIINRanges());
		assertTrue(visaLowerBound.validate());

		VisaValidator visaUpperBound = new VisaValidator("4000000000000000000");
		assertTrue(visaUpperBound.checkLength());
		assertTrue(visaUpperBound.checkIINRanges());
		assertTrue(visaUpperBound.validate());

		VisaValidator visaShort = new VisaValidator("411111111111");
		assertFalse(visaShort.checkLength());
		assertTrue(visaShort.checkIINRanges());
		assertFalse(visaShort.validate());

		VisaValidator visaWrongIin = new VisaValidator("5111111111111111");
		assertTrue(visaWrongIin.checkLength());
		assertFalse(visaWrongIin.checkIINRanges());
		assertFalse(visaWrongIin.validate());

		VisaValidator visaTooLong = new VisaValidator("41111111111111111111");
		assertFalse(visaTooLong.checkLength());
		assertTrue(visaTooLong.checkIINRanges());
		assertFalse(visaTooLong.validate());

		VisaElectronValidator visaElectronValid = new VisaElectronValidator("4026000000000000");
		assertTrue(visaElectronValid.checkLength());
		assertTrue(visaElectronValid.checkIINRanges());
		assertTrue(visaElectronValid.validate());

		VisaElectronValidator visaElectronShort = new VisaElectronValidator("402600000000000");
		assertFalse(visaElectronShort.checkLength());
		assertTrue(visaElectronShort.checkIINRanges());
		assertFalse(visaElectronShort.validate());

		VisaElectronValidator visaElectronWrongIin = new VisaElectronValidator("4099000000000000");
		assertTrue(visaElectronWrongIin.checkLength());
		assertFalse(visaElectronWrongIin.checkIINRanges());
		assertFalse(visaElectronWrongIin.validate());

		VisaElectronValidator visaElectronTooLong = new VisaElectronValidator("40260000000000000000");
		assertFalse(visaElectronTooLong.checkLength());
		assertTrue(visaElectronTooLong.checkIINRanges());
		assertFalse(visaElectronTooLong.validate());

		VisaElectronValidator visaElectron417500 = new VisaElectronValidator("4175000000000000");
		assertTrue(visaElectron417500.checkLength());
		assertFalse(visaElectron417500.checkIINRanges());
		assertFalse(visaElectron417500.validate());

		VisaElectronValidator visaElectronFiveDigit = new VisaElectronValidator("4175000000000000");
		try {
			Field listField = VisaElectronValidator.class.getDeclaredField("creditCardNumberList");
			listField.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<Integer> adjustedDigits = (List<Integer>) listField.get(visaElectronFiveDigit);
			adjustedDigits.set(0, 4);
			adjustedDigits.set(1, 17);
			adjustedDigits.set(2, 5);
			adjustedDigits.set(3, 0);
			adjustedDigits.set(4, 0);
			assertTrue(visaElectronFiveDigit.checkIINRanges());
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}

		AmericanExpressValidator americanExpressValid = new AmericanExpressValidator("340000000000000");
		assertTrue(americanExpressValid.checkLength());
		assertTrue(americanExpressValid.checkIINRanges());
		assertTrue(americanExpressValid.validate());

		AmericanExpressValidator americanExpressUpper = new AmericanExpressValidator("370000000000000");
		assertTrue(americanExpressUpper.checkIINRanges());

		AmericanExpressValidator americanExpressShort = new AmericanExpressValidator("34000000000000");
		assertFalse(americanExpressShort.checkLength());
		assertTrue(americanExpressShort.checkIINRanges());
		assertFalse(americanExpressShort.validate());

		AmericanExpressValidator americanExpressWrongIin = new AmericanExpressValidator("330000000000000");
		assertTrue(americanExpressWrongIin.checkLength());
		assertFalse(americanExpressWrongIin.checkIINRanges());
		assertFalse(americanExpressWrongIin.validate());

		MasterCardValidator masterCardRangeOne = new MasterCardValidator("5100000000000000");
		assertTrue(masterCardRangeOne.checkLength());
		assertTrue(masterCardRangeOne.checkIINRanges());
		assertTrue(masterCardRangeOne.validate());

		MasterCardValidator masterCardRangeOneUpper = new MasterCardValidator("5500000000000000");
		assertTrue(masterCardRangeOneUpper.checkIINRanges());

		MasterCardValidator masterCardRangeTwo = new MasterCardValidator("2221000000000000");
		assertTrue(masterCardRangeTwo.checkLength());
		assertTrue(masterCardRangeTwo.checkIINRanges());
		assertTrue(masterCardRangeTwo.validate());

		MasterCardValidator masterCardRangeTwoUpper = new MasterCardValidator("2720990000000000");
		assertTrue(masterCardRangeTwoUpper.checkIINRanges());

		MasterCardValidator masterCardShort = new MasterCardValidator("510000000000000");
		assertFalse(masterCardShort.checkLength());
		assertTrue(masterCardShort.checkIINRanges());
		assertFalse(masterCardShort.validate());

		MasterCardValidator masterCardWrongIin = new MasterCardValidator("5810000000000000");
		assertTrue(masterCardWrongIin.checkLength());
		assertFalse(masterCardWrongIin.checkIINRanges());
		assertFalse(masterCardWrongIin.validate());

		DiscoverValidator discoverIin6011 = new DiscoverValidator("6011000000000000");
		assertTrue(discoverIin6011.checkLength());
		assertTrue(discoverIin6011.checkIINRanges());
		assertTrue(discoverIin6011.validate());

		DiscoverValidator discoverIin622 = new DiscoverValidator("6221260000000000");
		assertTrue(discoverIin622.checkIINRanges());
		DiscoverValidator discoverIin644 = new DiscoverValidator("6440000000000000");
		assertTrue(discoverIin644.checkIINRanges());
		DiscoverValidator discoverIin65 = new DiscoverValidator("6500000000000000");
		assertTrue(discoverIin65.checkIINRanges());

		DiscoverValidator discoverIin622High = new DiscoverValidator("6229250000000000");
		assertTrue(discoverIin622High.checkIINRanges());

		DiscoverValidator discoverIin649 = new DiscoverValidator("6490000000000000");
		assertTrue(discoverIin649.checkIINRanges());

		DiscoverValidator discoverShort = new DiscoverValidator("601100000000000");
		assertFalse(discoverShort.checkLength());
		assertTrue(discoverShort.checkIINRanges());
		assertFalse(discoverShort.validate());

		DiscoverValidator discoverWrongIin = new DiscoverValidator("6010000000000000");
		assertTrue(discoverWrongIin.checkLength());
		assertFalse(discoverWrongIin.checkIINRanges());
		assertFalse(discoverWrongIin.validate());

		ChinaUnionPayValidator cupValid = new ChinaUnionPayValidator("6200000000000000");
		assertTrue(cupValid.checkLength());
		assertTrue(cupValid.checkIINRanges());
		assertTrue(cupValid.validate());

		ChinaUnionPayValidator cupLong = new ChinaUnionPayValidator("6200000000000000000");
		assertTrue(cupLong.checkLength());
		assertTrue(cupLong.checkIINRanges());
		assertTrue(cupLong.validate());

		ChinaUnionPayValidator cupTooLong = new ChinaUnionPayValidator("62000000000000000000");
		assertFalse(cupTooLong.checkLength());
		assertTrue(cupTooLong.checkIINRanges());
		assertFalse(cupTooLong.validate());

		ChinaUnionPayValidator cupShort = new ChinaUnionPayValidator("620000000000000");
		assertFalse(cupShort.checkLength());
		assertTrue(cupShort.checkIINRanges());
		assertFalse(cupShort.validate());

		ChinaUnionPayValidator cupWrongIin = new ChinaUnionPayValidator("6300000000000000");
		assertTrue(cupWrongIin.checkLength());
		assertFalse(cupWrongIin.checkIINRanges());
		assertFalse(cupWrongIin.validate());

		ChinaTUnionValidator ctuValid = new ChinaTUnionValidator("3100000000000000000");
		assertTrue(ctuValid.checkLength());
		assertTrue(ctuValid.checkIINRanges());
		assertTrue(ctuValid.validate());

		ChinaTUnionValidator ctuShort = new ChinaTUnionValidator("310000000000000000");
		assertFalse(ctuShort.checkLength());
		assertTrue(ctuShort.checkIINRanges());
		assertFalse(ctuShort.validate());

		ChinaTUnionValidator ctuWrongIin = new ChinaTUnionValidator("3200000000000000000");
		assertTrue(ctuWrongIin.checkLength());
		assertFalse(ctuWrongIin.checkIINRanges());
		assertFalse(ctuWrongIin.validate());

		DankortValidator dankortValid = new DankortValidator("4571000000000000");
		assertTrue(dankortValid.checkLength());
		assertTrue(dankortValid.checkIINRanges());
		assertTrue(dankortValid.validate());

		DankortValidator dankortShort = new DankortValidator("457100000000000");
		assertFalse(dankortShort.checkLength());
		assertTrue(dankortShort.checkIINRanges());
		assertFalse(dankortShort.validate());

		DankortValidator dankortWrongIin = new DankortValidator("4520000000000000");
		assertTrue(dankortWrongIin.checkLength());
		assertFalse(dankortWrongIin.checkIINRanges());
		assertFalse(dankortWrongIin.validate());

		DankortValidator dankortUpper = new DankortValidator("5019000000000000");
		assertTrue(dankortUpper.checkIINRanges());

		String dinersUpperPrefix = "55000000000000";
		String dinersUpperCard = generateLuhnNumber(dinersUpperPrefix, 16);
		assertNotNull(dinersUpperCard);
		DinersClubValidator dinersValid = new DinersClubValidator(dinersUpperCard);
		assertTrue(dinersValid.checkLength());
		assertTrue(dinersValid.checkIINRanges());
		assertTrue(dinersValid.validate());

		DinersClubValidator dinersShort = new DinersClubValidator("540000000000000");
		assertFalse(dinersShort.checkLength());
		assertTrue(dinersShort.checkIINRanges());
		assertFalse(dinersShort.validate());

		DinersClubValidator dinersWrongIin = new DinersClubValidator("5300000000000000");
		assertTrue(dinersWrongIin.checkLength());
		assertFalse(dinersWrongIin.checkIINRanges());
		assertFalse(dinersWrongIin.validate());

		DinersClubInternationalValidator dciThreeHundred = new DinersClubInternationalValidator("3000000000000000");
		assertTrue(dciThreeHundred.checkIINRanges());
		DinersClubInternationalValidator dciSpecial = new DinersClubInternationalValidator("3095000000000000");
		assertTrue(dciSpecial.checkIINRanges());
		DinersClubInternationalValidator dciUpper = new DinersClubInternationalValidator("3800000000000000");
		assertTrue(dciUpper.checkIINRanges());
		assertTrue(dciUpper.checkLength());
		assertTrue(dciUpper.validate());

		DinersClubInternationalValidator dciThreeOhFive = new DinersClubInternationalValidator("3050000000000000");
		assertTrue(dciThreeOhFive.checkIINRanges());

		DinersClubInternationalValidator dciThirtyNine = new DinersClubInternationalValidator("3900000000000000");
		assertTrue(dciThirtyNine.checkIINRanges());

		DinersClubInternationalValidator dciMaxLength = new DinersClubInternationalValidator("3800000000000000000");
		assertTrue(dciMaxLength.checkLength());
		assertTrue(dciMaxLength.checkIINRanges());
		assertTrue(dciMaxLength.validate());

		DinersClubInternationalValidator dciTooLong = new DinersClubInternationalValidator("38000000000000000000");
		assertFalse(dciTooLong.checkLength());
		assertTrue(dciTooLong.checkIINRanges());
		assertFalse(dciTooLong.validate());

		DinersClubInternationalValidator dciShort = new DinersClubInternationalValidator("300000000000000");
		assertFalse(dciShort.checkLength());
		assertTrue(dciShort.checkIINRanges());
		assertFalse(dciShort.validate());

		DinersClubInternationalValidator dciWrongIin = new DinersClubInternationalValidator("3600000000000000");
		assertTrue(dciWrongIin.checkLength());
		assertFalse(dciWrongIin.checkIINRanges());
		assertFalse(dciWrongIin.validate());

		InstaPaymenttValidator instaValid = new InstaPaymenttValidator("6370000000000000");
		assertTrue(instaValid.checkLength());
		assertTrue(instaValid.checkIINRanges());
		assertTrue(instaValid.validate());

		InstaPaymenttValidator instaUpper = new InstaPaymenttValidator("6390000000000000");
		assertTrue(instaUpper.checkIINRanges());

		InstaPaymenttValidator instaShort = new InstaPaymenttValidator("637000000000000");
		assertFalse(instaShort.checkLength());
		assertTrue(instaShort.checkIINRanges());
		assertFalse(instaShort.validate());

		InstaPaymenttValidator instaWrongIin = new InstaPaymenttValidator("6350000000000000");
		assertTrue(instaWrongIin.checkLength());
		assertFalse(instaWrongIin.checkIINRanges());
		assertFalse(instaWrongIin.validate());

		InterPaymentValidator interValid = new InterPaymentValidator("6360000000000000");
		assertTrue(interValid.checkLength());
		assertTrue(interValid.checkIINRanges());
		assertTrue(interValid.validate());

		InterPaymentValidator interMaxLength = new InterPaymentValidator("6360000000000000000");
		assertTrue(interMaxLength.checkLength());
		assertTrue(interMaxLength.checkIINRanges());

		InterPaymentValidator interTooLong = new InterPaymentValidator("63600000000000000000");
		assertFalse(interTooLong.checkLength());
		assertTrue(interTooLong.checkIINRanges());
		assertFalse(interTooLong.validate());

		InterPaymentValidator interShort = new InterPaymentValidator("636000000000000");
		assertFalse(interShort.checkLength());
		assertTrue(interShort.checkIINRanges());
		assertFalse(interShort.validate());

		InterPaymentValidator interWrongIin = new InterPaymentValidator("6370000000000000");
		assertTrue(interWrongIin.checkLength());
		assertFalse(interWrongIin.checkIINRanges());
		assertFalse(interWrongIin.validate());

		JCBValidator jcbValid = new JCBValidator("3528000000000000");
		assertTrue(jcbValid.checkLength());
		assertTrue(jcbValid.checkIINRanges());
		assertTrue(jcbValid.validate());

		JCBValidator jcbUpper = new JCBValidator("3589000000000000");
		assertTrue(jcbUpper.checkIINRanges());

		JCBValidator jcbShort = new JCBValidator("352800000000000");
		assertFalse(jcbShort.checkLength());
		assertTrue(jcbShort.checkIINRanges());
		assertFalse(jcbShort.validate());

		JCBValidator jcbWrongIin = new JCBValidator("3527000000000000");
		assertTrue(jcbWrongIin.checkLength());
		assertFalse(jcbWrongIin.checkIINRanges());
		assertFalse(jcbWrongIin.validate());

		LankaPayValidator lankaValid = new LankaPayValidator("3571110000000000");
		assertTrue(lankaValid.checkLength());
		assertTrue(lankaValid.checkIINRanges());
		assertTrue(lankaValid.validate());

		LankaPayValidator lankaShort = new LankaPayValidator("357111000000000");
		assertFalse(lankaShort.checkLength());
		assertTrue(lankaShort.checkIINRanges());
		assertFalse(lankaShort.validate());

		LankaPayValidator lankaWrongIin = new LankaPayValidator("3571100000000000");
		assertTrue(lankaWrongIin.checkLength());
		assertFalse(lankaWrongIin.checkIINRanges());
		assertFalse(lankaWrongIin.validate());

		MaestroValidator maestroFirst = new MaestroValidator("5000000000000000");
		assertTrue(maestroFirst.checkLength());
		assertTrue(maestroFirst.checkIINRanges());
		assertTrue(maestroFirst.validate());

		MaestroValidator maestroFirstUpper = new MaestroValidator("5099990000000000");
		assertTrue(maestroFirstUpper.checkIINRanges());

		MaestroValidator maestroSecond = new MaestroValidator("5600000000000000");
		assertTrue(maestroSecond.checkIINRanges());

		MaestroValidator maestroSecondUpper = new MaestroValidator("5899990000000000");
		assertTrue(maestroSecondUpper.checkIINRanges());

		MaestroValidator maestroThird = new MaestroValidator("6000000000000000");
		assertTrue(maestroThird.checkIINRanges());

		MaestroValidator maestroThirdUpper = new MaestroValidator("6999990000000000");
		assertTrue(maestroThirdUpper.checkIINRanges());

		MaestroValidator maestroMinLength = new MaestroValidator("500000000000");
		assertTrue(maestroMinLength.checkLength());
		assertTrue(maestroMinLength.checkIINRanges());
		assertTrue(maestroMinLength.validate());

		MaestroValidator maestroMaxLength = new MaestroValidator("6999990000000000000");
		assertTrue(maestroMaxLength.checkLength());
		assertTrue(maestroMaxLength.checkIINRanges());
		assertTrue(maestroMaxLength.validate());

		MaestroValidator maestroShort = new MaestroValidator("50000000000");
		assertFalse(maestroShort.checkLength());
		assertTrue(maestroShort.checkIINRanges());
		assertFalse(maestroShort.validate());

		MaestroValidator maestroTooLong = new MaestroValidator("500000000000000000000");
		assertFalse(maestroTooLong.checkLength());
		assertTrue(maestroTooLong.checkIINRanges());
		assertFalse(maestroTooLong.validate());

		MaestroValidator maestroWrongIin = new MaestroValidator("4000000000000000");
		assertTrue(maestroWrongIin.checkLength());
		assertFalse(maestroWrongIin.checkIINRanges());
		assertFalse(maestroWrongIin.validate());

		MaestroUKValidator maestroUkFour = new MaestroUKValidator("6759000000000000");
		assertTrue(maestroUkFour.checkLength());
		assertTrue(maestroUkFour.checkIINRanges());
		assertTrue(maestroUkFour.validate());

		MaestroUKValidator maestroUkMinLength = new MaestroUKValidator("675900000000");
		assertTrue(maestroUkMinLength.checkLength());
		assertTrue(maestroUkMinLength.checkIINRanges());

		MaestroUKValidator maestroUkMaxLength = new MaestroUKValidator("6767700000000000000");
		assertTrue(maestroUkMaxLength.checkLength());
		assertTrue(maestroUkMaxLength.checkIINRanges());

		MaestroUKValidator maestroUkSixLow = new MaestroUKValidator("6767700000000000");
		assertTrue(maestroUkSixLow.checkIINRanges());

		MaestroUKValidator maestroUkSixHigh = new MaestroUKValidator("6767740000000000");
		assertTrue(maestroUkSixHigh.checkIINRanges());

		MaestroUKValidator maestroUkShort = new MaestroUKValidator("67590000000");
		assertFalse(maestroUkShort.checkLength());
		assertTrue(maestroUkShort.checkIINRanges());
		assertFalse(maestroUkShort.validate());

		MaestroUKValidator maestroUkWrong = new MaestroUKValidator("6767750000000000");
		assertTrue(maestroUkWrong.checkLength());
		assertFalse(maestroUkWrong.checkIINRanges());
		assertFalse(maestroUkWrong.validate());

		MaestroUKValidator maestroUkTooLong = new MaestroUKValidator("675900000000000000000");
		assertFalse(maestroUkTooLong.checkLength());
		assertTrue(maestroUkTooLong.checkIINRanges());
		assertFalse(maestroUkTooLong.validate());

		MIRValidator mirValid = new MIRValidator("2200000000000000");
		assertTrue(mirValid.checkLength());
		assertTrue(mirValid.checkIINRanges());
		assertTrue(mirValid.validate());

		MIRValidator mirUpper = new MIRValidator("2204000000000000");
		assertTrue(mirUpper.checkIINRanges());

		MIRValidator mirShort = new MIRValidator("220000000000000");
		assertFalse(mirShort.checkLength());
		assertTrue(mirShort.checkIINRanges());
		assertFalse(mirShort.validate());

		MIRValidator mirWrongIin = new MIRValidator("2205000000000000");
		assertTrue(mirWrongIin.checkLength());
		assertFalse(mirWrongIin.checkIINRanges());
		assertFalse(mirWrongIin.validate());

		NPS_PridnestrovieValidator npsLow = new NPS_PridnestrovieValidator("6054740000000000");
		assertTrue(npsLow.checkLength());
		assertTrue(npsLow.checkIINRanges());
		assertTrue(npsLow.validate());

		NPS_PridnestrovieValidator npsHigh = new NPS_PridnestrovieValidator("6054744000000000");
		assertTrue(npsHigh.checkIINRanges());

		NPS_PridnestrovieValidator npsShort = new NPS_PridnestrovieValidator("605474000000000");
		assertFalse(npsShort.checkLength());
		assertTrue(npsShort.checkIINRanges());
		assertFalse(npsShort.validate());

		NPS_PridnestrovieValidator npsWrongIin = new NPS_PridnestrovieValidator("6054745000000000");
		assertTrue(npsWrongIin.checkLength());
		assertFalse(npsWrongIin.checkIINRanges());
		assertFalse(npsWrongIin.validate());

		RuPayValidator rupaySixty = new RuPayValidator("6000000000000000");
		assertTrue(rupaySixty.checkLength());
		assertTrue(rupaySixty.checkIINRanges());
		assertTrue(rupaySixty.validate());

		RuPayValidator rupay6521 = new RuPayValidator("6521000000000000");
		assertTrue(rupay6521.checkIINRanges());
		RuPayValidator rupay6522 = new RuPayValidator("6522000000000000");
		assertTrue(rupay6522.checkIINRanges());

		RuPayValidator rupayShort = new RuPayValidator("600000000000000");
		assertFalse(rupayShort.checkLength());
		assertTrue(rupayShort.checkIINRanges());
		assertFalse(rupayShort.validate());

		RuPayValidator rupayWrongIin = new RuPayValidator("6530000000000000");
		assertTrue(rupayWrongIin.checkLength());
		assertFalse(rupayWrongIin.checkIINRanges());
		assertFalse(rupayWrongIin.validate());

		TroyValidator troyValid = new TroyValidator("9792000000000000");
		assertTrue(troyValid.checkLength());
		assertTrue(troyValid.checkIINRanges());
		assertTrue(troyValid.validate());

		TroyValidator troyUpper = new TroyValidator("9792890000000000");
		assertTrue(troyUpper.checkIINRanges());

		TroyValidator troyShort = new TroyValidator("979200000000000");
		assertFalse(troyShort.checkLength());
		assertTrue(troyShort.checkIINRanges());
		assertFalse(troyShort.validate());

		TroyValidator troyWrongIin = new TroyValidator("9791990000000000");
		assertTrue(troyWrongIin.checkLength());
		assertFalse(troyWrongIin.checkIINRanges());
		assertFalse(troyWrongIin.validate());

		UATPValidator uatpValid = new UATPValidator("000001123456789");
		assertTrue(uatpValid.checkLength());
		assertTrue(uatpValid.checkIINRanges());
		assertTrue(uatpValid.validate());

		UATPValidator uatpShort = new UATPValidator("00000112345678");
		assertFalse(uatpShort.checkLength());
		assertTrue(uatpShort.checkIINRanges());
		assertFalse(uatpShort.validate());

		UATPValidator uatpWrongIin = new UATPValidator("000002123456789");
		assertTrue(uatpWrongIin.checkLength());
		assertFalse(uatpWrongIin.checkIINRanges());
		assertFalse(uatpWrongIin.validate());

		String verveRangeOneCard = generateLuhnNumber("506099000000000", 16);
		assertNotNull(verveRangeOneCard);
		VerveValidator verveRangeOne = new VerveValidator(verveRangeOneCard);
		assertTrue(verveRangeOne.checkLength());
		assertTrue(verveRangeOne.checkIINRanges());
		assertTrue(verveRangeOne.validate());

		String verveUpperNumber = generateLuhnNumber("506198000000000", 16);
		assertNotNull(verveUpperNumber);
		VerveValidator verveUpperRange = new VerveValidator(verveUpperNumber);
		assertTrue(verveUpperRange.checkLength());
		assertTrue(verveUpperRange.checkIINRanges());
		assertTrue(verveUpperRange.validate());

		String verveMaxNumber = generateLuhnNumber("5060991234567890", 19);
		assertNotNull(verveMaxNumber);
		VerveValidator verveMaxLengthValid = new VerveValidator(verveMaxNumber);
		assertTrue(verveMaxLengthValid.checkLength());
		assertTrue(verveMaxLengthValid.checkIINRanges());
		assertTrue(verveMaxLengthValid.validate());

		VerveValidator verveLowerEdge = new VerveValidator("0000000000000000");
		overwriteVerveDigits(verveLowerEdge, "5060991111111111");
		assertTrue(verveLowerEdge.checkLength());
		assertTrue(verveLowerEdge.checkIINRanges());
		assertTrue(verveLowerEdge.validate());

		VerveValidator verveUpperEdge = new VerveValidator("0000000000000000");
		overwriteVerveDigits(verveUpperEdge, "5061981111111111");
		assertTrue(verveUpperEdge.checkLength());
		assertTrue(verveUpperEdge.checkIINRanges());
		assertTrue(verveUpperEdge.validate());

		VerveValidator verveBelowLower = new VerveValidator("5060989999999999");
		assertTrue(verveBelowLower.checkLength());
		assertFalse(verveBelowLower.checkIINRanges());
		assertFalse(verveBelowLower.validate());

		VerveValidator verveAboveUpper = new VerveValidator("5061999999999999");
		assertTrue(verveAboveUpper.checkLength());
		assertFalse(verveAboveUpper.checkIINRanges());
		assertFalse(verveAboveUpper.validate());

		VerveValidator verveInjected = new VerveValidator("0000000000000000");
		overwriteVerveDigits(verveInjected, "5061501234567890");
		assertTrue(verveInjected.checkLength());
		assertTrue(verveInjected.checkIINRanges());

		VerveValidator verveInjectedSecondRange = new VerveValidator("0000000000000000");
		overwriteVerveDigits(verveInjectedSecondRange, "6500251234567890");
		assertTrue(verveInjectedSecondRange.checkLength());
		assertFalse(verveInjectedSecondRange.checkIINRanges());

		VerveValidator verveRangeTwo = new VerveValidator("6500020000000000");
		assertTrue(verveRangeTwo.checkLength());
		assertFalse(verveRangeTwo.checkIINRanges());
		assertFalse(verveRangeTwo.validate());

		VerveValidator verveShort = new VerveValidator("506099000000000");
		assertFalse(verveShort.checkLength());
		assertTrue(verveShort.checkIINRanges());
		assertFalse(verveShort.validate());

		VerveValidator verveWrongIin = new VerveValidator("5050000000000000");
		assertTrue(verveWrongIin.checkLength());
		assertFalse(verveWrongIin.checkIINRanges());
		assertFalse(verveWrongIin.validate());

		VerveValidator verveTooLong = new VerveValidator("50609900000000000000");
		assertFalse(verveTooLong.checkLength());
		assertTrue(verveTooLong.checkIINRanges());
		assertFalse(verveTooLong.validate());
	}

	private String generateLuhnNumber(String prefix, int totalLength) {
		StringBuilder builder = new StringBuilder(prefix);
		while (builder.length() < totalLength - 1) {
			builder.append('0');
		}
		LuhnValidator generator = new LuhnValidator();
		for (int digit = 0; digit <= 9; digit++) {
			builder.setLength(totalLength - 1);
			builder.append((char) ('0' + digit));
			if (generator.algorithmCheck(builder.toString())) {
				return builder.toString();
			}
		}
		return null;
	}

	private String repeatChar(char ch, int count) {
		StringBuilder builder = new StringBuilder(count);
		for (int i = 0; i < count; i++) {
			builder.append(ch);
		}
		return builder.toString();
	}

	private void overwriteVerveDigits(VerveValidator validator, String replacement) {
		try {
			Field listField = VerveValidator.class.getDeclaredField("creditCardNumberList");
			listField.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<Integer> digits = (List<Integer>) listField.get(validator);
			digits.clear();
			for (char ch : replacement.toCharArray()) {
				digits.add(Character.getNumericValue(ch));
			}
		} catch (ReflectiveOperationException e) {
			fail(e.getMessage());
		}
	}

}
