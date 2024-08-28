package org.bughouse.fen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FenValidatorTest {

    @Test
    public void noErrors() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("K1k5/8/8/8/8/8/8/8 w - - 0 1");
        assertEquals(returnCode.getCode(), 0);
    }

    @Test
    public void bothSidesInCheck() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("KrkR4/8/8/8/8/8/8/8 w - - 0 1");
        assertEquals(returnCode.getCode(), 15);
    }

    @Test
    public void emptyFen() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("   ");
        assertEquals(returnCode.getCode(), -1);
    }

    @Test
    public void missingWhiteKing() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("  k7/8/8/8/8/8/8/8 w - - 0 1  ");
        assertEquals(returnCode.getCode(), 11);
    }

    @Test
    public void tooManyKings() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("  k1K1K3/8/8/8/8/8/8/8 w - - 0 1  ");
        assertEquals(returnCode.getCode(), 12);
    }

    @Test
    public void kingsTooCloseToEachOther() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("    kK6/8/8/8/8/8/8/8 w - - 0 1");
        assertEquals(returnCode.getCode(), 14);
    }

    @Test
    public void leadingAndTrailingSpacesIgnored() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("    K1k5/8/8/8/8/8/8/8 w - - 0 1 ");
        assertEquals(returnCode.getCode(), 0);
        returnCode = validator.validate("K1k5/8/8/8/8/8/8/8 w - - 0 1");
        assertEquals(returnCode.getCode(), 0);
    }

    @Test
    public void sideInCheckMustMove() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("8/1k6/8/1Q6/8/1K6/8/8 w - - 0 1");
        assertEquals(returnCode.getCode(), 16);
    }

    @Test
    public void tripleAndMoreChecks() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("kQ6/QQ6/K7/8/8/8/8/8 b - - 0 1");
        assertEquals(returnCode.getCode(), 17);
    }

    @Test
    public void invalidDiscoveredCheck() {
        FenValidator validator = FenValidator.getInstance();
        ReturnCode returnCode = validator.validate("k3R3/8/8/Q7/K7/8/8/8 b - - 0 1");
        assertEquals(returnCode.getCode(), 18);
    }

}


