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
 * Tests for {@link Pyramid} (right rectangular pyramid).
 */
class PyramidTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("Constructor & getters")
    void constructorAndGetters() {
        Pyramid p = new Pyramid("Pyramid", "gold", 4.0, 6.0, 5.0);
        assertEquals("Pyramid", p.getName());
        assertEquals("gold", p.getColor());
        assertEquals(4.0, p.getWidth(), EPS);
        assertEquals(6.0, p.getLength(), EPS);
        assertEquals(5.0, p.getHeight(), EPS);
    }

    @Test
    @DisplayName("Setters: update width/length/height")
    void settersValid() {
        Pyramid p = new Pyramid("P", "c", 2.0, 3.0, 4.0);
        p.setWidth(4.0);
        p.setLength(6.0);
        p.setHeight(5.0);
        p.setName("New");
        p.setColor("blue");
        assertEquals(4.0, p.getWidth(), EPS);
        assertEquals(6.0, p.getLength(), EPS);
        assertEquals(5.0, p.getHeight(), EPS);
        assertEquals("New", p.getName());
        assertEquals("blue", p.getColor());
    }

    @Test
    @DisplayName("Accuracy: w=4,l=6,h=5 => V=40, SA = base + lateral")
    void accuracy() {
        Pyramid p = new Pyramid("Pyramid", "gold", 4.0, 6.0, 5.0);

        // Expected volume
        double expectedV = (1.0 / 3.0) * 4.0 * 6.0 * 5.0;

        // slant heights for a right rectangular pyramid
        double sW = Math.sqrt(Math.pow(4.0 / 2.0, 2) + Math.pow(5.0, 2)); // faces with base length
        double sL = Math.sqrt(Math.pow(6.0 / 2.0, 2) + Math.pow(5.0, 2)); // faces with base width
        double baseArea = 4.0 * 6.0;
        double lateral = 6.0 * sW + 4.0 * sL;
        double expectedSA = baseArea + lateral;

        assertEquals(expectedV, p.getVolume(), EPS);
        assertEquals(expectedSA, p.getSurfaceArea(), EPS);
    }

    @Test
    @DisplayName("Boundary: tiny and huge dimensions are finite")
    void boundaries() {
        Pyramid tiny = new Pyramid("tiny", "white", 1e-9, 1e-9, 1e-9);
        assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        assertTrue(Double.isFinite(tiny.getVolume()));

        Pyramid huge = new Pyramid("huge", "black", 1e6, 1e6, 1e6);
        assertTrue(Double.isFinite(huge.getSurfaceArea()));
        assertTrue(Double.isFinite(huge.getVolume()));
    }

    @Test
    @DisplayName("Validation: zero/negative/NaN/INF inputs rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Pyramid("P", "c", 0.0, 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Pyramid("P", "c", 1.0, -1.0, 1.0));

        Pyramid p = new Pyramid("P", "c", 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> p.setWidth(0.0));
        assertThrows(IllegalArgumentException.class, () -> p.setLength(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> p.setHeight(Double.NEGATIVE_INFINITY));
    }

    @Test
    @DisplayName("Polymorphism: interface reference computes pyramid formulas")
    void polymorphism() {
        ThreeDimensionalShape shape = new Pyramid("P", "gold", 4.0, 6.0, 5.0);
        // spot-check volume matches interface-dispatched result
        assertEquals((1.0 / 3.0) * 4.0 * 6.0 * 5.0, shape.getVolume(), EPS);
        assertTrue(shape.getSurfaceArea() > 0.0);
    }
}
