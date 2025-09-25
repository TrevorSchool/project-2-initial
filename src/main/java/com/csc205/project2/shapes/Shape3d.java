package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: ChatGPT 5
 * Generation Date: 9/24/2025
 *
 * Original Prompt:
 * "Can you create me a class in Java called Shape3d inside the package com.csc205.project2.shapes
 * package, that implements the interface ThreeDimensionalShape containing abstract methods
 * double getSurfaceArea() and double getVolume(). Create concrete implementations of getVolume()
 * and getSufaceArea() in Shape3D that call the abstract methods from the interface. Create 2
 * property variables String name and String color, and can you make them private, but create
 * getters and setters that are public for each of those properties. Create the constructor for
 * the class for initialization. Create a toString() method formatting the object outputs
 * consistently. Create getter/setter methods as appropriate for this class given what I’ve
 * described. Please include proper JavaDoc documentation and ask for input validation and error
 * handling where appropriate within this class. Ensure common Java naming conventions are
 * followed throughout the class as well please. Can you also include this “ai generation
 * documentation” header, in-between the package and class header? Don’t fill it out, I can
 * do that part myself: X
 *
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
 * Base class for all 3D shapes in {@code com.csc205.project2.shapes}.
 * <p>
 * This class implements {@link ThreeDimensionalShape} and provides concrete, validated
 * implementations of {@link #getVolume()} and {@link #getSurfaceArea()} that delegate to
 * abstract "hook" methods ({@link #computeVolume()} and {@link #computeSurfaceArea()}).
 * Subclasses (e.g., {@code Sphere}, {@code Cube}, {@code Cylinder}) implement those hooks
 * with the appropriate geometry formulas.
 * </p>
 *
 * <h2>Design</h2>
 * <ul>
 *   <li><b>Template Method pattern:</b> Final public methods perform validation and delegate
 *       to protected abstract computations implemented by subclasses.</li>
 *   <li><b>Input validation:</b> Constructor and setters validate {@code name} and {@code color}
 *       to be non-null and non-blank. Computed volume/surface-area must be finite and
 *       non-negative.</li>
 *   <li><b>Error handling:</b> Violations throw {@link IllegalArgumentException} (for inputs)
 *       or {@link IllegalStateException} (for invalid computed results).</li>
 * </ul>
 *
 * <p><b>Naming conventions:</b> Class names are PascalCase; fields, parameters, and methods are
 * lowerCamelCase; constants (if added later) are UPPER_SNAKE_CASE.</p>
 */
public abstract class Shape3d implements ThreeDimensionalShape {

    /** Human-readable shape name, e.g., "Sphere", "Cube". */
    private String name;

    /** Color descriptor, e.g., "red", "#FF0000", "rgb(255,0,0)". */
    private String color;

    /**
     * Creates a new {@code Shape3d}.
     *
     * @param name  the shape's name; must be non-null and not blank
     * @param color the shape's color; must be non-null and not blank
     * @throws IllegalArgumentException if {@code name} or {@code color} is null/blank
     */
    public Shape3d(final String name, final String color) {
        this.name = requireNonBlank(name, "name");
        this.color = requireNonBlank(color, "color");
    }

    // -----------------------------------------------------------------------
    // Public API from ThreeDimensionalShape (concrete, validated)
    // -----------------------------------------------------------------------

    /**
     * Returns the volume of this shape.
     * <p>
     * This implementation delegates to {@link #computeVolume()} and enforces that the result is
     * finite and non-negative.
     * </p>
     *
     * @return the volume in cubic units
     * @throws IllegalStateException if the computed volume is NaN, infinite, or negative
     */
    @Override
    public final double getVolume() {
        final double v = computeVolume();
        validateNonNegativeFinite(v, "volume");
        return v;
    }

    /**
     * Returns the surface area of this shape.
     * <p>
     * This implementation delegates to {@link #computeSurfaceArea()} and enforces that the result is
     * finite and non-negative.
     * </p>
     *
     * @return the surface area in square units
     * @throws IllegalStateException if the computed surface area is NaN, infinite, or negative
     */
    @Override
    public final double getSurfaceArea() {
        final double a = computeSurfaceArea();
        validateNonNegativeFinite(a, "surface area");
        return a;
    }

    // -----------------------------------------------------------------------
    // Abstract hooks for subclasses to implement actual geometry
    // -----------------------------------------------------------------------

    /**
     * Computes the raw volume (in cubic units) for the specific 3D shape.
     *
     * @return the unvalidated volume value
     */
    protected abstract double computeVolume();

    /**
     * Computes the raw surface area (in square units) for the specific 3D shape.
     *
     * @return the unvalidated surface area value
     */
    protected abstract double computeSurfaceArea();

    // -----------------------------------------------------------------------
    // Properties: name & color (validated)
    // -----------------------------------------------------------------------

    /**
     * Gets the shape's name.
     *
     * @return the name (never null or blank once set)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the shape's name.
     *
     * @param name the new name; must be non-null and not blank
     * @throws IllegalArgumentException if {@code name} is null or blank
     */
    public void setName(final String name) {
        this.name = requireNonBlank(name, "name");
    }

    /**
     * Gets the shape's color.
     *
     * @return the color (never null or blank once set)
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the shape's color.
     *
     * @param color the new color; must be non-null and not blank
     * @throws IllegalArgumentException if {@code color} is null or blank
     */
    public void setColor(final String color) {
        this.color = requireNonBlank(color, "color");
    }

    // -----------------------------------------------------------------------
    // Object overrides
    // -----------------------------------------------------------------------

    /**
     * Returns a consistent, human-readable representation of this shape.
     *
     * @return a string like {@code Shape3d{name='Sphere', color='red'}}
     */
    @Override
    public String toString() {
        return String.format("Shape3d{name='%s', color='%s'}", name, color);
    }

    // -----------------------------------------------------------------------
    // Validation helpers (internal)
    // -----------------------------------------------------------------------

    /**
     * Ensures the given string is non-null and not blank (after trimming).
     *
     * @param value the value to check
     * @param field a field label for error messages
     * @return the trimmed string if valid
     * @throws IllegalArgumentException if invalid
     */
    private static String requireNonBlank(final String value, final String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " must not be null.");
        }
        final String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank.");
        }
        return trimmed;
    }

    /**
     * Ensures a computed metric is finite and non-negative.
     *
     * @param x     the value to check
     * @param label the metric label (e.g., "volume" or "surface area")
     * @throws IllegalStateException if value is NaN, infinite, or negative
     */
    private static void validateNonNegativeFinite(final double x, final String label) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalStateException("Computed " + label + " must be a finite number.");
        }
        if (x < 0.0d) {
            throw new IllegalStateException("Computed " + label + " must be non-negative.");
        }
    }
}
