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
 * A right rectangular pyramid with base {@code width × length} and vertical height {@code height}.
 * <p>Assumes the apex is directly above the center of the base (right pyramid). Slant heights:
 * <ul>
 *   <li>{@code s_w = sqrt((width/2)^2 + height^2)} for faces with base {@code length}</li>
 *   <li>{@code s_l = sqrt((length/2)^2 + height^2)} for faces with base {@code width}</li>
 * </ul>
 * Formulas:
 * <ul>
 *   <li>Volume: {@code (1/3) * width * length * height}</li>
 *   <li>Surface Area (total): {@code baseArea + lateralArea = (width * length) + (length * s_w) + (width * s_l)}</li>
 * </ul>
 */
public final class Pyramid extends Shape3D {

    private double width;
    private double length;
    private double height;

    /**
     * Creates a new {@code Pyramid}.
     *
     * @param name   non-null, non-blank shape name
     * @param color  non-null, non-blank color descriptor
     * @param width  base width  &gt; 0
     * @param length base length &gt; 0
     * @param height vertical height &gt; 0
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Pyramid(final String name, final String color,
                   final double width, final double length, final double height) {
        super(name, color);
        setWidth(width);
        setLength(length);
        setHeight(height);
    }

    public double getWidth() { return width; }

    /**
     * Sets the base width.
     * @param width &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setWidth(final double width) {
        Sphere.validatePositiveFinite(width, "width");
        this.width = width;
    }

    public double getLength() { return length; }

    /**
     * Sets the base length.
     * @param length &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setLength(final double length) {
        Sphere.validatePositiveFinite(length, "length");
        this.length = length;
    }

    public double getHeight() { return height; }

    /**
     * Sets the vertical height.
     * @param height &gt; 0
     * @throws IllegalArgumentException if invalid
     */
    public void setHeight(final double height) {
        Sphere.validatePositiveFinite(height, "height");
        this.height = height;
    }

    @Override
    protected double computeVolume() {
        return (1.0 / 3.0) * width * length * height;
    }

    @Override
    protected double computeSurfaceArea() {
        final double sW = Math.sqrt((width * 0.5) * (width * 0.5) + height * height);   // slant height for faces with base length
        final double sL = Math.sqrt((length * 0.5) * (length * 0.5) + height * height); // slant height for faces with base width
        final double baseArea = width * length;
        final double lateralArea = length * sW + width * sL; // two faces each direction combined
        return baseArea + lateralArea;
    }

    @Override
    public String toString() {
        return String.format("Pyramid{name='%s', color='%s', width=%.6f, length=%.6f, height=%.6f, surfaceArea=%.6f, volume=%.6f}",
                getName(), getColor(), width, length, height, getSurfaceArea(), getVolume());
    }
}
