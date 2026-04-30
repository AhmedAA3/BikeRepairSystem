package MVCsem2.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticReportTest {
    private DiagnosticReport diagnosticReport;

    @BeforeEach
    public void setUp() {
        diagnosticReport = new DiagnosticReport();
    }

    @Test
    public void testNewDiagnosticReportHasNoResults() {
        assertFalse(diagnosticReport.hasResults(),
                "A new diagnostic report should not have results.");
    }

    @Test
    public void testAddWheelIsDamagedDiagnosticResult() {
        diagnosticReport.addDiagnosticResult("Wheel is damaged");

        assertTrue(diagnosticReport.hasResults(),
                "Diagnostic report should have results after adding Wheel is damaged.");
        assertEquals("Wheel is damaged", diagnosticReport.getDiagnosticResults().get(0),
                "Diagnostic result was not stored correctly.");
    }

    @Test
    public void testAddHeadlightsAreBrokenDiagnosticResult() {
        diagnosticReport.addDiagnosticResult("Headlights are broken");

        assertEquals("Headlights are broken", diagnosticReport.getDiagnosticResults().get(0),
                "Diagnostic result was not stored correctly.");
    }

    @Test
    public void testAddTwoDiagnosticResultsFromViewScenario() {
        diagnosticReport.addDiagnosticResult("Wheel is damaged");
        diagnosticReport.addDiagnosticResult("Headlights are broken");

        assertEquals(2, diagnosticReport.getDiagnosticResults().size(),
                "There should be two diagnostic results.");
        assertEquals("Wheel is damaged", diagnosticReport.getDiagnosticResults().get(0),
                "First diagnostic result is wrong.");
        assertEquals("Headlights are broken", diagnosticReport.getDiagnosticResults().get(1),
                "Second diagnostic result is wrong.");
    }

    @Test
    public void testEmptyDiagnosticResultIsNotAdded() {
        diagnosticReport.addDiagnosticResult("   ");

        assertFalse(diagnosticReport.hasResults(),
                "Empty diagnostic result should not be added.");
    }

    @Test
    public void testNullDiagnosticResultIsNotAdded() {
        diagnosticReport.addDiagnosticResult(null);

        assertFalse(diagnosticReport.hasResults(),
                "Null diagnostic result should not be added.");
    }

    @Test
    public void testCopyDiagnosticReport() {
        diagnosticReport.addDiagnosticResult("Wheel is damaged");
        diagnosticReport.addDiagnosticResult("Headlights are broken");

        DiagnosticReport copy = new DiagnosticReport(diagnosticReport);

        assertEquals(2, copy.getDiagnosticResults().size(),
                "Copied diagnostic report should contain two results.");
        assertEquals("Wheel is damaged", copy.getDiagnosticResults().get(0),
                "First copied diagnostic result is wrong.");
        assertEquals("Headlights are broken", copy.getDiagnosticResults().get(1),
                "Second copied diagnostic result is wrong.");
    }
}