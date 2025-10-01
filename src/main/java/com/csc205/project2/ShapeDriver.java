package com.csc205.project2;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: ChatGPT 5
 * Generation Date: 9/30/2025
 *
 * Original Prompt:
 * "Now we need to create an advanced driver class ShapeDriver that demonstrates polymorphism (Array/List of Shape3D references holding different shapes), comparative analysis (which shape has the
 * largest volume for given constraints), interactive features (allow user to create shapes with custom parameters), Formatted output (Professional presentation of results). I will attach a
 * screenshot of an example output provided with the assignment. Please maintain Java naming conventions, and comments that help me understand what’s within these testing classes like you’ve done
 * for the other classes. the package needs to be package com.csc205.project2;"
 *
 * Follow-up Prompts (if any):
 * 1. "[Refinement prompt 1]"
 * 2. "[Refinement prompt 2]"
 *
 * Manual Modifications:
 * - Made references to the Shape3D class the old way: Shape3d
 * - It was necessary to correct because the code wouldn't work because the class Shape3d doesn't exist.
 *
 * Formula Verification:
 * - Volume formula verified against: [source]
 * - Surface area formula verified against: [source]
 */

import com.csc205.project2.shapes.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * ShapeDriver — Console app demonstrating:
 * <ul>
 *   <li><b>Polymorphism:</b> List of {@link ThreeDimensionalShape} with mixed concrete types.</li>
 *   <li><b>Comparative Analysis:</b> Largest volume / largest surface area / most efficient (V/SA),
 *       optionally under a surface-area cap.</li>
 *   <li><b>Interactive Input:</b> User can create shapes with validated parameters.</li>
 *   <li><b>Formatted Output:</b> Clean, readable report similar to the assignment sample.</li>
 * </ul>
 *
 * <p>Note: All math calls are via the interface (dynamic dispatch to subclass hooks),
 * while name/color are accessed through the shared base class {@link Shape3D}.</p>
 */
public final class ShapeDriver {

    private ShapeDriver() { /* utility class */ }

    /** Entry point. */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // ensure '.' decimal for consistent formatting
        final Scanner in = new Scanner(System.in);

        // Polymorphic collection: one list, many concrete shapes.
        final List<ThreeDimensionalShape> shapes = new ArrayList<>();

        // Seed some demo data so the first report looks like the screenshot example.
        shapes.add(new Sphere("Red Ball", "red", 5.0));
        shapes.add(new Cube("Blue Box", "blue", 4.0));
        shapes.add(new Cylinder("Gray Tube", "gray", 3.0, 6.0));

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt(in, "Choose an option [1-6]: ", 1, 6);
            System.out.println();

            switch (choice) {
                case 1 -> printCreatedShapesReport(shapes);
                case 2 -> analyzeAndPrintResults(shapes, Double.POSITIVE_INFINITY);
                case 3 -> {
                    double cap = readPositiveDouble(in, "Enter a maximum surface area cap (> 0): ");
                    System.out.println();
                    analyzeAndPrintResults(shapes, cap);
                }
                case 4 -> createShapeInteractively(in, shapes);
                case 5 -> editShapeIdentity(in, shapes);
                case 6 -> running = false;
                default -> System.out.println("Unexpected option. Try again.");
            }

