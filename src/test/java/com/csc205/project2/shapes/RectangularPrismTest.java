package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: ChatGPT 5
 * Generation Date: 9/28/2025
 *
 * Original Prompt:
 * "For each of the shape classes we just created we also need to generate Junit 5 test classes. We need to test Basic Functionality (Constructor, getters, setters),
 * Calculation Accuracy (Volume and surface area with known values), Boundary Testing (Zero values, very small/large numbers), Input Validation (Negative values,
 * null inputs), and Inheritance Testing (Polymorphic behavior verification). Please maintain Java naming conventions, and comments that help me understand what’s
 * within these testing classes like you’ve done for the other classes."
 *
 * Follow-up Prompts (if any):
 * 1. "[Refinement prompt 1]"
 * 2. "[Refinement prompt 2]"
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Volume formula verified against: [source]
 * - Surface area formula verified against: [source]
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link RectangularPrism}.
 */
class RectangularPrismTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("Constructor & getters")
    void constructorAndGetters() {
        RectangularPrism rp = new RectangularPrism("Prism", "orange", 3.0, 4.0, 5.0);
        assertEquals("Prism", rp.getName());
        assertEquals("orange", rp.getColor());
        assertEquals(3.0, rp.getLength(), EPS);
        assertEquals(4.0, rp.getWidth(), EPS);
        assertEquals(5.0, rp.getHeight(), EPS);
    }

    @Test
    @DisplayName("Setters: update dimensions")
    void settersValid() {
        RectangularPrism rp = new RectangularPrism("P", "c", 1.0, 1.0, 1.0);
        rp.setLength(3.0);
        rp.setWidth(4.0);
        rp.setHeight(5.0);
        rp.setName("New");
        rp.setColor("blue");
        assertEquals(3.0, rp.getLength(), EPS);
        assertEquals(4.0, rp.getWidth(), EPS);
        assertEquals(5.0, rp.getHeight(), EPS);
        assertEquals("New", rp.getName());
        assertEquals("blue", rp.getColor());
    }

    @Test
    @DisplayName("Accuracy: l=3,w=4,h=5 => SA=94, V=60")
    void accuracy() {
        RectangularPrism rp = new RectangularPrism("Prism", "orange", 3.0, 4.0, 5.0);
        assertEquals(94.0, rp.getSurfaceArea(), EPS);
        assertEquals(60.0, rp.getVolume(), EPS);
    }

    @Test
    @DisplayName("Boundary: tiny and huge dimensions are finite")
    void boundaries() {
        RectangularPrism tiny = new RectangularPrism("tiny", "white", 1e-9, 1e-9, 1e-9);
        assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        assertTrue(Double.isFinite(tiny.getVolume()));

        RectangularPrism huge = new RectangularPrism("huge", "black", 1e6, 1e6, 1e6);
        assertTrue(Double.isFinite(huge.getSurfaceArea()));
        assertTrue(Double.isFinite(huge.getVolume()));
    }

    @Test
    @DisplayName("Validation: zero/negative/NaN/INF inputs rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("P", "c", 0.0, 2.0, 3.0));
        assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("P", "c", 2.0, -1.0, 3.0));

        RectangularPrism rp = new RectangularPrism("P", "c", 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> rp.setLength(0.0));
        assertThrows(IllegalArgumentException.class, () -> rp.setWidth(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> rp.setHeight(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Polymorphism: interface reference computes prism formulas")
    void polymorphism() {
        ThreeDimensionalShape shape = new RectangularPrism("Prism", "orange", 2.0, 3.0, 4.0);
        assertEquals(2.0 * (2*3 + 2*4 + 3*4), shape.getSurfaceArea(), EPS);
        assertEquals(2.0 * 3.0 * 4.0, shape.getVolume(), EPS);
    }
}
