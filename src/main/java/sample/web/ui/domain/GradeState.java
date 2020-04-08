package sample.web.ui.domain;

/**
 *
 * @author  Mark van Dalen
 *
 */

public interface GradeState {
    void next(Grade pkg);
    void prev(Grade pkg);
    void printStatus();
}