            System.out.println();
        }

        System.out.println("Goodbye!");
    }

    // ───────────────────────────────── MENU / INTERACTION ─────────────────────────────────

    private static void printMainMenu() {
        System.out.println("=== 3D Shape Analysis System ===");
        System.out.println("1) Show created shapes report");
        System.out.println("2) Show analysis results (no SA constraint)");
        System.out.println("3) Show analysis results (with SA constraint)");
        System.out.println("4) Create a new shape (interactive)");
        System.out.println("5) Edit a shape's name/color");
        System.out.println("6) Exit");
        System.out.println();
    }

    /** Interactive factory: prompts for concrete type, reads validated parameters, adds to list. */
    private static void createShapeInteractively(Scanner in, List<ThreeDimensionalShape> shapes) {
        System.out.println("Create Shape:");
        System.out.println("  1) Sphere");
        System.out.println("  2) Cube");
        System.out.println("  3) Cylinder");
        System.out.println("  4) Rectangular Prism");
        System.out.println("  5) Pyramid (right rectangular)");
        int t = readInt(in, "Select shape type [1-5]: ", 1, 5);

        String name  = readNonBlankString(in, "Enter name: ");
        String color = readNonBlankString(in, "Enter color: ");

        try {
            switch (t) {
                case 1 -> {
                    double r = readPositiveDouble(in, "Enter radius (> 0): ");
                    shapes.add(new Sphere(name, color, r));
                }
                case 2 -> {
                    double a = readPositiveDouble(in, "Enter side length (> 0): ");
                    shapes.add(new Cube(name, color, a));
                }
                case 3 -> {
                    double r = readPositiveDouble(in, "Enter radius (> 0): ");
                    double h = readPositiveDouble(in, "Enter height (> 0): ");
                    shapes.add(new Cylinder(name, color, r, h));
                }
                case 4 -> {
                    double l = readPositiveDouble(in, "Enter length (> 0): ");
                    double w = readPositiveDouble(in, "Enter width  (> 0): ");
                    double h = readPositiveDouble(in, "Enter height (> 0): ");
                    shapes.add(new RectangularPrism(name, color, l, w, h));
                }
                case 5 -> {
                    double w = readPositiveDouble(in, "Enter base width  (> 0): ");
                    double l = readPositiveDouble(in, "Enter base length (> 0): ");
                    double h = readPositiveDouble(in, "Enter vertical height (> 0): ");
                    shapes.add(new Pyramid(name, color, w, l, h));
                }
                default -> System.out.println("Unknown type.");
            }
            System.out.println("\n✅ Shape created successfully.");
        } catch (IllegalArgumentException ex) {
            System.out.println("\n❌ Could not create shape: " + ex.getMessage());
        }
    }

    /** Simple editor for name/color (validates non-blank via base class). */
    private static void editShapeIdentity(Scanner in, List<ThreeDimensionalShape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("(no shapes to edit)");
            return;
        }
        for (int i = 0; i < shapes.size(); i++) {
            ThreeDimensionalShape s = shapes.get(i);
            String nm = base(s).getName();
            String cl = base(s).getColor();
            String type = s.getClass().getSimpleName();
            System.out.printf("[%d] %s (%s) - %s%n", i, nm, cl, type);
        }
        int idx = readInt(in, "Select index to edit: ", 0, shapes.size() - 1);
        Shape3D b = base(shapes.get(idx));
        String newName  = readNonBlankString(in, "Enter new name: ");
        String newColor = readNonBlankString(in, "Enter new color: ");
        try {
            b.setName(newName);
            b.setColor(newColor);
            System.out.println("✅ Updated successfully.");
        } catch (IllegalArgumentException ex) {
            System.out.println("❌ Update failed: " + ex.getMessage());
        }
    }

    // ───────────────────────────────── REPORTS ─────────────────────────────────

    /**
     * Prints a list of created shapes in the same feel as the sample screenshot.
     * Demonstrates polymorphism: SA/Volume are computed via the interface, not via type checks.
     */
    private static void printCreatedShapesReport(List<ThreeDimensionalShape> shapes) {
        System.out.println("=== 3D Shape Analysis System ===\n");

        if (shapes.isEmpty()) {
            System.out.println("Created Shapes:\n(none yet)\n");
            return;
        }

        System.out.println("Created Shapes:");
        for (int i = 0; i < shapes.size(); i++) {
            ThreeDimensionalShape s = shapes.get(i);
            String type = s.getClass().getSimpleName();
            Shape3D b = base(s);

            System.out.printf("%d. %s {name='%s', %s}%n",
                    i + 1, type, b.getName(), summarizeDimensions(s));
            System.out.printf("   - Surface Area: %s square units%n", fmt2(s.getSurfaceArea()));
            System.out.printf("   - Volume: %s cubic units%n%n", fmt2(s.getVolume()));
        }
    }

    /**
     * Performs comparative analysis and prints results block:
     *  - Largest Volume
     *  - Largest Surface Area
     *  - Most Efficient (Volume/Surface)
     *
     * If {@code saCap} is finite, only shapes with SA ≤ cap are considered.
     */
    private static void analyzeAndPrintResults(List<ThreeDimensionalShape> shapes, double saCap) {
        System.out.println("Analysis Results:");
        if (shapes.isEmpty()) {
            System.out.println("(no shapes to analyze)");
            return;
        }

        // Optionally filter by surface-area cap
        List<ThreeDimensionalShape> eligible = shapes.stream()
                .filter(s -> s.getSurfaceArea() <= saCap)
                .toList();

        if (eligible.isEmpty()) {
            System.out.println("No shapes meet the given surface-area constraint.");
            return;
        }

        // Largest Volume
        ThreeDimensionalShape maxV = eligible.stream()
                .max(Comparator.comparingDouble(ThreeDimensionalShape::getVolume))
                .orElse(null);

        // Largest Surface Area
        ThreeDimensionalShape maxSA = eligible.stream()
                .max(Comparator.comparingDouble(ThreeDimensionalShape::getSurfaceArea))
                .orElse(null);

        // Most Efficient (Volume / Surface)
        ThreeDimensionalShape bestRatio = eligible.stream()
                .max(Comparator.comparingDouble(s -> s.getVolume() / s.getSurfaceArea()))
                .orElse(null);

        if (maxV != null) {
            Shape3D b = base(maxV);
            System.out.printf("- Largest Volume: %s (%s)%n",
                    b.getName(), fmt2(maxV.getVolume()));
        }
        if (maxSA != null) {
            Shape3D b = base(maxSA);
            System.out.printf("- Largest Surface Area: %s (%s)%n",
                    b.getName(), fmt2(maxSA.getSurfaceArea()));
        }
        if (bestRatio != null) {
            Shape3D b = base(bestRatio);
            double ratio = bestRatio.getVolume() / bestRatio.getSurfaceArea();
            System.out.printf("- Most Efficient (Volume/Surface): %s (%s)%n",
                    b.getName(), fmt2(ratio));
        }
        if (Double.isFinite(saCap)) {
            System.out.printf("  (Surface-area cap applied: SA ≤ %s)%n", fmt2(saCap));
        }
    }

    // ───────────────────────────────── HELPERS ─────────────────────────────────

    /** Safe cast helper; all your shapes extend Shape3d. */
    private static Shape3D base(ThreeDimensionalShape s) {
        return (Shape3D) s;
    }

    /** Summarize dimensions in the style of the screenshot (shape-specific). */
    private static String summarizeDimensions(ThreeDimensionalShape s) {
        if (s instanceof Sphere sp) {
            return "radius=" + fmtPlain(sp.getRadius());
        } else if (s instanceof Cube c) {
            return "side=" + fmtPlain(c.getSideLength());
        } else if (s instanceof Cylinder c) {
            return "radius=" + fmtPlain(c.getRadius()) + ", height=" + fmtPlain(c.getHeight());
        } else if (s instanceof RectangularPrism rp) {
            return "length=" + fmtPlain(rp.getLength()) + ", width=" + fmtPlain(rp.getWidth())
                    + ", height=" + fmtPlain(rp.getHeight());
        } else if (s instanceof Pyramid p) {
            return "width=" + fmtPlain(p.getWidth()) + ", length=" + fmtPlain(p.getLength())
                    + ", height=" + fmtPlain(p.getHeight());
        }
        return "(unknown dims)";
    }

    // Number formatting (two decimals to match the sample)
    private static String fmt2(double x) { return String.format("%.2f", x); }
    private static String fmtPlain(double x) { return String.format("%.1f", x); }

    // Robust input readers with validation and re-prompt
    private static int readInt(Scanner in, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine();
            try {
                int v = Integer.parseInt(s.trim());
                if (v < min || v > max) {
                    System.out.printf("Enter an integer in [%d, %d].%n", min, max);
                } else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static double readPositiveDouble(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine();
            try {
                double v = Double.parseDouble(s.trim());
                if (Double.isNaN(v) || Double.isInfinite(v) || v <= 0.0) {
                    System.out.println("Enter a positive, finite number.");
                } else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readNonBlankString(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine();
            if (line == null) {
                System.out.println("Input cannot be null.");
                continue;
            }
            String t = line.trim();
            if (t.isEmpty()) {
                System.out.println("Input cannot be blank.");
                continue;
            }
            return t;
        }
    }
}
