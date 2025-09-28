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
 * Tests for {@link Cube}.
 */
class CubeTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("Constructor & getters")
    void constructorAndGetters() {
        Cube c = new Cube("Cube", "green", 2.0);
        assertEquals("Cube", c.getName());
        assertEquals("green", c.getColor());
        assertEquals(2.0, c.getSideLength(), EPS);
    }

    @Test
    @DisplayName("Setters: update fields")
    void settersValid() {
        Cube c = new Cube("C", "c", 1.0);
        c.setName("New");
        c.setColor("blue");
        c.setSideLength(3.0);
        assertEquals("New", c.getName());
        assertEquals("blue", c.getColor());
        assertEquals(3.0, c.getSideLength(), EPS);
    }

    @Test
    @DisplayName("Accuracy: side=2 => SA=24, V=8")
    void accuracy() {
        Cube c = new Cube("Cube", "green", 2.0);
        assertEquals(24.0, c.getSurfaceArea(), EPS);
        assertEquals(8.0, c.getVolume(), EPS);
    }

    @Test
    @DisplayName("Boundary: tiny and huge side lengths are finite")
    void boundariesSmallLarge() {
        Cube tiny = new Cube("tiny", "white", 1e-9);
        assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        assertTrue(Double.isFinite(tiny.getVolume()));

        Cube huge = new Cube("huge", "black", 1e6);
        assertTrue(Double.isFinite(huge.getSurfaceArea()));
        assertTrue(Double.isFinite(huge.getVolume()));
    }

    @Test
    @DisplayName("Validation: zero/negative/NaN/INF side rejected")
    void invalidSide() {
        assertThrows(IllegalArgumentException.class, () -> new Cube("Cube", "c", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Cube("Cube", "c", -1.0));

        Cube c = new Cube("Cube", "c", 1.0);
        assertThrows(IllegalArgumentException.class, () -> c.setSideLength(0.0));
        assertThrows(IllegalArgumentException.class, () -> c.setSideLength(-2.0));
        assertThrows(IllegalArgumentException.class, () -> c.setSideLength(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> c.setSideLength(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Polymorphism: interface reference computes cube formulas")
    void polymorphism() {
        ThreeDimensionalShape shape = new Cube("Cube", "green", 3.0);
        assertEquals(6.0 * 9.0, shape.getSurfaceArea(), EPS);
        assertEquals(27.0, shape.getVolume(), EPS);
    }
}
