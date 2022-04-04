// package com.demo.toolrental.demo.services;

// import com.demo.toolrental.demo.models.RentalAgreement;
// import com.demo.toolrental.demo.models.Tooltype;
// import com.demo.toolrental.demo.models.Tools;

// import java.time.LocalDate;
// import java.util.stream.Stream;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
// import org.mockito.InjectMocks;

// /**
//  *
//  * @author Miguel
//  */
// public class CheckoutServiceTests {

//     @InjectMocks
//     CheckoutService checkout;

//     private static Tooltype[] tooltype = {
//         new Tooltype("Ladder", 1.99, true, true, false),
//         new Tooltype("Chainsaw", 1.49, true, false, true),
//         new Tooltype("Jackhammer", 2.99, true, false, false)
//     };
//     private static Tools[] tools = {
//         new Tools("CHNS", Long.valueOf(1), "Stihl"),
//         new Tools("LADW", Long.valueOf(2), "Werner"),
//         new Tools("JAKD", Long.valueOf(3), "DeWalt"),
//         new Tools("JAKR", Long.valueOf(4), "Ridgid")
//     };

//     @BeforeEach
//     void setUp() {
//     }

//     @Test
//     public void printTest() {
//         System.out.println("Running JUNIT test");
//     }

//     @ParameterizedTest
//     @MethodSource("provideErrorParameters")
//     void testExpectedException(Tooltype tooltype, Tools tools, int rentalDayCount, int discountPercent, LocalDate checkoutDate, String exceptionMessage) {
//         Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            checkout.checkout(tooltype, tools, rentalDayCount, discountPercent, checkoutDate);
//         }, exceptionMessage);

//     }

//     @ParameterizedTest
//     @MethodSource("provideParameters")
//     void testSuccessCheckout(Tooltype tooltype, Tools tools, int rentalDayCount, int discountPercent, LocalDate checkoutDate, double testedCharge ) throws Exception {
//         RentalAgreement agreement = checkout.checkout(tooltype, tools, rentalDayCount, discountPercent, checkoutDate);
//         // System.out.println(agreement.getChargeDays());
//         Assertions.assertTrue(agreement.getFinalCharge() == testedCharge);
//     }

//     private static Stream<Arguments> provideParameters() {
//         return Stream.of(
//                 Arguments.of(tooltype[0], tools[1], 3, 10, LocalDate.of(2020, 7, 2), 3.58),
//                 Arguments.of(tooltype[1], tools[0], 5, 25, LocalDate.of(2015, 7, 2), 3.35),
//                 Arguments.of(tooltype[2], tools[2], 6, 0, LocalDate.of(2015, 9, 3), 8.98),
//                 Arguments.of(tooltype[2], tools[3], 9, 0, LocalDate.of(2015, 7, 2), 14.95),
//                 Arguments.of(tooltype[2], tools[3], 4, 50, LocalDate.of(2020, 7, 2), 1.50)
//         );
//     }
    
//     private static Stream<Arguments> provideErrorParameters() {
//         return Stream.of(
//                 Arguments.of(tooltype[2], tools[3], 101, LocalDate.of(2015, 9, 3), "Please enter discount percent between 0 and 100"),
//                 Arguments.of(tooltype[1], tools[0], -5, 25, LocalDate.of(2015, 7, 2), "Please enter rental agreement days larger than 1"),
//                 Arguments.of(tooltype[2], tools[2], 6, 0, LocalDate.of(2015, 9, 3), "System could not find tool. Please search again.")
//         );
//     }
// }
