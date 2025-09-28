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
 * An axis-aligned rectangular prism (cuboid) defined by length, width, and height.
 * <p>Formulas:
 * <ul>
 *   <li>Surface Area: {@code 2(lw + lh + wh)}</li>
 *   <li>Volume: {@code lwh}</li>
 * </ul>
 */
public final class RectangularPrism extends Shape3D {

    private double length;
    private double width;
    private double height;

    /**
     * Creates a new {@code RectangularPrism}.
     *
     * @param name   non-null, non-blank shape name
     * @param color  non-null, non-blank color descriptor
     * @param length length &gt; 0
     * @param width  width  &gt; 0
     * @param height height &gt; 0
     * @throws IllegalArgumentException if any argument is invalid
     */
    public RectangularPrism(final String name, final String color,
                            final double length, final double width, final double height) {
        super(name, color);
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    public double getLength() { return length; }

    /**
     * Sets the length.
     * @param length length &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setLength(final double length) {
        Sphere.validatePositiveFinite(length, "length");
        this.length = length;
    }

    public double getWidth() { return width; }

    /**
     * Sets the width.
     * @param width width &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setWidth(final double width) {
        Sphere.validatePositiveFinite(width, "width");
        this.width = width;
    }

    public double getHeight() { return height; }

    /**
     * Sets the height.
     * @param height height &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setHeight(final double height) {
        Sphere.validatePositiveFinite(height, "height");
        this.height = height;
    }

    @Override
    protected double computeVolume() {
        return length * width * height;
    }

    @Override
    protected double computeSurfaceArea() {
        return 2.0 * (length * width + length * height + width * height);
    }

    @Override
    public String toString() {
        return String.format("RectangularPrism{name='%s', color='%s', length=%.6f, width=%.6f, height=%.6f, surfaceArea=%.6f, volume=%.6f}",
                getName(), getColor(), length, width, height, getSurfaceArea(), getVolume());
    }
}
