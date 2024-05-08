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

}


