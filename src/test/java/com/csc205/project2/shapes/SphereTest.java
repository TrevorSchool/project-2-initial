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
 * Tests for {@link Sphere}.
 * Covers: constructor/getters/setters, accuracy, boundaries, validation, and polymorphism.
 */
class SphereTest {

    private static final double EPS = 1e-9;

    @Test
    @DisplayName("Constructor & getters: valid radius")
    void constructorAndGetters() {
        Sphere s = new Sphere("Sphere", "red", 3.0);
        assertEquals("Sphere", s.getName());
        assertEquals("red", s.getColor());
        assertEquals(3.0, s.getRadius(), EPS);
    }

    @Test
    @DisplayName("Setters: update name/color/radius with valid values")
    void settersValid() {
        Sphere s = new Sphere("S", "c", 1.0);
        s.setName("NewName");
        s.setColor("blue");
        s.setRadius(2.5);
        assertEquals("NewName", s.getName());
        assertEquals("blue", s.getColor());
        assertEquals(2.5, s.getRadius(), EPS);
    }

    @Test
    @DisplayName("Accuracy: r=3 has SA=36π and V=36π")
    void accuracy() {
        Sphere s = new Sphere("Sphere", "red", 3.0);
        double expected = 36.0 * Math.PI;
        assertEquals(expected, s.getSurfaceArea(), EPS);
        assertEquals(expected, s.getVolume(), EPS);
    }

    @Test
    @DisplayName("Boundary: extremely small and large radii remain finite")
    void boundariesSmallLarge() {
        Sphere tiny = new Sphere("tiny", "white", 1e-9);
        assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        assertTrue(Double.isFinite(tiny.getVolume()));

        Sphere huge = new Sphere("huge", "black", 1e6);
        assertTrue(Double.isFinite(huge.getSurfaceArea()));
        assertTrue(Double.isFinite(huge.getVolume()));
    }

    @Test
    @DisplayName("Validation: zero/negative/NaN/INF radius rejected")
    void invalidRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Sphere("s", "c", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Sphere("s", "c", -1.0));

        Sphere s = new Sphere("s", "c", 1.0);
        assertThrows(IllegalArgumentException.class, () -> s.setRadius(0.0));
        assertThrows(IllegalArgumentException.class, () -> s.setRadius(-2.0));
        assertThrows(IllegalArgumentException.class, () -> s.setRadius(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> s.setRadius(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Validation: null/blank name & color rejected by base class")
    void invalidNameColor() {
        assertThrows(IllegalArgumentException.class, () -> new Sphere(null, "red", 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Sphere("Sphere", null, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Sphere("   ", "blue", 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Sphere("Sphere", "   ", 1.0));

        Sphere s = new Sphere("Sphere", "blue", 1.0);
        assertThrows(IllegalArgumentException.class, () -> s.setName(" "));
        assertThrows(IllegalArgumentException.class, () -> s.setColor("\t"));
    }

    @Test
    @DisplayName("Polymorphism: call via ThreeDimensionalShape reference")
    void polymorphism() {
        ThreeDimensionalShape shape = new Sphere("Sphere", "red", 2.0);
        // getSurfaceArea/getVolume are from the interface -> implemented in Shape3d delegating to Sphere hooks
        assertEquals(4.0 * Math.PI * 4.0, shape.getSurfaceArea(), EPS);
        assertEquals((4.0 / 3.0) * Math.PI * 8.0, shape.getVolume(), EPS);
    }
}
