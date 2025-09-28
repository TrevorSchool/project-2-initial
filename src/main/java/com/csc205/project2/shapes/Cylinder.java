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
 * A right circular cylinder defined by radius and height.
 * <p>Formulas:
 * <ul>
 *   <li>Surface Area (total): {@code 2πr(h + r)}</li>
 *   <li>Volume: {@code πr^2h}</li>
 * </ul>
 */
public final class Cylinder extends Shape3D {

    private double radius;
    private double height;

    /**
     * Creates a new {@code Cylinder}.
     *
     * @param name   non-null, non-blank shape name
     * @param color  non-null, non-blank color descriptor
     * @param radius radius &gt; 0
     * @param height height &gt; 0
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Cylinder(final String name, final String color, final double radius, final double height) {
        super(name, color);
        setRadius(radius);
        setHeight(height);
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
        Sphere.validatePositiveFinite(radius, "radius");
        this.radius = radius;
    }

    /** Returns the height. */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height.
     *
     * @param height height &gt; 0
     * @throws IllegalArgumentException if {@code height} ≤ 0 or NaN/∞
     */
    public void setHeight(final double height) {
        Sphere.validatePositiveFinite(height, "height");
        this.height = height;
    }

    @Override
    protected double computeVolume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    protected double computeSurfaceArea() {
        return 2.0 * Math.PI * radius * (height + radius);
    }

    @Override
    public String toString() {
        return String.format("Cylinder{name='%s', color='%s', radius=%.6f, height=%.6f, surfaceArea=%.6f, volume=%.6f}",
                getName(), getColor(), radius, height, getSurfaceArea(), getVolume());
    }
}
