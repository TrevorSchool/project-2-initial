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
 * A sphere defined by its radius.
 * <p>Formulas:
 * <ul>
 *   <li>Surface Area: {@code 4 * π * r^2}</li>
 *   <li>Volume: {@code (4/3) * π * r^3}</li>
 * </ul>
 */
public final class Sphere extends Shape3D {

    private double radius;

    /**
     * Creates a new {@code Sphere}.
     *
     * @param name   non-null, non-blank shape name
     * @param color  non-null, non-blank color descriptor
     * @param radius radius &gt; 0
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Sphere(final String name, final String color, final double radius) {
        super(name, color);
        setRadius(radius);
    }

    /** Returns the radius. */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius.
     *
     * @param radius radius &gt; 0
     * @throws IllegalArgumentException if {@code radius} ≤ 0 or NaN/∞
     */
    public void setRadius(final double radius) {
        validatePositiveFinite(radius, "radius");
        this.radius = radius;
    }

    @Override
    protected double computeVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    @Override
    protected double computeSurfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.format("Sphere{name='%s', color='%s', radius=%.6f, surfaceArea=%.6f, volume=%.6f}",
                getName(), getColor(), radius, getSurfaceArea(), getVolume());
    }

    // ---- validation helper (package-private for reuse) ----
    static void validatePositiveFinite(final double x, final String label) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0.0) {
            throw new IllegalArgumentException(label + " must be a positive, finite number.");
        }
    }
}
