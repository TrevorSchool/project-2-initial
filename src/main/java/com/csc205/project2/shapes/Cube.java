package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: ChatGPT 5
 * Generation Date: 9/26/2025
 *
 * Original Prompt:
 * "Can you also create the following Java classes with the following properties, in the
 * same package as Shape3D: Sphere (properties: radius), Cube (properties: sideLength),
 * Cylinder (properties: radius, height), RectangularPrism (properties: length, width,
 * height), and Pyramid (properties: width, length, height). Each of the classes needs to
 * extend Shape 3D. Each class needs to implement the abstract calculation methods from
 * Shape3D. include proper contructors with validation. Override the toString() but use
 * shape-specific formatting. Please include proper JavaDoc documentation and ask for input
 * validation and error handling where appropriate within this class. Ensure common Java
 * naming conventions are followed throughout the class as well please."
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

/**
 * A cube defined by its side length.
 * <p>Formulas:
 * <ul>
 *   <li>Surface Area: {@code 6 * a^2}</li>
 *   <li>Volume: {@code a^3}</li>
 * </ul>
 */
public final class Cube extends Shape3D {

    private double sideLength;

    /**
     * Creates a new {@code Cube}.
     *
     * @param name       non-null, non-blank shape name
     * @param color      non-null, non-blank color descriptor
     * @param sideLength side length &gt; 0
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Cube(final String name, final String color, final double sideLength) {
        super(name, color);
        setSideLength(sideLength);
    }

    /** Returns the side length. */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Sets the side length.
     *
     * @param sideLength side length &gt; 0
     * @throws IllegalArgumentException if {@code sideLength} ≤ 0 or NaN/∞
     */
    public void setSideLength(final double sideLength) {
        Sphere.validatePositiveFinite(sideLength, "sideLength");
        this.sideLength = sideLength;
    }

    @Override
    protected double computeVolume() {
        return sideLength * sideLength * sideLength;
    }

    @Override
    protected double computeSurfaceArea() {
        return 6.0 * sideLength * sideLength;
    }

    @Override
    public String toString() {
        return String.format("Cube{name='%s', color='%s', sideLength=%.6f, surfaceArea=%.6f, volume=%.6f}",
                getName(), getColor(), sideLength, getSurfaceArea(), getVolume());
    }
}
