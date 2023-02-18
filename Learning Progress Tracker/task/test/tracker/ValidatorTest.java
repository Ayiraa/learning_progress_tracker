package tracker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ValidatorTest{
        @ParameterizedTest
        @DisplayName("Valid names should return true")
        @ValueSource(strings = {"Jean-Claude", "O'Neill", "Ivan"})
        public void validNamesTest(String name) {
            assertTrue(Validator.isValidFirstName(name));
        }

        @ParameterizedTest
        @DisplayName("Invalid names should return false")
        @ValueSource(strings = {"", "A", "Jean--Claude", "O''Neill",  "Ivan99", "Stanisław Oğuz", "~D0MInAt0R~"})
        public void invalidNamesTest(String name) {
            assertFalse(Validator.isValidFirstName(name));
        }

        @ParameterizedTest
        @DisplayName("Valid emails should return true")
        @ValueSource(strings = {"mail@mail.mail", "ivan.aiac99@gmail.com", "email.email@email.email", "astrid_sunny.m@outlook.com"})
        public void validEmailsTest(String email) {
            assertTrue(Validator.isValidEmail(email));
        }

        @ParameterizedTest
        @DisplayName("Invalid emails should return false")
        @ValueSource(strings = {"", "email", "*mail~7891@mail.com", "ivan.aiac.com", "***@mail.com", "mail@.com"})
        public void invalidEmailsTest(String email) {
            assertFalse(Validator.isValidEmail(email));
        }

        @ParameterizedTest
        @DisplayName("Valid course point formats should return true")
        @ValueSource(strings = {"1 1 1 1", "1 0 3 0", "0 10 0 10"})
        public void validCoursePointsFormatTest(String pointsFormat) {
            assertTrue(Validator.isPointsValid(pointsFormat));
        }

        @ParameterizedTest
        @DisplayName("Invalid course point formats should return false")
        @ValueSource(strings = {"-1 -1 -1 -1", "-1 0 3 0", "0 10 0 10 0 10", "one three zero seven"})
        public void invalidCoursePointsFormatTest(String pointsFormat) {
            assertFalse(Validator.isPointsValid(pointsFormat));
        }
}
