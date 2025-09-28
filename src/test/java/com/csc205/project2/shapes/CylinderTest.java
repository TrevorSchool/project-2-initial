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
 * Tests for {@link Cylinder}.
 */
class CylinderTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("Constructor & getters")
    void constructorAndGetters() {
        Cylinder c = new Cylinder("Cylinder", "gray", 2.0, 5.0);
        assertEquals("Cylinder", c.getName());
        assertEquals("gray", c.getColor());
        assertEquals(2.0, c.getRadius(), EPS);
        assertEquals(5.0, c.getHeight(), EPS);
    }

    @Test
    @DisplayName("Setters: update radius/height")
    void settersValid() {
        Cylinder c = new Cylinder("C", "c", 1.0, 1.0);
        c.setRadius(3.5);
        c.setHeight(7.25);
        c.setName("New");
        c.setColor("blue");
        assertEquals(3.5, c.getRadius(), EPS);
        assertEquals(7.25, c.getHeight(), EPS);
        assertEquals("New", c.getName());
        assertEquals("blue", c.getColor());
    }

    @Test
    @DisplayName("Accuracy: r=2,h=5 => SA=2πr(h+r)=28π, V=πr^2h=20π")
    void accuracy() {
        Cylinder c = new Cylinder("Cylinder", "gray", 2.0, 5.0);
        assertEquals(28.0 * Math.PI, c.getSurfaceArea(), EPS);
        assertEquals(20.0 * Math.PI, c.getVolume(), EPS);
    }

    @Test
    @DisplayName("Boundary: tiny and huge dimensions are finite")
    void boundaries() {
        Cylinder tiny = new Cylinder("tiny", "white", 1e-9, 1e-9);
        assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        assertTrue(Double.isFinite(tiny.getVolume()));

        Cylinder huge = new Cylinder("huge", "black", 1e6, 1e6);
        assertTrue(Double.isFinite(huge.getSurfaceArea()));
        assertTrue(Double.isFinite(huge.getVolume()));
    }

    @Test
    @DisplayName("Validation: zero/negative/NaN/INF radius/height rejected")
    void invalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Cylinder("C", "c", 0.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Cylinder("C", "c", 1.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Cylinder("C", "c", -1.0, 1.0));

        Cylinder c = new Cylinder("C", "c", 1.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> c.setRadius(0.0));
        assertThrows(IllegalArgumentException.class, () -> c.setRadius(-2.0));
        assertThrows(IllegalArgumentException.class, () -> c.setRadius(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> c.setHeight(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Polymorphism: interface reference computes cylinder formulas")
    void polymorphism() {
        ThreeDimensionalShape shape = new Cylinder("Cyl", "gray", 1.5, 4.0);
        assertEquals(2.0 * Math.PI * 1.5 * (4.0 + 1.5), shape.getSurfaceArea(), EPS);
        assertEquals(Math.PI * 1.5 * 1.5 * 4.0, shape.getVolume(), EPS);
    }
}
